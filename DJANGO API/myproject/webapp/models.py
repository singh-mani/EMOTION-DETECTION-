from django.db import models

# Create your models here.
class employee(models.base.Model):
    firstname=models.CharField(max_length=10)
    lastname=models.CharField(max_length=10)
    emp_id=models.IntegerField()

    def __str__(self):
        return self.firstname

class img(models.base.Model):
    IMG_URL=models.CharField(max_length=100)


    def __str__(self):
        return self.IMG_URL
