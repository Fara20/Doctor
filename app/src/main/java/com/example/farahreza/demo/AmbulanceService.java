package com.example.farahreza.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AmbulanceService extends AppCompatActivity {
EditText vecname,contact,email;

  //  Ambulance user;
    FirebaseAuth mAuth;
    DatabaseReference dRef, dRef1;
    String clinicname;
    Query usrqry;
    Session session;
    String type="Ambulance";
    Button btn;
    String strname,strcontact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance_service);
        vecname=findViewById(R.id.vecname);
        contact=findViewById(R.id.contact);
        btn=findViewById(R.id.SaveButton);
        email=findViewById(R.id.email);

        mAuth=FirebaseAuth.getInstance();
        dRef= FirebaseDatabase.getInstance().getReference("Ambulance");

        session=new Session(this);








        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dRef= FirebaseDatabase.getInstance().getReference("Ambulance");

                strname=vecname.getText().toString().trim();
                strcontact=contact.getText().toString().trim();

                if (TextUtils.isEmpty(strname)){

                    Toast.makeText(getApplicationContext(),"Please enter name",Toast.LENGTH_LONG).show();
                    //stopping from further execution of the function.
                    return;
                }
                if (TextUtils.isEmpty(strcontact)){

                    Toast.makeText(getApplicationContext(),"Please enter contact",Toast.LENGTH_LONG).show();
                    //stopping from further execution of the function.
                    return;
                }


                FirebaseUser user=mAuth.getCurrentUser();
                String userid=user.getUid();




                Ambulance newuser=new Ambulance(strname,strcontact);
                dRef.child(strname).setValue(newuser);

                Toast.makeText(getApplicationContext(), "Ambulance Inserted successfully!", Toast.LENGTH_SHORT).show();
            }
        });




    }
}
