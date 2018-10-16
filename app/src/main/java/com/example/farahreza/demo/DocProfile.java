package com.example.farahreza.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DocProfile extends AppCompatActivity {
    private EditText et2 , et3;
    private Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_profile);

        Intent i = getIntent();
        et2 = findViewById(R.id.editText2);
        et3 = findViewById(R.id.editText3);
        btn1 = findViewById(R.id.button);



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(getApplicationContext(),BookingConfirmation.class);
                if((et2.getText().toString()).equals(""))
                    Toast.makeText(getApplicationContext(),"please choose a date",Toast.LENGTH_LONG).show();
                else  if((et3.getText().toString()).equals(""))
                    Toast.makeText(getApplicationContext(),"please choose a time",Toast.LENGTH_LONG).show();
                else
                    startActivity(j);
            }
        });


    }
}