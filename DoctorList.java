package com.example.farahreza.demo;

import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DoctorList extends AppCompatActivity  {

    ListView lv;

    ArrayList<DoctorInfo> dctrlst;
    ArrayList<String>dctname;
    AutoCompleteTextView autoCompleteTextView;

    DatabaseReference dr;
    String clinicname;
    DatabaseReference dRef, dRef1;
    Session session;
    Query usrqry;
    FirebaseAuth mAuth;
    ClinicSignUpInformation user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);

        autoCompleteTextView=findViewById(R.id.List);

        lv=findViewById(R.id.RetriveInfo);
        dctrlst=new ArrayList<DoctorInfo>();
        dctname=new ArrayList<String>();

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

                dr=FirebaseDatabase.getInstance().getReference("DoctorInfo").child(clinicname);

                dr.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds:dataSnapshot.getChildren())
                        {
                            DoctorInfo user=ds.getValue(DoctorInfo.class);
                            dctrlst.add(user);
                            dctname.add(user.getName());

                        }




                    /* ArrayAdapter<String> adapter = new ArrayAdapter<String>(DoctorList.this,android.R.layout.simple_list_item_1,dctname){
                        @Override
                        public View getView(int position, View convertView, ViewGroup parent) {
                            View view =super.getView(position, convertView, parent);

                            TextView textView=(TextView) view.findViewById(android.R.id.text1);

                            /*YOUR CHOICE OF COLOR*/
                           // textView.setTextColor(Color.parseColor("#a50303"));

                           // return view;
                       // }
                  //  };

                        //sort();

                        studentAdapter adapter=new studentAdapter(DoctorList.this,dctrlst);
                          lv.setAdapter(adapter);

                        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(DoctorList.this,android.R.layout.simple_list_item_1,dctname);

                        autoCompleteTextView.setAdapter(adapter1);

                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                String text=lv.getItemAtPosition(i).toString();
                                Toast.makeText(DoctorList.this," "+text,Toast.LENGTH_LONG).show();
                            }
                        });

                       /* autoCompleteTextView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(lv.getVisibility()==View.VISIBLE){
                                    lv.setVisibility(View.GONE);
                                }else{
                                    lv.setVisibility(View.VISIBLE);
                                }
                            }
                        });*/

                        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                String txt=autoCompleteTextView.getText().toString();
                                Toast.makeText(DoctorList.this," "+txt,Toast.LENGTH_LONG).show();
                            }
                        });



                       // studentAdapter adapter=new studentAdapter(DoctorList.this,dctrlst);
                      //  lv.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_LONG).show();
            }
        });




    }

    public void sort()
    {



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      getMenuInflater().inflate(R.menu.menu,menu);
      return true;
    }


}