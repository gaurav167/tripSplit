# -*- coding: utf-8 -*-
# Generated by Django 1.11 on 2018-02-13 00:58
from __future__ import unicode_literals

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('group', '0001_initial'),
    ]

    operations = [
        migrations.DeleteModel(
            name='Transaction',
        ),
    ]