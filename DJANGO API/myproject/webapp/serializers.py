from rest_framework import serializers
from . models import employee
from . models import img
class employeeSerializer(serializers.ModelSerializer):

    class Meta:
        model=employee
        fields='__all__'


class imgSerializer(serializers.ModelSerializer):

    class Meta:
        model=img
        fields='__all__'
