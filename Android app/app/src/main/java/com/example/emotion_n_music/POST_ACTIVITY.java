package com.example.emotion_n_music;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class POST_ACTIVITY extends AppCompatActivity {






    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    TextView mResultView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playlist);
         mResultView=(TextView)findViewById(R.id.resultView);


        String img_name = getIntent().getStringExtra("iii");
        String temp=MainActivity.SERVER_ADDRESS+"pictures/"+img_name+".JPG";
        String postUrl= "http://192.168.43.207:8000/img/";
        String postBody="{\n" +
                "    \"IMG_URL\": \""+temp+"\"\n"+
                "}";


        try {
            mResultView.setText(postBody);
            Log.d("***  POST ***", postBody);
            postRequest(postUrl,postBody);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    void postRequest(String postUrl,String postBody) throws IOException {

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON, postBody);

        Request request = new Request.Builder()
                .url(postUrl)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }


            @Override
            public void onResponse(Call call, Response response) throws IOException {



                final String myResponse=response.body().string();

                Log.d("***** TAG *POST**",myResponse);

                POST_ACTIVITY.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mResultView.setText(myResponse);
                    }
                });

            }
        });
    }
}
