package com.example.emotion_n_music;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class REGISTER extends AppCompatActivity {

    private void open_Main_Activity()
    {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        Button register=findViewById(R.id.REGISTER);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                insertMOVIE();
            }

            private void insertMOVIE() {
                TextView ID=findViewById(R.id.register_id);


                EditText KEY1=findViewById(R.id.register_pwd1);
                EditText KEY2=findViewById(R.id.register_pwd2);
                // Read from input fields
                // Use trim to eliminate leading or trailing white space
                String nameString = ID.getText().toString().trim();
                String pwd1 = KEY1.getText().toString().trim();
                String pwd2 = KEY2.getText().toString().trim();

                if(!pwd1.equals(pwd2)){
                    Log.e("** 8** **  PWD1 == ",pwd1);
                    Log.e("** 8** **  PWD2 == ",pwd2);

                    TextView rr=findViewById(R.id.register_result);
                    rr.setText(" PASSWORDS DONT MATCH");
                }

                else {
                    ContentValues values = new ContentValues();
                    values.put("ID", nameString);
                    values.put("KEYY", pwd1);


                    // Insert a new pet into the provider, returning the content URI for the new pet.
                    Uri newUri = getContentResolver().insert(MovieContract.MovieEntry.CONTENT_URI, values);

                    // Show a toast message depending on whether or not the insertion was successful
                    if (newUri == null) {

                        Toast tt = Toast.makeText(getApplicationContext(), " FAILED ", Toast.LENGTH_SHORT);
                        tt.show();
                    } else {
                        Toast tt = Toast.makeText(getApplicationContext(), " SUCCESS ", Toast.LENGTH_SHORT);
                        tt.show();

                        open_Main_Activity();
                    }
                }
            }



        });
    }


}
