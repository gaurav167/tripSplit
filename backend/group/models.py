from django.db import models
from django.utils import timezone

# from user.models import User

class Group(models.Model):
	name = models.CharField(max_length=20)
	create_date = models.DateTimeField(default=timezone.now)
	users = models.ManyToManyField('User')
	# trans = models.ForeignKey('Transaction', on_delete=models.CASCADE)

	def __str__(self):
		return self.name

class User(models.Model):
	pass