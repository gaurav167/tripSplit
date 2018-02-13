from django.conf.urls import url
from . import views

urlpatterns = [
    url(r'create/', views.create, name='group_create'),
    url(r'transact/', views.transact, name='transact'),
    url(r'total/(?P<g_id>\d+)', views.total, name='total'),
    url(r'ghistory/(?P<g_id>\d+)', views.grp_history, name='grp_history'),
    url(r'uhistory/(?P<u_id>\d+)', views.usr_history, name='usr_history'),
]
