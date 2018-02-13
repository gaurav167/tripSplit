from django.http import JsonResponse
from . import models
from group.models import Group

from PIL import Image, ImageEnhance, ImageFilter
import pytesseract

import binascii
import StringIO
import re


def readimage(path):
    count = os.stat(path).st_size / 2
    with open(path, "rb") as f:
        return bytearray(f.read()) 

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


def upload(request):
	if request.method == 'POST':
		data = request.POST.get('img')
		u_id = request.POST.get('uid')
		g_id = request.POST.get('gid')
		buf = StringIO.StringIO()
		for line in data.splitlines():
		    line = line.strip().replace(" ", "")
		    if not line:
		        continue
		    bytes = binascii.unhexlify(line)
		    buf.write(bytes)

		path = str(g_id) + str(u_id) + ".jpg"
		with open(path, "wb") as f:
		    f.write(buf.getvalue())

		img = Image()
		img.image = path
		img.save()

		text = pytesseract.image_to_string(Image.open(path))

		texts = text.split('\n')
		items = {}
		for t in texts:
			m = re.search("\w", t.reverse())
			if m and m.start() == 0:
				price = t[m.start():]
				item = t[:m.start()]
				items[item] = price

		return JsonResponse(items)
		