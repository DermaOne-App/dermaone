# Gunakan base image Python
FROM python:3.10-slim

# Tentukan working directory
WORKDIR /app

# Copy semua file ke dalam container
COPY . /app

# Install dependencies Python
RUN pip install --no-cache-dir -r requirements.txt

# Expose port Flask (3000)
EXPOSE 5000

# Jalankan aplikasi Flask
CMD ["python", "app.py"]
