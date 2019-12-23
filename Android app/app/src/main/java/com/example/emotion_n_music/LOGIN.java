package com.example.emotion_n_music;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;




public class LOGIN extends AppCompatActivity {



    private void open_Register_Activity()
    {
        Intent intent=new Intent(this,REGISTER.class);
        startActivity(intent);
    }

    private void open_Main_Activity()
    {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    @Override



    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        Button bb=findViewById(R.id.REGISTER);
        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_Register_Activity();
            }
        });





        Button button=findViewById(R.id.GO);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView ii=findViewById(R.id.ID);
                EditText kk=findViewById(R.id.KEY);

                String check_id=ii.getText().toString().trim();
                String check_key=kk.getText().toString().trim();
                verify(check_id,check_key);
            }

            void verify(String check_id,String check_key)
            {
                String[] projection = {

                        MovieContract.MovieEntry.COLUMN_ID,
                        MovieContract.MovieEntry.COLUMN_KEY,
                };

                // Perform a query on the provider using the ContentResolver.
                // Use the {@link PetEntry#CONTENT_URI} to access the pet data.
                Cursor cursor = getContentResolver().query(
                        MovieContract.MovieEntry.CONTENT_URI,   // The content URI of the words table
                        projection,             // The columns to return for each row
                        null,                   // Selection criteria
                        null,                   // Selection criteria
                        null);                  // The sort order for the returned rows

                TextView displayView = (TextView) findViewById(R.id.INCORRECT);

                try {

                    int nameColumnIndex = cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_ID);
                    int breedColumnIndex = cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_KEY);
                    boolean found=false;
                    // Iterate through all the returned rows in the cursor
                    while (cursor.moveToNext()) {
                        // Use that index to extract the String or Int value of the word
                        // at the current row the cursor is on.

                        String currentName = cursor.getString(nameColumnIndex);
                        String currentBreed = cursor.getString(breedColumnIndex);

                        if (currentName.equals(check_id) && currentBreed.equals(check_key)) {
                            found=true;
                            open_Main_Activity();
                            break;
                        }
                    }
                    if(!found)displayView.setText("SORRY NO MATCH \n ");

                } finally {
                    // Always close the cursor when you're done reading from it. This releases all its
                    // resources and makes it invalid.
                    cursor.close();
                }



            }


        });
    }
}
