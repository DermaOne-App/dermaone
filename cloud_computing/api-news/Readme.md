# API News Service

This project is a simple Flask-based API for news services, containerized using Docker. Below is a guide to understanding the application's structure, dependencies, and how to run it locally or using Docker.

---

## Features
- Built with Flask for web service capabilities.
- Modular design using Blueprints for route organization.
- Includes error handling for internal server errors (HTTP 500).
- Configurable using environment variables with `.env` support.
- Dockerized for seamless deployment.
- Integrates SerpAPI for fetching news data.
- Supports integration with Google Cloud Platform (GCP).

---

## Prerequisites

### Local Setup
- Python 3.10+
- pip (Python package manager)
- Virtual environment tool (recommended)

### Docker Setup
- Docker installed on your system

### Google Cloud Platform Setup
- A Google Cloud account
- Google Cloud SDK installed and authenticated
- Enable the necessary APIs on GCP (e.g., Compute Engine, Cloud Run, or App Engine).

---

## Installation

### 1. Clone the Repository
```bash
# Clone the repository 
git clone <repo-url>
cd api-news
```

### 2. Install Dependencies (Local Setup)
```bash
# Create a virtual environment (optional but recommended)
python -m venv venv
source venv/bin/activate  # On Windows: venv\Scripts\activate

# Install dependencies
pip install -r requirements.txt
```

---

## Running the Application

### 1. Local Environment
1. Create a `.env` file with the required environment variables.
   Example `.env` file:
   ```env
   PORT=5000
   SERPAPI_KEY=your_serpapi_key
   ```
2. Run the application:
   ```bash
   python app.py
   ```
3. Access the application at `http://localhost:5000` (default port).

### 2. Using Docker
1. Build the Docker image:
   ```bash
   docker build -t api-news .
   ```
2. Run the container:
   ```bash
   docker run -p 5000:5000 api-news
   ```
3. Access the application at `http://localhost:5000`.

### 3. Deploying to Google Cloud Platform
#### Using Cloud Run:
1. Authenticate with Google Cloud:
   ```bash
   gcloud auth login
   gcloud config set project [PROJECT_ID]
   ```
2. Build and push the Docker image to Google Container Registry (GCR):
   ```bash
   gcloud builds submit --tag gcr.io/[PROJECT_ID]/api-news
   ```
3. Deploy the application:
   ```bash
   gcloud run deploy api-news \
       --image gcr.io/[PROJECT_ID]/api-news \
       --platform managed \
       --region [REGION] \
       --allow-unauthenticated
   ```
4. Access the deployed service URL provided by GCP.

---

## Project Structure
```
api-news/
├── app.py                 # Main Flask application
├── Dockerfile             # Docker configuration file
├── requirements.txt       # Python dependencies
├── .env                   # Environment variable file
├── controllers/           # Directory for controller logic
│   └── news_controller.py # Example: Handles news-related logic
├── routes/                # Directory for route definitions
│   └── news_routes.py     # Example: Blueprint for news routes
├── .dockerignore          # Files to ignore in Docker builds
├── .gitignore             # Files to ignore in Git commits
```

---

## Dockerfile Overview
```dockerfile
# Use a lightweight Python base image
FROM python:3.10-slim

# Set the working directory
WORKDIR /app

# Copy all project files
COPY . /app

# Install dependencies
RUN pip install --no-cache-dir -r requirements.txt

# Expose the port used by Flask (5000)
EXPOSE 5000

# Command to run the Flask application
CMD ["python", "app.py"]
```

---

## Environment Variables
The application uses environment variables for configuration. These can be stored in a `.env` file.

| Variable          | Description                   | Default |
|-------------------|-------------------------------|---------|
| `PORT`            | Port for the server           | `5000`  |
| `SERPAPI_KEY`     | API key for accessing SerpAPI | None    |

---

## Error Handling
The application includes a handler for HTTP 500 errors, returning a structured JSON response:
```json
{
  "error": "Internal Server Error"
}
```

---

## License
This project is licensed under the MIT License. See the LICENSE file for details.
