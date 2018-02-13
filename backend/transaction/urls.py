from django.conf.urls import url
from . import views

urlpatterns = [
    url(r'', views.transact, name='transaction'),
    url(r'upload/', views.upload, name='upload'),
]