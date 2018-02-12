from django.core import serializers
from . import models
# from user.models import User
# from transaction.models import Transaction
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


def transact(request):
	if request.method == "POST":
		g_id = request.POST.get('g_id')
		log = request.POST.get('log')

		try:
			grp = Group.objects.get(id=g_id)
		except:
			return JsonResponse({'error':'Group id invalid.'})
		else:
			pos = log.rfind(',')
			if pos > log.rfind(':'):
				log = log[:pos] + log[pos + 1:]
			log = log.replace("'", "\"")
			trs = Transction(grp,log)
			trs.save()
			trs.trans.add(grp)
			return JsonResponse({'error':'success', 'transaction_id':trs.id})
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
		return JsonResponse({'error':'Only available via POST.','status_code':'400'})
