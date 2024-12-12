# API Skin Disease Prediction

This repository provides a Flask-based API for skin disease prediction using a TensorFlow model. It supports image upload for disease classification and integrates with Google Cloud Storage for file management.

## Features
- Predicts skin diseases using a pre-trained TensorFlow model (`model.h5`).
- Stores prediction results in a JSON file (`prediction_history.json`).
- Uploads processed images to Google Cloud Storage and generates signed URLs.
- Provides history of predictions through an endpoint.
- Dockerized for ease of deployment.

## Endpoints
### 1. **Health Check**
- **URL:** `/`
- **Method:** `GET`
- **Response:**
  - `200 OK`: Returns `"Model is running!"` if the application is active.

### 2. **Prediction**
- **URL:** `/predict`
- **Method:** `POST`
- **Parameters:**
  - `image`: Image file to be analyzed (form-data).
- **Response:**
  ```json
  {
    "namaFile": "uploaded_image.jpg",
    "imageUrl": "signed_url",
    "label": "Predicted Disease",
    "confidence": "Confidence Percentage"
  }
  ```
- **Error Codes:**
  - `400`: No image file provided.
  - `500`: Internal server error during prediction.

### 3. **History**
- **URL:** `/history`
- **Method:** `GET`
- **Response:**
  - Returns a JSON array of all previous predictions.

## File Structure
```plaintext
.
├── main.py                  # Main Flask application
├── Dockerfile               # Dockerfile for containerization
├── requirements.txt         # Python dependencies
├── model.h5                 # Pre-trained TensorFlow model
├── prediction_history.json  # Stores prediction history
├── serviceAccount.json      # Google Cloud credentials
└── uploads/                 # Temporary directory for uploaded files
```

## Setup Instructions

### 1. Prerequisites
- Python 3.9 or higher
- Docker
- Google Cloud credentials (`serviceAccount.json`)
- download model.h5 and put it in the same folder as main.py
    ```bash
    https://drive.google.com/file/d/1zpxIiWSmYCYBXzMJJtsU9oFvcUfBOe0E/view
    ```

### 2. Install Dependencies
```bash
pip install -r requirements.txt
```

### 3. Run Locally
```bash
python main.py
```
- The application will be accessible at `http://localhost:8080`.

### 4. Run with Docker
#### Build the Docker Image
```bash
docker build -t skin-disease-api .
```
#### Run the Container
```bash
docker run -p 8080:8080 -e PORT=8080 -v /path/to/serviceAccount.json:/app/serviceAccount.json skin-disease-api
```

## Environment Variables
- `GOOGLE_APPLICATION_CREDENTIALS`: Path to Google Cloud credentials JSON file.
- `PORT`: Port to run the application (default: 8080).

## Deployment on Google Cloud Run
1. Build and push the Docker image to a container registry.
2. Deploy the container to Cloud Run.
3. Set environment variables and permissions for the Cloud Storage bucket.

## Technologies Used
- **Flask**: Web framework.
- **TensorFlow**: Model loading and prediction.
- **Google Cloud Storage**: Image hosting.
- **Gunicorn**: Production-grade WSGI server.

## Author
Developed by DermaOne Team.
