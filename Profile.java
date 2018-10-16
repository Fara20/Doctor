package com.example.farahreza.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Profile extends AppCompatActivity {

    DatabaseReference reference,reff;
    FirebaseAuth mAuth;
    Query usrqry,qry;
    String name,mobile,email,type;
    PatientUsers user;
    Users usr;
    Session session;
    TextView name1,email1,phoneNumber1,type1;
    String Name,Phone,Email,P;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name1=findViewById(R.id.Name1);
        email1=findViewById(R.id.Email);
        phoneNumber1=findViewById(R.id.Phone);
        type1=findViewById(R.id.type);
        session=new Session(this);
        mAuth=FirebaseAuth.getInstance();

        Intent i=getIntent();
      P=i.getStringExtra("Pass");

        reference= FirebaseDatabase.getInstance().getReference("PatientUsers");
        usrqry=reference.orderByKey().equalTo(session.getusename());

        usrqry.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot value:dataSnapshot.getChildren())
                {
                    user=value.getValue(PatientUsers.class);
                    name1.setText(user.getName());
                }

                  //  Name=user.getName();
                Phone=user.getPhone();
                email=user.getEmail();

                    email1.setText(email);
                   phoneNumber1.setText(Phone);

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

        reff= FirebaseDatabase.getInstance().getReference().child("Users");
       qry=reff.orderByChild("uid").equalTo(session.getusename());

       qry.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               for(DataSnapshot value:dataSnapshot.getChildren())
               {
                   usr=value.getValue(Users.class);
               }

               type1.setText(usr.getType());
           }

           @Override
           public void onCancelled(DatabaseError databaseError) {
               Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_LONG).show();

           }
       });






    }
}
