from flask import Flask, request, jsonify
from tensorflow.keras.models import load_model
from tensorflow.keras.preprocessing.image import load_img, img_to_array
from google.cloud import storage
import numpy as np
import os
import json
from threading import Lock
import uuid
import logging
from datetime import datetime, timedelta

# Initialize the Flask app
app = Flask(__name__)

# Load the model
model = load_model('model.h5')

# Define class mapping
class_indices = {
    0: 'BA-cellulitis',
    1: 'BA-impetigo',
    2: 'FU-athlete-foot',
    3: 'FU-nail-fungus',
    4: 'FU-ringworm',
    5: 'PA-cutaneous-larva-migrans',
    6: 'VI-chickenpox',
    7: 'VI-shingles'
}
classes = list(class_indices.values())
image_size = 150

UPLOAD_FOLDER = './uploads'
os.makedirs(UPLOAD_FOLDER, exist_ok=True)
app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER

# History file
HISTORY_FILE = 'prediction_history.json'
lock = Lock()  # Thread safety for file access

# Cloud Storage bucket configuration
BUCKET_NAME = 'imagepredictdermaone'  # Replace with your bucket name
storage_client = storage.Client()

# Ensure history file exists
if not os.path.exists(HISTORY_FILE):
    with open(HISTORY_FILE, 'w') as f:
        json.dump([], f)

def generate_signed_url(bucket_name, blob_name):
    """Generate a signed URL for a GCS object."""
    bucket = storage_client.bucket(bucket_name)
    blob = bucket.blob(blob_name)

    # Generate a signed URL valid for 1 hour
    url = blob.generate_signed_url(
        version="v4",
        expiration=datetime.utcnow() + timedelta(hours=1),
        method="GET"
    )
    return url

def upload_to_gcs(file_path, file_name):
    """Upload a file to Google Cloud Storage."""
    try:
        bucket = storage_client.bucket(BUCKET_NAME)
        blob = bucket.blob(file_name)
        blob.upload_from_filename(file_path)
        
        # Generate a signed URL instead of making the file public
        public_url = generate_signed_url(BUCKET_NAME, file_name)
        return public_url
    except Exception as e:
        logging.error(f"Failed to upload to GCS: {str(e)}")
        raise

def save_prediction_to_history(prediction):
    """Save prediction results to history file."""
    with lock:
        history = []
        if os.path.exists(HISTORY_FILE):
            with open(HISTORY_FILE, 'r') as file:
                history = json.load(file)
        history.append(prediction)
        with open(HISTORY_FILE, 'w') as file:
            json.dump(history, file, indent=4)

@app.route('/history', methods=['GET'])
def get_history():
    """Retrieve the prediction history."""
    if os.path.exists(HISTORY_FILE):
        with open(HISTORY_FILE, 'r') as file:
            history = json.load(file)
        return jsonify(history), 200
    return jsonify({'message': 'No history found'}), 200

@app.route('/predict', methods=['POST'])
def predict():
    if 'image' not in request.files:
        return jsonify({"error": "No image file provided"}), 400

    # Get the image from the request
    file = request.files['image']
    file_name = f"{uuid.uuid4()}_{file.filename}"  # Unique file name
    file_path = os.path.join(app.config['UPLOAD_FOLDER'], file_name)

    try:
        # Save the uploaded file locally
        file.save(file_path)

        # Preprocess the image
        image = load_img(file_path, target_size=(image_size, image_size))
        x = img_to_array(image)
        x /= 255.0
        x = np.expand_dims(x, axis=0)

        # Predict
        predictions = model.predict(x)
        category_index = np.array(predictions[0]).argmax()
        label = class_indices[category_index]
        confidence = float(predictions[0][category_index]) * 100  # Convert to percentage

        # Upload to GCS
        public_url = upload_to_gcs(file_path, file_name)

        # Save to history file
        result = {
            "namaFile": file.filename,
            "imageUrl": public_url,  # URL of the uploaded image
            "label": label,
            "confidence": f"{confidence:.2f}%"
        }
        save_prediction_to_history(result)

        # Clean up local file
        os.remove(file_path)

        return jsonify(result)
    except Exception as e:
        # Clean up temporary file in case of an error
        if os.path.exists(file_path):
            os.remove(file_path)
        logging.error(f"Error in prediction: {str(e)}")
        return jsonify({"error": str(e)}), 500

# Health check endpoint
@app.route('/', methods=['GET'])
def health_check():
    return "Model is running!"

if __name__ == "__main__":
    logging.basicConfig(level=logging.INFO)
    app.run(host='0.0.0.0', port=8080)
