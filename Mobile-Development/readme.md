# Skin Disease Detection App

A mobile application for detecting skin diseases using machine learning. The app also provides health-related news, stores prediction history, and offers user-friendly features like login, signup, and profile management.

## Features

### 1. Skin Disease Detection
- Upload an image from the gallery.
- Predict skin disease using a machine learning model integrated via cloud computing.

### 2. Health Information
- Fetch and display health-related news using the NewsAPI.

### 3. Prediction History
- View a history of previous skin disease predictions.

### 4. Authentication
- Login and Sign-up with email and password.
- Google OAuth integration for quick sign-in.

### 5. Homepage with Navigation
- **Dashboard**: Access key features.
- **Health Information**: Stay updated with health-related articles.
- **History**: Review past predictions.
- **Profile**: Manage user information and settings.

## Architecture

The app follows the MVVM (Model-View-ViewModel) architecture for clean, maintainable, and testable code.

### Components:
- **ViewModel & LiveData**: Manage UI-related data and handle changes.
- **Repository**: Abstract data sources (API, local database).
- **Retrofit**: Simplify API calls.

## Technologies Used

### Language:
- Kotlin

### Dependencies:
- **Retrofit**: For API communication.
- **Glide**: For image loading and caching.
- **AndroidX Core**: For backward compatibility and modern Android development.
- **Material Design**: For building a modern and intuitive UI.
- **Google Play Services Auth**: For integrating Google OAuth.

### Unit Testing:
- Comprehensive unit tests to ensure the app's reliability.

### Android Testing:
- Android-specific testing tools to validate app performance and user interaction.

---

Feel free to contribute to the development of this application by submitting issues, feature requests, or pull requests. Together, we can make healthcare more accessible and efficient!
