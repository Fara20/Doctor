package com.example.farahreza.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DocCancelDate extends AppCompatActivity {


    TextView t1,t2,t3;
    Button btn1;
    DatePicker dob;
    DatabaseReference dr,reference,reff;
    String date,time,clinic,location,doc,pname,uid,num;
   // Switch switch1, switch2, switch3;
    Query usrqry,qry;
    Session session;
    FirebaseAuth mAuth;
    ArraylistDctr n;
    ArrayList<AppointmentInfo> dctrlst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_cancel_date);

        dob=findViewById(R.id.dob);
        btn1=findViewById(R.id.btn);
        //switch1=findViewById(R.id.switch1);
        //switch2=findViewById(R.id.switch2);
        //switch3=findViewById(R.id.switch3);
        t1=findViewById(R.id.tt1);
        t2=findViewById(R.id.tt2);
        t3=findViewById(R.id.tt3);
        session=new Session(this);
        mAuth=FirebaseAuth.getInstance();



        Intent i=getIntent();
        doc=i.getStringExtra("doc");
        //location=i.getStringExtra("loca");



        /*switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
                switch1.setChecked(false);
                time="9pm-12am";

            }
        });*/

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int year=dob.getYear();
                int month=dob.getMonth()+1;
                int day=dob.getDayOfMonth();

                date=new StringBuilder().append(year).append("-")
                        .append(month).append("-")
                        .append(day).append("").toString();

                final  Intent c=new Intent(getApplicationContext(),DocDateList.class);
                //c.putExtra("clinic",C);
                c.putExtra("date",date);
                 dctrlst=new ArrayList<AppointmentInfo>();
               // c.putExtra("time",time);
                c.putExtra("doc",doc);
               DatabaseReference dreff= FirebaseDatabase.getInstance().getReference("AppointmentInfo");

                dreff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot ds:dataSnapshot.getChildren()) {

                                AppointmentInfo user1 = ds.getValue(AppointmentInfo.class);

                                    if (user1.getDocname().compareTo(doc)==0) {
                                        if (user1.getDate().compareTo(date) == 0) {
                                            dctrlst.add(user1);
                                        }
                                    }



                        }
                         n=new ArraylistDctr(dctrlst);
                       // adapter=new ClinicCancelAdapter(DocDateList.this,dctrlst);
                        //lv.setAdapter(adapter);
                 /*   autoCompleteTextView.setAdapter(adapter);

                    autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            flag=1;
                            AppointmentInfo ty= adapter.getItem(i);
                           // autoCompleteTextView.setText(ty.getName());
                            uid=ty.getUid();
                            String sme=autoCompleteTextView.getText().toString();



                            Toast.makeText(DocDateList.this,sme,Toast.LENGTH_LONG).show();
                        }
                    });*/

                        //dctrlst.add(user);








                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });



              c.putExtra("list",n);
              Bundle args=new Bundle();
              args.putSerializable("Arr",dctrlst);
              //args.putParcelableArrayList("ppp",dctrlst);
              c.putExtra("bundle",args);
                startActivity(c);
            }
        });

    }


}
