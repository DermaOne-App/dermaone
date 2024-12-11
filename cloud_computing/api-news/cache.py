from flask import request, jsonify, make_response
import time

# Simple in-memory cache
cache = {}

def cache_middleware(func):
    def wrapper(*args, **kwargs):
        cache_key = request.full_path

        # Check if response exists in the cache
        if cache_key in cache:
            # Check cache expiration (e.g., 5 minutes)
            if time.time() - cache[cache_key]['timestamp'] < 300:
                cached_response = cache[cache_key]['data']
                return make_response(cached_response, 200)

        # Call the original function
        response = func(*args, **kwargs)

        # Ensure response is a valid Flask Response
        if isinstance(response, tuple):
            body, status = response
            flask_response = make_response(body, status)
        else:
            flask_response = response

        # Cache the response if status is 200
        if flask_response.status_code == 200:
            cache[cache_key] = {
                'data': flask_response.get_json(),
                'timestamp': time.time(),
            }

        return flask_response

    wrapper.__name__ = func.__name__
    return wrapper
