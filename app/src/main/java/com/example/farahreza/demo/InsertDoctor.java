package com.example.farahreza.demo;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
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
import java.util.Calendar;
import java.util.List;

public class InsertDoctor extends AppCompatActivity {

    Button insertbtn;
    EditText name, capacity;
    RadioGroup  grpgender;
    RadioButton  genderbtn;
    Switch switch1, switch2, switch3;
    ClinicSignUpInformation user;
    Spinner spinner2;
    DoctorInfo docuser;
    FirebaseAuth mAuth;
    DatabaseReference dRef, dRef1;
    String clinicname;
    Query usrqry;
    Session session;
    String type="Doctor";

    String strname,strcapacity,timeslot1,timeslot2,timeslot3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_doctor);

        spinner2 = (Spinner) findViewById(R.id.spinner2);
        insertbtn = findViewById(R.id.btn);
        name = findViewById(R.id.namee);
        capacity = findViewById(R.id.capacity);
        grpgender=findViewById(R.id.radiogroup);

        switch1=findViewById(R.id.switch1);
        switch2=findViewById(R.id.switch2);
        switch3=findViewById(R.id.switch3);

        spinner2 = (Spinner) findViewById(R.id.spinner2);
        List<String> list = new ArrayList<String>();
        list.add("Select a Speciality");
        list.add("Medicine");
        list.add("Pathology");
        list.add("Pediatrics");
        list.add("Dental");
        list.add("Cardiology");
        list.add("Surgery");
        list.add("Neurology");
        list.add("Psychiatry");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
               R.layout.spinner_dropdown_item, list);

        dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);



        mAuth=FirebaseAuth.getInstance();
        dRef= FirebaseDatabase.getInstance().getReference("DoctorInfo");
        dRef1=FirebaseDatabase.getInstance().getReference("ClinicSignUpInformation");
        session=new Session(this);





       dRef1= FirebaseDatabase.getInstance().getReference().child("ClinicSignUpInformation");
        usrqry=dRef1.orderByKey().equalTo(session.getusename());

        usrqry.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot value:dataSnapshot.getChildren())
                {
                    user=value.getValue(ClinicSignUpInformation.class);
                }


               clinicname=user.getName();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_LONG).show();
            }
        });


        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged (CompoundButton buttonView,boolean isChecked){
                // do something, the isChecked will be
                // true if the switch is in the On position
                timeslot1 = "8am-2pm";
            }
        });

        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                timeslot2 = "4pm-8pm";
            }
        });
        switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                timeslot3 = "9pm-12am";
            }
        });


        strname=name.getText().toString().trim();
        strcapacity=capacity.getText().toString().trim();


        if(switch1.isChecked()){

            if (switch2.isChecked()){

                if (switch3.isChecked()){



                }

                timeslot3=null;

            }
            if (switch3.isChecked()){

                timeslot2=null;



            }

            timeslot2=null;
            timeslot3=null;



        }
        if (switch2.isChecked()){
            if (switch3.isChecked()){

                timeslot1=null;

            }
            timeslot1=null;
            timeslot3=null;

        }
        if (switch3.isChecked()){
            timeslot1=null;
            timeslot2=null;

        }


        insertbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strname=name.getText().toString().trim();
                strcapacity=capacity.getText().toString().trim();


                if (TextUtils.isEmpty(strname)){

                    Toast.makeText(getApplicationContext(),"Please enter name",Toast.LENGTH_LONG).show();
                    //stopping from further execution of the function.
                    return;
                }
                if (TextUtils.isEmpty(strcapacity)){

                    Toast.makeText(getApplicationContext(),"Please enter capacity",Toast.LENGTH_LONG).show();
                    //stopping from further execution of the function.
                    return;
                }


                  int id1 = grpgender.getCheckedRadioButtonId();
                  genderbtn = findViewById(id1);
                String gender = genderbtn.getText().toString();
                String speciality =String.valueOf(spinner2.getSelectedItem());


                if (TextUtils.isEmpty(gender)){

                    Toast.makeText(getApplicationContext(),"Please select a gender",Toast.LENGTH_LONG).show();
                    //stopping from further execution of the function.
                    return;
                }
                if (TextUtils.isEmpty(speciality)){

                    Toast.makeText(getApplicationContext(),"Please select a speciality",Toast.LENGTH_LONG).show();
                    //stopping from further execution of the function.
                    return;
                }


                if (TextUtils.isEmpty(timeslot1)&&TextUtils.isEmpty(timeslot2)&&TextUtils.isEmpty(timeslot3)){

                    Toast.makeText(getApplicationContext(),"Please a timeslot atleast",Toast.LENGTH_LONG).show();
                    //stopping from further execution of the function.
                    return;
                }







                DoctorInfo newuser=new DoctorInfo(strname,gender,speciality,strcapacity,timeslot1,timeslot2,timeslot3,type);
                DatabaseReference cref=FirebaseDatabase.getInstance().getReference("Capacity");
                Calendar now = Calendar.getInstance();
                String date=new StringBuilder().append(now.get(Calendar.YEAR)).append("-").append(Calendar.MONTH).append("-").append(Calendar.DAY_OF_MONTH).toString();
                CapacityDoc newcap=new CapacityDoc(strname,date,strcapacity,Integer.toString(0));
                cref.push().setValue(newcap);
                dRef.child(clinicname).push().setValue(newuser);

                Toast.makeText(getApplicationContext(), "Doctor Inserted successfully!", Toast.LENGTH_SHORT).show();

            }
        });











    }
}
