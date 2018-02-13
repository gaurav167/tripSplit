from django.db import models
from django.utils import timezone

from user.models import User
from group.models import Group

class Transaction(models.Model):
	user = models.ForeignKey('user.User', on_delete=models.CASCADE)
	group = models.ForeignKey('group.Group', on_delete=models.CASCADE)
	log = models.TextField()

	def __str__(self):
		return self.id

class Image(models.Model):
	image = models.ImageField(upload_to='invoice/', height_field=None, width_field=None ,default='/invoice/pic.jpg')
