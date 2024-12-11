from flask import Blueprint
from controllers.news_controller import fetch_news
from cache import cache_middleware

# Create a Blueprint
news_blueprint = Blueprint('news', __name__)

# Route to fetch news with caching middleware
@news_blueprint.route('/', methods=['GET'])
@cache_middleware
def get_news():
    return fetch_news()
