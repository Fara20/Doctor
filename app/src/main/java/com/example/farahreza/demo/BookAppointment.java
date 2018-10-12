package com.example.farahreza.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import java.util.ArrayList;
import java.util.List;

public class BookAppointment extends AppCompatActivity {

    String Placename,type;
    Button sbmt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        sbmt=findViewById(R.id.btnSubmit1);
        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setHint("Search  Location");

        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                .setCountry("BD")
                .build();

        autocompleteFragment.setFilter(typeFilter);


        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                // Log.i(TAG, "Place: " + place.getName());
                Placename=place.getName().toString();


                //Toast.makeText(getApplicationContext()," "+Placename,Toast.LENGTH_LONG).show();

            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                //Log.i(TAG, "An error occurred: " + status);
            }
        });


        sbmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!Placename.isEmpty())
                {
                   // Intent i=getIntent();
                   // final String C=i.getStringExtra("clinic");

                    final  Intent c=new Intent(getApplicationContext(),DocLocList.class);
                    //c.putExtra("clinic",C);
                    c.putExtra("place",Placename);
                    startActivity(c);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please Select a Location",Toast.LENGTH_LONG).show();
                }
            }

        });



    }

    }

