from django.urls import path

from app import views
from app.settings import LOGIN_URL

urlpatterns = [
    path("", views.home_page, name="home_page"),
    path(LOGIN_URL, views.login, name="login"),
    path("csv_form", views.csv_form, name="csv_form"),
]
