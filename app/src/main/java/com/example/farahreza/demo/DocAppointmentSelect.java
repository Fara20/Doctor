package com.example.farahreza.demo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DocAppointmentSelect extends AppCompatActivity {

    TextView t1,t2,t3;
    Button btn1;
    DatePicker dob;
    DatabaseReference dr,reference,reff;
    String date,time,clinic,location,doc,pname,uid;
    Switch switch1, switch2, switch3;
    Query usrqry,qry;
    Session session;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_appointment_select);
        dob=findViewById(R.id.dob);
        btn1=findViewById(R.id.btn);
        switch1=findViewById(R.id.switch1);
        switch2=findViewById(R.id.switch2);
        switch3=findViewById(R.id.switch3);
        t1=findViewById(R.id.tt1);
        t2=findViewById(R.id.tt2);
        t3=findViewById(R.id.tt3);
        session=new Session(this);
        mAuth=FirebaseAuth.getInstance();

        int year=dob.getYear();
        int month=dob.getMonth()+1;
        int day=dob.getDayOfMonth();

        uid=session.getusename();





        date=new StringBuilder().append(year).append("-")
                .append(month).append("-")
                .append(day).append("").toString();





        Intent i=getIntent();
        clinic=i.getStringExtra("hos");
        location=i.getStringExtra("loca");
        doc=i.getStringExtra("doc");
        reff= FirebaseDatabase.getInstance().getReference("AppointmentInfo");
        dr= FirebaseDatabase.getInstance().getReference("DoctorInfo").child(clinic);
        usrqry=dr.orderByChild("name").equalTo(doc);

        usrqry.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot value:dataSnapshot.getChildren())
                {
                   DoctorInfo user=value.getValue(DoctorInfo.class);
                   if(user.getTimeslot1()==null)
                   {
                       switch1.setVisibility(View.GONE);
                       t1.setVisibility(View.GONE);
                   }
                    if(user.getTimeslot2()==null)
                    {
                        switch2.setVisibility(View.GONE);
                        t2.setVisibility(View.GONE);
                    }
                    if(user.getTimeslot3()==null)
                    {
                        switch3.setVisibility(View.GONE);
                        t3.setVisibility(View.GONE);
                    }
                }


                //if(  Name=user.getName();


                //FirebaseUser user = mAuth.getCurrentUser();
                //  String userid=user.getUid();
                //Toast.makeText(getApplicationContext()," "+userid,Toast.LENGTH_SHORT).show();

                //PatientUsers newuser=new PatientUsers(Name,Phone,email,P);


                //reference.child(userid).setValue(newuser);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_LONG).show();
            }
        });
        reference= FirebaseDatabase.getInstance().getReference("PatientUsers");
        qry=reference.orderByKey().equalTo(session.getusename());
        qry.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot value:dataSnapshot.getChildren())
                {
                    PatientUsers user1=value.getValue(PatientUsers.class);
                    pname=user1.getName();
                }

                //  Name=user.getName();


                //FirebaseUser user = mAuth.getCurrentUser();
                //  String userid=user.getUid();
                //Toast.makeText(getApplicationContext()," "+userid,Toast.LENGTH_SHORT).show();

                //PatientUsers newuser=new PatientUsers(Name,Phone,email,P);


                //reference.child(userid).setValue(newuser);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_LONG).show();
            }
        });


        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(switch1.isChecked())
                {
                   switch2.setChecked(false);
                   switch3.setChecked(false);



                }
               else if(switch2.isChecked())
                {
                    switch1.setChecked(false);
                    switch3.setChecked(false);



                }
                else if(switch3.isChecked())
                {
                    switch1.setChecked(false);
                    switch2.setChecked(false);



                }



            }
        });

        switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch2.setChecked(false);
                switch3.setChecked(false);
                time="8am-2pm";

            }
        });

        switch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch1.setChecked(false);
                switch3.setChecked(false);
                time="4pm-8pm";

            }
        });

        switch3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch2.setChecked(false);
                switch3.setChecked(false);
                time="9pm-12am";

            }
        });




        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mix=clinic+"-"+uid+"-"+date;
                AppointmentInfo info=new AppointmentInfo(pname,clinic,location,date,time,doc,uid);
                DatabaseReference dr=FirebaseDatabase.getInstance().getReference("Capacity");

                reff.child(mix).setValue(info);
                Toast.makeText(getApplicationContext(), "Appointment Scheduled Successfully!", Toast.LENGTH_LONG).show();

            }
        });


    }
}
