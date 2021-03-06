package com.example.farahreza.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
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

public class DocLocList extends AppCompatActivity {
    ListView lv;

    ArrayList<ClinicSignUpInformation> dctrlst;
     AutoCompleteTextView autoCompleteTextView;
     String loc;
     Button btn;

    DatabaseReference dr;
    Query usrqry;
    FirebaseAuth mAuth;
    ClinicSignUpInformation user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_loc_list);

        autoCompleteTextView=findViewById(R.id.List);

        lv=findViewById(R.id.RetriveInfo);
        dctrlst=new ArrayList<ClinicSignUpInformation>();
        dr= FirebaseDatabase.getInstance().getReference("ClinicSignUpInformation");
        btn=findViewById(R.id.btnSubmit1);

        Intent i=getIntent();
        final String L=i.getStringExtra("place");

        //autoCompleteTextView.setTextColor();

        dr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren())
                {
                    ClinicSignUpInformation user=ds.getValue(ClinicSignUpInformation.class);

                    if(L.compareTo(user.getLocation())==0) {
                        dctrlst.add(user);
                    }
                   // dctname.add(user.getName());

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

              DocAdapter adapter=new DocAdapter(DocLocList.this,dctrlst);
                  lv.setAdapter(adapter);

                //  ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(DoctorList.this,android.R.layout.simple_list_item_1,dctname);
                //  ArrayAdapter<DoctorInfo> adapter2 = new ArrayAdapter<DoctorInfo>(DoctorList.this,android.R.layout.simple_list_item_1,dctrlst);

                autoCompleteTextView.setAdapter(adapter);

                lv.setAdapter(adapter);


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

                        loc=autoCompleteTextView.getText().toString();

                        //autoCompleteTextView.setText("hello");
                        //Toast.makeText(DocLocList.this,loc,Toast.LENGTH_LONG).show();
                    }
                });



                // studentAdapter adapter=new studentAdapter(DoctorList.this,dctrlst);
                //  lv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                loc=lv.getItemAtPosition(i).toString();
                autoCompleteTextView.setText(loc);
                //Toast.makeText(DocLocList.this,loc,Toast.LENGTH_LONG).show();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final  Intent c=new Intent(getApplicationContext(),PatientDocList.class);
                //c.putExtra("clinic",C);
                c.putExtra("hos",loc);
                c.putExtra("loca",L);
                startActivity(c);
            }
        });

    }
}
