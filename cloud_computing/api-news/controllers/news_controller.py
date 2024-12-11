import os
import requests
from flask import request, jsonify

def fetch_news():
    query = request.args.get('q')
    page = request.args.get('page', 1, type=int)

    # Validate query parameter
    if not query or query.strip() == '':
        return jsonify({"error": "Query parameter 'q' is required"}), 400

    try:
        # Call SerpAPI
        response = requests.get(
            'https://serpapi.com/search',
            params={
                'q': query,
                'api_key': os.getenv('SERPAPI_KEY'),
                'gl': 'id',
                'start': (page - 1) * 10  # Offset for pagination
            }
        )
        response.raise_for_status()

        # Return JSON response and status code
        return jsonify(response.json()), 200
    except requests.exceptions.HTTPError as http_err:
        error_message = http_err.response.json().get('error', 'Unknown error')
        return jsonify({"error": f"API error: {error_message}"}), http_err.response.status_code
    except requests.exceptions.RequestException:
        return jsonify({"error": "Failed to fetch data from API"}), 500
