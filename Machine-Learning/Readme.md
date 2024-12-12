# MACHINE LEARNING

This project focuses on classifying skin diseases using machine learning techniques. The implementation is done in a Jupyter Notebook and can be executed on Google Colab. The dataset required for this project is stored in a shared Google Drive folder.

## Features

- **Dataset**: A curated dataset of skin disease images available on [Google Drive](https://drive.google.com/drive/folders/1f_yzkXmHvsLkh33qwSiwk8Ec4Hhs8_KI?usp=sharing).
- **Platform**: Executable on Google Colab for easy accessibility and resource optimization.
- **Classification Model**: Machine learning models to identify and classify various skin diseases.

## Prerequisites

To run this project, you need the following:

- A Google account
- Basic knowledge of Python and machine learning
- Access to Google Colab

## Steps to Execute the Notebook

1. **Open the Notebook in Google Colab**:
   - Upload the file `Skin-Diseases.ipynb` to your Google Drive.
   - Open the notebook in Google Colab.

2. **Connect Google Drive**:
   - Mount your Google Drive to access the dataset by running the following code in a Colab cell:

     ```python
     from google.colab import drive
     drive.mount('/content/drive')
     ```

3. **Set Dataset Path**:
   - Update the dataset path in the notebook to point to the shared folder:

     ```python
     dataset_path = "/content/drive/MyDrive/Skin-Disease-Dataset"
     ```

4. **Install Dependencies**:
   - Install all required libraries by running:

     ```python
     !pip install -r requirements.txt
     ```

5. **Run the Notebook**:
   - Execute the cells sequentially to train and evaluate the model.

## Dataset Details

- **Source**: [Google Drive](https://drive.google.com/drive/folders/1f_yzkXmHvsLkh33qwSiwk8Ec4Hhs8_KI?usp=sharing)
- **Structure**:
  - The dataset contains labeled images of various skin conditions.
  - Make sure to organize the dataset in folders corresponding to each label.

## Model Architecture

The model uses convolutional neural networks (CNNs) to classify skin diseases. The architecture details are provided in the notebook.

## Results

- Evaluation metrics such as accuracy, precision, recall, and F1-score are calculated to measure model performance.
- A confusion matrix is used for visualizing classification results.

## Troubleshooting

- Ensure that the dataset path is correctly set in the notebook.
- Check your internet connection when using Colab.
- Update the dependencies if there are version conflicts.
