from rest_framework import serializers
from . models import employee
from . models import img
from . models import file
class employeeSerializer(serializers.ModelSerializer):

    class Meta:
        model=employee
        fields='__all__'


class imgSerializer(serializers.ModelSerializer):

    class Meta:
        model=img
        fields='__all__'

class fileSerializer(serializers.ModelSerializer):

    class Meta:
        model=file
        fields='__all__'