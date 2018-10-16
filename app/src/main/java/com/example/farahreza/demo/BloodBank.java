package com.example.farahreza.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BloodBank extends AppCompatActivity {
    private Spinner spinner1;
    private Button submitbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_bank);
        Intent i = getIntent();
        addItemsOnSpinner2();
        addListenerOnButton();


    }

    public void addItemsOnSpinner2() {

        spinner1 = (Spinner) findViewById(R.id.spinbg);
        List<String> list = new ArrayList<String>();
        list.add("Select an Option");
        list.add("A+");
        list.add("B+");
        list.add("AB+");
        list.add("O+");
        list.add("A-");
        list.add("B-");
        list.add("AB-");
        list.add("O-");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);
    }

    public void addListenerOnButton() {

        spinner1 = (Spinner) findViewById(R.id.spinbg);
        submitbtn = (Button) findViewById(R.id.btnsubmit);

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),BloodFound.class);
                if(String.valueOf(spinner1.getSelectedItem())=="A+")
                    startActivity(i);
                else  if(String.valueOf(spinner1.getSelectedItem())=="B+")
                    startActivity(i);
                else   if(String.valueOf(spinner1.getSelectedItem())=="AB+")
                    startActivity(i);
                else   if(String.valueOf(spinner1.getSelectedItem())=="O+")
                    startActivity(i);
                else   if(String.valueOf(spinner1.getSelectedItem())=="A-")
                    startActivity(i);
                else   if(String.valueOf(spinner1.getSelectedItem())=="B-")
                    startActivity(i);
                else   if(String.valueOf(spinner1.getSelectedItem())=="AB-")
                    startActivity(i);
                else   if(String.valueOf(spinner1.getSelectedItem())=="O-")
                    startActivity(i);

                else
                    Toast.makeText(getApplicationContext(),"Please Select an Option", Toast.LENGTH_LONG).show();


            }
        });
    }
}