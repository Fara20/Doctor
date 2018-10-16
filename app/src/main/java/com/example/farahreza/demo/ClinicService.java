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
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ClinicService extends AppCompatActivity {
    Button reminder1;
    Button tips;
    Button Sms1;
    CardView InsertDoc;
    Button blood;
    CardView Edit;
    CardView Ambb;
    CardView emer;
    CardView Blood;
    CardView Doclist;
    CardView Plist;
    Session session;
    DatabaseReference  reference;
    Query usrqry;
    String Name,Phone,email,P,L;
    ClinicSignUpInformation user;
    FirebaseAuth mAuth;



    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_service);
        dl=findViewById(R.id.activity_clinic_service);
        InsertDoc=findViewById(R.id.insertdoc);
       // Edit=findViewById(R.id.edit);
        Sms1=findViewById(R.id.sms);
        reminder1=findViewById(R.id.reminder);
        session=new Session(this);
        Ambb=findViewById(R.id.ambb);
        emer=findViewById(R.id.emerr);
        Blood=findViewById(R.id.bloodbank);
        Plist=findViewById(R.id.plist);
       Doclist=findViewById(R.id.docList);
      /*  reminder=findViewById(R.id.button7);
        tips=findViewById(R.id.button6);
        sms=findViewById(R.id.button8);
        book = findViewById(R.id.button1);
        blood=findViewById(R.id.button4);

        */
        mAuth=FirebaseAuth.getInstance();
        reference= FirebaseDatabase.getInstance().getReference("ClinicSignUpInformation");
        usrqry=reference.orderByKey().equalTo(session.getusename());

        t = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();



        getSupportActionBar().setTitle("Clinic Dashboard");

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

        //Intent i=getIntent();
       // P=i.getStringExtra("Pass");

      /*  usrqry.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot value:dataSnapshot.getChildren())
                {
                    user=value.getValue(ClinicSignUpInformation.class);
                }

                Name=user.getName();
                Phone=user.getPhoneNo();
                email=user.getEmail();
                L=user.getLocation();

                FirebaseUser user = mAuth.getCurrentUser();
                String userid=user.getUid();
                Toast.makeText(getApplicationContext()," "+userid,Toast.LENGTH_SHORT).show();

                ClinicSignUpInformation newuser=new ClinicSignUpInformation(Name,email,Phone,L,P);

                reference.child(userid).setValue(newuser);

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
                        final  Intent pf=new Intent(getApplicationContext(),ClinicProfile.class);
                        startActivity(pf);
                        break;
                    case R.id.notifications:
                        final  Intent pp=new Intent(getApplicationContext(),EditInfo.class);
                        startActivity(pp);
                        break;
                    case R.id.Change:
                       //Toast.makeText(getApplicationContext(),"lalala",Toast.LENGTH_SHORT).show();
                        final  Intent cc=new Intent(getApplicationContext(),ClinicPassChange.class);
                        startActivity(cc);

                        break;
                    case R.id.Sign_Out:
                        AlertDialog.Builder builder=new AlertDialog.Builder(ClinicService.this);
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


            InsertDoc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent c=new Intent(getApplicationContext(),InsertDoctor.class);
                    startActivity(c);

                }
            });
     //   InsertDoc.setOnClickListener(new View.OnClickListener() {
     //       @Override
      //      public void onClick(View view) {
      //          final  Intent c=new Intent(getApplicationContext(),BookAppointment.class);
        //        startActivity(c);

//            }
  //      });

            Blood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent d=new Intent(getApplicationContext(),ClinicBloodd.class);
                    startActivity(d);

                }
            });

            Doclist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent e=new Intent(getApplicationContext(),DoctorList.class);
                    startActivity(e);
                }
            });


       Ambb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent e=new Intent(getApplicationContext(),AmbulanceService.class);
                startActivity(e);
            }
        });

        emer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent e=new Intent(getApplicationContext(),ClinicEmergency.class);
                startActivity(e);
            }
        });
        Plist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent e=new Intent(getApplicationContext(),ClinicPatientList.class);
                startActivity(e);
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
