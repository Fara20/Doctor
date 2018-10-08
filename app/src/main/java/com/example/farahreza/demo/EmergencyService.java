package com.example.farahreza.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EmergencyService extends AppCompatActivity {
    ListView lv;

    ArrayList<EmergencyJava> emerlst;
    AutoCompleteTextView autoCompleteTextView;


    DatabaseReference dr;

    DatabaseReference dRef;
    Session session;
    Query usrqry;
    FirebaseAuth mAuth;
   EmergencyJava user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_service);

        Intent i = getIntent();

        lv=findViewById(R.id.RetriveInfo);
        emerlst=new ArrayList<EmergencyJava>();
        autoCompleteTextView =findViewById(R.id.List);

        mAuth=FirebaseAuth.getInstance();
        dRef= FirebaseDatabase.getInstance().getReference("EmergencyJava");
        session=new Session(this);

        dRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren())
                {
                   EmergencyJava user=ds.getValue(EmergencyJava.class);
                    emerlst.add(user);
                }
                emergencyAdapter adapter=new emergencyAdapter(EmergencyService.this,emerlst);

                autoCompleteTextView.setAdapter(adapter);
                lv.setAdapter(adapter);



                autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        String txt=autoCompleteTextView.getText().toString();
                        Toast.makeText(EmergencyService.this,txt,Toast.LENGTH_LONG).show();
                    }
                });

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String text=lv.getItemAtPosition(i).toString();
                        Toast.makeText(EmergencyService.this,text,Toast.LENGTH_LONG).show();
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text=lv.getItemAtPosition(i).toString();
                Toast.makeText(EmergencyService.this,text,Toast.LENGTH_LONG).show();
            }
        });


    }
}
