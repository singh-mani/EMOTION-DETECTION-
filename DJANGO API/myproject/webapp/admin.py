from django.contrib import admin
from . models import employee
from . models import file
# Register your models here.

admin.site.register(employee)
admin.site.register(file)