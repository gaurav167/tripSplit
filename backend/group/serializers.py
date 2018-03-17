from rest_framework import serializers
from group.models import Group

class GroupSerializer(serializers.ModelSerializer):
	class Meta:
		model = Group
		fields = ('id', 'name', 'create_date','users')

	def create(self, validated_data):
		"""
		Create and return a new `Group` instance, given the validated data.
		"""
		return Group.objects.create(**validated_data)

	def update(self, instance, validated_data):
		"""
		Update and return an existing `Snippet` instance, given the validated data.
		"""
		instance.name = validated_data.get('name', instance.name)
		u_ids = validated_data.get('users', [])
		for u_id in u_ids:
			try:
				usr = User.objects.get(id=u_id)
				instance.users.add(usr)
			except:
				pass
		instance.save()
		return instance