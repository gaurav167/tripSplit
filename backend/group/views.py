from django.core import serializers
from django.http import JsonResponse

from . import models
from user.models import User
from transaction.models import Transaction
import json


def create(request):
	if request.method == "POST":
		# Take user_ids (list) from POST
		name = request.POST.get('name')

		grp = Group(name=name)
		grp.save()

		for u_id in u_ids:
			try:
				usr = User.objects.get(id=u_id)
				grp.users.add(usr)
			except:
				return JsonResponse({'error':'User id invalid.'})
			else:
				return JsonResponse({'error':'success', 'group_id':grp.id})

	else:
		return JsonResponse({'error':'Only available via POST.','status_code':'400'})


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
