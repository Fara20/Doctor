package com.example.farahreza.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class HealthTipsServices extends AppCompatActivity {
 CardView hair,skin,firstaid,fitness;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_tips_services);

        hair=findViewById(R.id.hair);
        skin=findViewById(R.id.skintips);
        firstaid=findViewById(R.id.firstaid);
        fitness=findViewById(R.id.fitness);

        hair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),HairtipsPage.class);
                startActivity(i);
            }
        });

        skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent j = new Intent(getApplicationContext(),SkintipsPage.class);
                startActivity(j);
            }
        });


        firstaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent k = new Intent(getApplicationContext(),FirstaidPage.class);
                startActivity(k);
            }
        });



        fitness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent k = new Intent(getApplicationContext(),FitnessPage.class);
                startActivity(k);
            }
        });

    }
}
