package com.example.farahreza.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class BloodInfo extends AppCompatActivity {

    TextView aplus,aminus,bplus,bminus,oplus,ominus,abplus,abminus;
    DatabaseReference reference,reff;
    FirebaseAuth mAuth;
    Query usrqry,qry;
    String clinic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_info);
        aplus=findViewById(R.id.aplus);
        aminus=findViewById(R.id.aminus);
        bplus=findViewById(R.id.bplus);
        bminus=findViewById(R.id.bminus);
        abplus=findViewById(R.id.abplus);
        abminus=findViewById(R.id.abminus);
        oplus=findViewById(R.id.oplus);
        ominus=findViewById(R.id.ominus);

        Intent i=getIntent();
        clinic=i.getStringExtra("hos");


        reference= FirebaseDatabase.getInstance().getReference("BloodBankInfo");
        usrqry=reference.orderByKey().equalTo(clinic);


        usrqry.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot value:dataSnapshot.getChildren())
                {
                   BloodBankInfo user=value.getValue(BloodBankInfo.class);
                    aplus.setText(user.getAplus());
                    aminus.setText(user.getAminus());
                    bplus.setText(user.getBplus());
                    bminus.setText(user.getBminus());
                    abplus.setText(user.getAbplus());
                    abminus.setText(user.getAbminus());
                    oplus.setText(user.getOplus());
                    ominus.setText(user.getOminus());
                }



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
}
