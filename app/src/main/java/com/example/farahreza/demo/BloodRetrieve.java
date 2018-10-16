package com.example.farahreza.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
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

public class BloodRetrieve extends AppCompatActivity {
    TextView name,address,gender,date,type,batch;
    DatabaseReference dRef,dRef1,dr;
    BloodBankInfo user1;
    Query q;
    FirebaseAuth fa;
    String useId,clinicname;
    Session session;
    ClinicSignUpInformation user;



    EditText aplus, bplus,abplus,oplus,aminus,bminus,abminus,ominus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_retrieve);

        aplus=findViewById(R.id.aplus);
        bplus=findViewById(R.id.bplus);
        abplus=findViewById(R.id.abplus);
        oplus=findViewById(R.id.oplus);
        aminus=findViewById(R.id.aminus);
        bminus=findViewById(R.id.bminus);
        abminus=findViewById(R.id.abminus);
        ominus=findViewById(R.id.ominus);

        fa=FirebaseAuth.getInstance();
        dRef= FirebaseDatabase.getInstance().getReference("BloodBankInfo");
        dRef1=FirebaseDatabase.getInstance().getReference("ClinicSignUpInformation");
        session=new Session(this);

        dRef1= FirebaseDatabase.getInstance().getReference().child("ClinicSignUpInformation");
        q=dRef1.orderByKey().equalTo(session.getusename());

        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot value:dataSnapshot.getChildren())
                {
                    user=value.getValue(ClinicSignUpInformation.class);
                }
                clinicname=user.getName();
                Toast.makeText(BloodRetrieve.this, clinicname, Toast.LENGTH_SHORT).show();
                dr=FirebaseDatabase.getInstance().getReference("BloodBankInfo/Apolo Hospital");
                dr.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot value:dataSnapshot.getChildren()){
                            user1=value.getValue(BloodBankInfo.class);
                        }
                        aplus.setText(user1.getAplus());

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

       /* q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                clinicname=user.getName();*/
    }
}
