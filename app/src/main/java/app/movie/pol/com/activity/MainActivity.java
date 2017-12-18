package app.movie.pol.com.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import app.movie.pol.com.R;


public class MainActivity extends AppCompatActivity{


    Button cerca;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final EditText titol = (EditText) findViewById(R.id.movieTitle);
        cerca = (Button) findViewById(R.id.cerca);


        cerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    try {

                        if (TextUtils.isEmpty(titol.getText().toString())) {
                            Toast.makeText(MainActivity.this, "Vaja! Sembla que no he entès aquest títol...", Toast.LENGTH_LONG).show();
                            return;
                        }

                        intent = new Intent(MainActivity.this, MovieDisplayList.class);
                        intent.putExtra("title", titol.getText().toString());
                        startActivity(intent);

                    } catch(Exception e){
                        Toast.makeText(MainActivity.this, "Error desconegut", Toast.LENGTH_LONG).show();
                    }

            }
        });
    }





}
