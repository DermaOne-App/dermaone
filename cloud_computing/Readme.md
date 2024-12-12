# Cloud Computing Implementation Documentation

This documentation provides an overview of the cloud computing solutions implemented for API services, including API News Service and API Skin Disease Prediction. The project leverages Google Cloud Platform (GCP) features like OAuth2 authentication, Cloud Run, billing monitoring, and more.

---

## Overview of Implementations

### **1. OAuth2 Integration**
We implemented OAuth2 for secure authentication and authorization across services. This ensures controlled access to APIs and resources while maintaining user privacy and data security.

#### Key Features:
- Secure authentication using industry standards.
- Token-based authorization for API endpoints.
- Seamless integration with GCP IAM policies.

#### Usage:
- Configure OAuth2 credentials in the Google Cloud Console.
- Use the credentials in applications for authentication and authorization workflows.

---

### **2. Performance Monitoring with Cloud Run**
Both API services are deployed using **Google Cloud Run**, ensuring scalability and high availability.

#### Features:
- Automatic scaling based on traffic.
- Real-time monitoring of performance metrics (latency, CPU/memory usage).
- Logs integration with Cloud Logging for debugging and analytics.

#### Implementation Steps:
1. Containerized applications using Docker.
2. Deployed to Cloud Run using `gcloud run deploy` command.
3. Configured monitoring dashboards in Google Cloud Console.

---

### **3. Billing Monitoring**
To manage cloud costs effectively, we set up billing alerts and budgets.

#### Features:
- Budget thresholds with email notifications.
- Real-time expenditure tracking in the Billing dashboard.
- Cost analysis per service and region.

#### Setup:
1. Created a budget in the **Billing section** of Google Cloud Console.
2. Set up notifications for threshold breaches (e.g., 50%, 75%, 90%).
3. Monitored usage trends using the **Cost Breakdown** tool.

---

## Individual API Service Descriptions

### **A. API Skin Disease Prediction**
A Flask-based API for skin disease prediction, utilizing a TensorFlow model and Google Cloud Storage for image handling.

#### Features:
- Image upload and prediction.
- Stores processed images in Google Cloud Storage.
- Generates signed URLs for secure image access.

#### Deployment Highlights:
- Integrated TensorFlow for model predictions.
- Secured image storage with Cloud Storage permissions.
- Real-time scaling with Cloud Run.

### **B. API News Service**
A Flask-based application providing news-related services integrated with SerpAPI. The service is containerized and deployed using Docker.

#### Features:
- Modular Flask application with Blueprints.
- Integrated with SerpAPI for fetching news data.
- Configured for deployment on Cloud Run.

#### Deployment Highlights:
- Dockerized application for easy deployment.
- Monitored using Cloud Run metrics.
- Authentication via OAuth2 for secure access.

---

## Key Benefits of Using GCP

1. **Scalability**: Applications automatically scale based on traffic, ensuring high availability.
2. **Cost Optimization**: Billing alerts and budgets prevent unexpected costs.
3. **Security**: OAuth2 and GCP IAM policies ensure secure access to resources.
4. **Ease of Deployment**: Cloud Run simplifies the process of deploying containerized applications.

---

## Future Enhancements
- Integration with Cloud Pub/Sub for asynchronous messaging.
- Implementation of automated CI/CD pipelines using Cloud Build.
- Enhanced AI/ML features for prediction services using Vertex AI.

---

## References
- [Google Cloud Run Documentation](https://cloud.google.com/run/docs)
- [Google OAuth2 Documentation](https://cloud.google.com/docs/authentication)
- [Billing Monitoring Setup](https://cloud.google.com/billing/docs/how-to/budgets)

---

This documentation serves as a guide to understanding the implemented cloud solutions and the steps taken to ensure reliability, scalability, and cost-efficiency.
