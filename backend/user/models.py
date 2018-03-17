from django.db import models

class User(models.Model):
	ph_no = models.CharField(max_length=10)
	address = models.TextField()
	name = models.CharField(max_length=20)
	friends = models.ManyToManyField('User')

	def __str__(self):
		return self.name