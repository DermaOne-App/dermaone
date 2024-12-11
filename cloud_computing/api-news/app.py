from flask import Flask
from routes.news_routes import news_blueprint
from dotenv import load_dotenv
import os

# Load environment variables
load_dotenv()

# Initialize Flask app
app = Flask(__name__)

# Get the port from environment variable (Cloud Run will set this)
port = int(os.environ.get('PORT', 5000))

# Register Blueprints
app.register_blueprint(news_blueprint, url_prefix='/news')

# Error handler
@app.errorhandler(500)
def internal_server_error(error):
    return {"error": "Internal Server Error"}, 500

# Run the app
if __name__ == '__main__':
    # Use 0.0.0.0 as host to make it accessible externally
    app.run(debug=True, host='0.0.0.0', port=port)
