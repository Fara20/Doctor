package com.example.farahreza.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ClinicBlood extends AppCompatActivity {

Button btn;
EditText aplus, bplus,abplus,oplus,aminus,bminus,abminus,ominus;
String straplus, strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus;
    ClinicSignUpInformation user;
    BloodBankInfo bloodBankInfouser;
    FirebaseAuth mAuth;
    DatabaseReference dRef, dRef1;
    String clinicname;
    Query usrqry,qry;
    Session session;
    String type="Blood Bank";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_blood);
        btn=findViewById(R.id.btn);
        aplus=findViewById(R.id.aplus);
        bplus=findViewById(R.id.bplus);
        abplus=findViewById(R.id.abplus);
        oplus=findViewById(R.id.oplus);
        aminus=findViewById(R.id.aminus);
        bminus=findViewById(R.id.bminus);
        abminus=findViewById(R.id.abminus);
        ominus=findViewById(R.id.ominus);


        mAuth=FirebaseAuth.getInstance();
        dRef= FirebaseDatabase.getInstance().getReference("BloodBankInfo");
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
                    clinicname=user.getName();
                }

                dRef= FirebaseDatabase.getInstance().getReference("BloodBankInfo");
                qry=dRef.orderByKey().equalTo(clinicname);
                qry.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot value:dataSnapshot.getChildren())
                        {
                           BloodBankInfo user1=value.getValue(BloodBankInfo.class);
                           aplus.setText(user1.getAplus());
                           bplus.setText(user1.getBplus());
                           abplus.setText(user1.getAbplus());
                           abminus.setText(user1.getAbminus());
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






            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_LONG).show();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                straplus=aplus.getText().toString().trim();
                strbplus=bplus.getText().toString().trim();
                strabplus=abplus.getText().toString().trim();
                stroplus=oplus.getText().toString().trim();
                straminus=aminus.getText().toString().trim();
                strbminus=bminus.getText().toString().trim();
                strabminus=abminus.getText().toString().trim();
                strominus=ominus.getText().toString().trim();

                BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                dRef.child(clinicname).setValue(newuser);

                Toast.makeText(getApplicationContext(), "BloodBank has been updated successfully!", Toast.LENGTH_SHORT).show();


            }
        });

    }
}
