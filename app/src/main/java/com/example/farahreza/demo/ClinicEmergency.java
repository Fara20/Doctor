package com.example.farahreza.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ClinicEmergency extends AppCompatActivity {
EditText name, nmbr;
String placename,strname,strnmbr;
Button btn;

    EmergencyJava usr;
    FirebaseAuth mAuth;
    DatabaseReference dRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_emergency);

        name=findViewById(R.id.vecname);
        nmbr=findViewById(R.id.contact);
        btn=findViewById(R.id.SaveButton);
        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        dRef = FirebaseDatabase.getInstance().getReference().child("EmergencyJava");

        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                .setCountry("BD")
                .build();

        autocompleteFragment.setFilter(typeFilter);


        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                // Log.i(TAG, "Place: " + place.getName());
                placename= place.getName().toString();


                //Toast.makeText(getApplicationContext()," "+Placename,Toast.LENGTH_LONG).show();

            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                //Log.i(TAG, "An error occurred: " + status);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               strname=name.getText().toString().trim();
               strnmbr=nmbr.getText().toString().trim();

                if (TextUtils.isEmpty(strname)){

                    Toast.makeText(getApplicationContext(),"Please enter name",Toast.LENGTH_LONG).show();
                    //stopping from further execution of the function.
                    return;
                }
                if (TextUtils.isEmpty(strnmbr)){

                    Toast.makeText(getApplicationContext(),"Please enter contact info",Toast.LENGTH_LONG).show();
                    //stopping from further execution of the function.
                    return;
                }
                if (TextUtils.isEmpty(placename)){

                    Toast.makeText(getApplicationContext(),"Please enter location",Toast.LENGTH_LONG).show();
                    //stopping from further execution of the function.
                    return;
                }





                EmergencyJava newuser = new EmergencyJava(strname, strnmbr, placename);

                   dRef.child(strname).setValue(newuser);
                   Toast.makeText(getApplicationContext(), "Saved Successfully!", Toast.LENGTH_LONG).show();



            }
        });




    }
}
