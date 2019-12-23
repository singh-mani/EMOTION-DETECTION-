package com.example.emotion_n_music;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private void open_PLAYLIST_ACTIVITY()
    {
        Intent intent=new Intent(this,PLAYLIST.class);
        startActivity(intent);
    }


    private void open_POST_ACTIVITY(String name_of_img)
    {
        Log.e("** name_of__img**",name_of_img+"  NO NO   -- MAIN OPENPOST() ");

        Intent intent=new Intent(this,POST_ACTIVITY.class);
        intent.putExtra("iii", name_of_img);

        startActivity(intent);
    }
    private static final int  RESULT_LOAD_IMAGE = 1;
    public static final String SERVER_ADDRESS = "https://manindersandhu.000webhostapp.com/";
    public String IMG_NAME;
    ImageView imageToUpload;
    Button bUploadImage;
    EditText uploadImageName;
    Button bSelectImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageToUpload=(ImageView) findViewById(R.id.imageToUpload);
        bUploadImage=(Button) findViewById(R.id.bUploadImage);
        uploadImageName=(EditText) findViewById((R.id.etUploadName));
        bSelectImage=(Button) findViewById(R.id.bSelectImage);


        imageToUpload.setOnClickListener(this);
        bUploadImage.setOnClickListener(this);
        bSelectImage.setOnClickListener(this);
        uploadImageName.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data!=null){


            Uri selectedImage=data.getData();
            imageToUpload.setImageURI(selectedImage);

        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.bSelectImage:
                Intent galleryIntent = new Intent (Intent.ACTION_PICK , MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                Log.i("****IMAGE", "  111 1111  ********** **** URL IS     ***********************************");

                startActivityForResult(galleryIntent , RESULT_LOAD_IMAGE);
                break;


            case R.id.bUploadImage:
                IMG_NAME=uploadImageName.getText().toString();

                Bitmap image = ((BitmapDrawable) imageToUpload.getDrawable()).getBitmap();


                Log.d("*****IMAGE", "  111 1111  ********** **** upload ImAGE     ***********************************");

                new UploadImage(image ,IMG_NAME).execute();

                //open_PLAYLIST_ACTIVITY();
                break;



        }



    }

    private class UploadImage extends AsyncTask<Void ,Void,Void>{

        Bitmap image;
        String name;

        public UploadImage(Bitmap image , String name) {
            this.image=image;
            this.name=name;
        }


        @Override
        protected Void doInBackground(Void... voids) {

            ByteArrayOutputStream byteArrayOutputStream =new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG , 100 ,byteArrayOutputStream);
            String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray() , Base64.DEFAULT);

            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("image" , encodedImage));
            dataToSend.add(new BasicNameValuePair("name" , name));


            HttpParams httpRequestParams = getHttpRequestParams();
            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost( SERVER_ADDRESS + "SavePicture.php");



            try{
                Log.d("  *****  FOUNDDDD ", " 111 1111 -- MAIN  ********** **** URL IS  "+SERVER_ADDRESS+"     ***********************************");

                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                client.execute(post);

                Log.d("  *****  FOUNDDDD ", "  222 2222  -- MAIN ********** **** URL IS  "+SERVER_ADDRESS+"     ***********************************");
            }catch (Exception e)
            {

                e.printStackTrace();

                Toast.makeText(getApplicationContext() , " ERROR SHOWN " , Toast.LENGTH_SHORT).show();
            }




            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(getApplicationContext() , "IMAGE UPLOADED " , Toast.LENGTH_LONG).show();

            open_POST_ACTIVITY(IMG_NAME);


        }

    }
    private  HttpParams getHttpRequestParams(){

        HttpParams httpRequestParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpRequestParams , 1000 * 80);
        HttpConnectionParams.setSoTimeout(httpRequestParams , 1000*80);
        return httpRequestParams;
    }
}
