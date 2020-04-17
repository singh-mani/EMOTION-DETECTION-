from django.shortcuts import render

# Create your views here.

from django.http import HttpRequest
from django.http import HttpResponse
from django.shortcuts import get_object_or_404
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status
from rest_framework.parsers import FileUploadParser
from . models import employee
from . models import img
from . models import file
from . serializers import employeeSerializer
from . serializers import imgSerializer
from . serializers import fileSerializer
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
        print('\n\n\nhahaha\n\n\n')
        serializer = imgSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            id=serializer.data['IMG_URL']
            print(id)
            out=of(id)
            print(out)
            return Response({"IMG_URL": "\""+out+"\""}, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)



class fileList(APIView):
    print(" INSIDE FILELIST HAHSHS")
    def get(self , request):
        print(" 111  GETTTT     ")
        file1=file.objects.all()
        serializer = fileSerializer(file1,many = True)
        return Response(serializer.data)

    def post(self , request):
        print(" IT IS POST  !!!")
        # print(dict(request.POST.lists()))

        serializer = fileSerializer(data=request.data)
        if serializer.is_valid():
            #print(serializer)
            serializer.save()
            id=serializer.data['file']
            print(id)
            out=of(id)
            # serializer.data['emotion']=out
            print(out)

            return Response({'emotion':out}, status=status.HTTP_201_CREATED )
             
        else:
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
