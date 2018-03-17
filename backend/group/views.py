from django.core import serializers
from django.http import JsonResponse

from . import models
from user.models import User
from transaction.models import Transaction
import json

from rest_framework import status
from rest_framework.decorators import api_view
from rest_framework.response import Response
from group.models import Group
from group.serializers import GroupSerializer


@api_view(['GET'])
def all_groups(request):
	if request.method == "GET":
		groups = Group.objects.all()
		serializer = GroupSerializer(groups, many=True)
		return Response(serializer.data)


@api_view(['POST'])
def create(request):
	if request.method == "POST":
		# Take user_ids (list) from POST
		serializer = GroupSerializer(data=request.data)
		if serializer.is_valid():
			serializer.save()
			return Response(serializer.data, status=status.HTTP_201_CREATED)
		else:
			return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


@api_view(['GET','PUT','DELETE'])
def group_stat(request, pk):
	try:
		grp = Group.objects.get(pk=pk)
	except:
		return Response(status=status.HTTP_404_NOT_FOUND)

	if request.method == "GET":
		serializer = GroupSerializer(grp)
		return Response(serializer.data)

	elif request.method == "PUT":
		serializer = GroupSerializer(grp, data=request.data)
		if serializer.is_valid():
			serializer.save()
			return Response(serializer.data)
		return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

	elif request.method == 'DELETE':
		grp.delete()
		return Response(status=status.HTTP_204_NO_CONTENT)


def total(request,g_id):
	if request.method == "GET":
		g = Group.objects.get(id=g_id)
		trs = Transction.objects.filter(grp=g)
		if len(trs) == 0:
			return JsonResponse({'error':'No transaction'})
		total = 0
		urs = {}
		for tr in trs:
			log = json.loads(tr.log)
			for key,val in log.items():
				usr[key] += val
				total += val
		usr = json.dumps(usr)
		return JsonResponse({'status':'success','data':usr})

	else:
		return JsonResponse({'error':'Only available via POST.','status_code':'400'})


def grp_history(request,g_id):
	if request.method == "GET":
		g = Group.objects.get(id=g_id)
		trs = Transction.objects.filter(grp=g)
		if len(trs) == 0:
			return JsonResponse({'error':'No transaction'})
		if len(trs) > 10:
			trs = trs[:-10]

		data = serializers.serialize('json',trs)
		return JsonResponse(data, safe=False)

	else:
		return JsonResponse({'error':'Only available via POST.','status_code':'400'})


def usr_history(request,u_id):
	if request.method == "GET":
		trs = Transction.user.filter(id=u_id)
		if len(trs) == 0:
			return JsonResponse({'error':'No transaction'})
		if len(trs) > 10:
			trs = trs[:-10]

		data = serializers.serialize('json',trs)
		return JsonResponse(data, safe=False)

	else:
		return JsonResponse({'error':'Only available via GET.','status_code':'400'})
