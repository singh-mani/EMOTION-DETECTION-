package com.example.emotion_n_music;

import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PLAYLIST extends AppCompatActivity {

    private TextView mResultView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playlist);
        mResultView=findViewById(R.id.resultView);

        OkHttpClient client = new OkHttpClient();
        String url="http://192.168.43.207:8000/img/";

        Request request=new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if(response.isSuccessful())
                {

                    final String myResponse=response.body().string();

                    PLAYLIST.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                        mResultView.setText(myResponse);
                        }
                    });



                }
            }
        });

    }





}

