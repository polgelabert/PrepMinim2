package app.movie.pol.com.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import app.movie.pol.com.R;


public class MainActivity extends AppCompatActivity{


    Button cerca;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView titol2 = (TextView) findViewById(R.id.description);
        final EditText titol = (EditText) findViewById(R.id.movieTitle);

        cerca = (Button) findViewById(R.id.cerca);


        cerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, MovieDisplayList.class);
                intent.putExtra("title", titol.getText().toString());
                startActivity(intent);
                //setResult(RESULT_OK, intent);               // Result_ok = -1
                //finish();
            }
        });
    }





}
