package com.example.farahreza.demo;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class PatientServices extends AppCompatActivity {
    Button reminder1;
    Button tips;
    Button Sms1;
 CardView book;
    Button blood;
    CardView Ambb;
    CardView emer;
    CardView Blood;

    DatabaseReference  reference;
    Session session;
    String Name,Phone,email,P;
    PatientUsers user;

    FirebaseAuth mAuth;
    Query usrqry;

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_services);
        dl=findViewById(R.id.activity_patient_service);
        book=findViewById(R.id.Book);
        Sms1=findViewById(R.id.sms);
        reminder1=findViewById(R.id.reminder);
        session=new Session(this);
        Ambb=findViewById(R.id.ambb);
        emer=findViewById(R.id.emerr);
        Blood=findViewById(R.id.blood);
        mAuth=FirebaseAuth.getInstance();
        reference= FirebaseDatabase.getInstance().getReference("PatientUsers");
        usrqry=reference.orderByKey().equalTo(session.getusename());
      /*  reminder=findViewById(R.id.button7);
        tips=findViewById(R.id.button6);
        sms=findViewById(R.id.button8);
        book = findViewById(R.id.button1);
        blood=findViewById(R.id.button4);
        */

        t = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setTitle("Patient");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView) findViewById(R.id.nv);
      /* reference= FirebaseDatabase.getInstance().getReference().child("PatientUsers");
        usrqry=reference.orderByChild("uid").equalTo(session.getusename());
        usrqry.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    user=dataSnapshot1.getValue(PatientUsers.class);
                }
                name=user.getName();
                email=user.getEmail();
                mobile=user.getPhone();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/


/*
        usrqry.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot value:dataSnapshot.getChildren())
                {
                    user=value.getValue(PatientUsers.class);
                }

                Name=user.getName();
                Phone=user.getPhone();
                email=user.getEmail();
                //Intent i=getIntent();
                //P=i.getStringExtra("Pass");

                FirebaseUser user = mAuth.getCurrentUser();
                String userid=user.getUid();
                //Toast.makeText(getApplicationContext()," "+P,Toast.LENGTH_LONG).show();

               // PatientUsers newuser=new PatientUsers(Name,Phone,email,P);


               // reference.child(userid).setValue(newuser);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_LONG).show();
            }
        });*/

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.profile:
                        Intent i=getIntent();
                        final String P=i.getStringExtra("Pass");
                        final  Intent pf=new Intent(getApplicationContext(),Profile.class);
                       // pf.putExtra("Pass",P);
                        startActivity(pf);
                        break;
                    case R.id.notifications:
                        final  Intent pp=new Intent(getApplicationContext(),PatientEditInfo.class);
                        startActivity(pp);

                        break;
                    case R.id.Change:
                       // Toast.makeText(getApplicationContext(),"lalala",Toast.LENGTH_SHORT).show();
                        final  Intent cp=new Intent(getApplicationContext(),ChangePass.class);
                        startActivity(cp);

                            break;
                    case R.id.Sign_Out:
                        AlertDialog.Builder builder=new AlertDialog.Builder(PatientServices.this);
                        builder.setMessage("Are you sure?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                session.clearAll();
                                final  Intent ec=new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(ec);
                            }
                        }).setNegativeButton("No",null);

                        AlertDialog alert=builder.create();
                        alert.show();

                        //final  Intent ec=new Intent(getApplicationContext(),MainActivity.class);
                        //startActivity(ec);

                }
                return true;
            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final  Intent c=new Intent(getApplicationContext(),BookAppointment.class);
                startActivity(c);

            }
        });

        Sms1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final  Intent ss=new Intent(getApplicationContext(),SmsPage.class);
                startActivity(ss);
            }
        });


        reminder1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final  Intent rr=new Intent(getApplicationContext(),Reminder.class);
                startActivity(rr);
            }
        });
        Ambb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final  Intent abb=new Intent(getApplicationContext(),AmbulenceService.class);
                startActivity(abb);
            }
        });
        emer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final  Intent emm=new Intent(getApplicationContext(),EmergecyUnit.class);
                startActivity(emm);
            }
        });
        Blood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final  Intent emm=new Intent(getApplicationContext(),BloodBank.class);
                startActivity(emm);
            }
        });

        /*

        tips.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final  Intent d=new Intent(getApplicationContext(),HealthTips.class);
                startActivity(d);
            }
        });
        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final  Intent f=new Intent(getApplicationContext(),SmsPage.class);
                startActivity(f);
            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final  Intent g=new Intent(getApplicationContext(),BookAppointment.class);
                startActivity(g);
            }
        });

        */

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        switch(keyCode)
        {
            case KeyEvent.KEYCODE_BACK:

                moveTaskToBack(true);

                return true;
        }
        return false;
    }
}
