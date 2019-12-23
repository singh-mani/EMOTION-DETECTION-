from django.shortcuts import render

# Create your views here.

from django.http import HttpRequest
from django.http import HttpResponse
from django.shortcuts import get_object_or_404
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status
from . models import employee
from . models import img
from . serializers import employeeSerializer
from . serializers import imgSerializer
import json as simplejson
from . software_output import outputfunction as of

class employeeList(APIView):

    def get(self , request):
        employee1=employee.objects.all()
        serializer = employeeSerializer(employee1,many = True)
        return Response(serializer.data)

    def post(self , request):
        # print(dict(request.POST.lists()))

        serializer = employeeSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class imgList(APIView):

    def get(self , request):
        img1=img.objects.all()
        serializer = imgSerializer(img1,many = True)
        return Response(serializer.data)

    def post(self , request):
        # print(dict(request.POST.lists()))

        serializer = imgSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            id=serializer.data['IMG_URL']
            print(id)
            out=of(id)
            print(out)
            return Response({"IMG_URL": "\""+out+"\""}, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
