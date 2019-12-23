def outputfunction(urll):
    import cv2
    import numpy as np
    import os
    from keras.models import model_from_json
    from keras.preprocessing import image
    import urllib.request

    urllib.request.urlretrieve(urll, "local-filename.jpg")

    pp=os.getcwd()+"\webapp\jjson.json"
    ph=os.getcwd()+"\webapp\\fer.h5"
    pi=os.getcwd()+"\\local-filename.jpg"
    px=os.getcwd()+"\webapp\\haarcascade_frontalface_default.xml"
    print("\n\n\n\n\n\n\n\n")
    print(pp)
    print(ph)
    print(pi)
    print(px)
    model = model_from_json(open(pp, "r").read())
    model.load_weights(ph)

    model.summary()

    face_cascade = cv2.CascadeClassifier(px)
    img = cv2.imread(pi,1)
    grayimg = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
    faces = face_cascade.detectMultiScale(grayimg,scaleFactor=1.15,minNeighbors=5)

    emotions = ('angry', 'disgust', 'fear', 'happy', 'sad', 'surprise', 'neutral')
    predicted_emotion="NO_FACE_FOUND"
    for x,y,w,h in faces:
        faceimg=grayimg[y:y+h,x:x+w]
        faceimg = cv2.resize(faceimg,(48,48))
        img_pixels = image.img_to_array(faceimg)
        img_pixels = np.expand_dims(img_pixels, axis = 0)
        img_pixels /= 255
        predictions = model.predict(img_pixels)
        max_index = np.argmax(predictions[0])
        predicted_emotion = emotions[max_index]
    return predicted_emotion


        #img = cv2.rectangle(img,(x,y),(x+w,y+h),(0,255,0),2)




        #cv2.putText(img, predicted_emotion, (int(x), int(y)), cv2.FONT_HERSHEY_SIMPLEX, 1, (0,0,255), 2)
    #resized_img = cv2.resize(img, (600, 700))
    #cv2.imshow("manas:",resized_img)
    #cv2.imwrite("manas_updated.jpg",resized_img)
    #cv2.waitKey(0)
    #cv2.destroyAllWindows()
