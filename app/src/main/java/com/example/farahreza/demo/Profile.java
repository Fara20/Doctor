package com.example.farahreza.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    DatabaseReference reference,reff;
    Query usrqry,qry;
    String name,mobile,email,type;
    PatientUsers user;
    Users usr;
    Session session;
    TextView name1,email1,phoneNumber1,type1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name1=findViewById(R.id.Name1);
        email1=findViewById(R.id.Email);
        phoneNumber1=findViewById(R.id.Phone);
        type1=findViewById(R.id.type);
        session=new Session(this);

        reference= FirebaseDatabase.getInstance().getReference().child("PatientUsers");
        usrqry=reference.orderByKey().equalTo(session.getusename());

        usrqry.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot value:dataSnapshot.getChildren())
                {
                    user=value.getValue(PatientUsers.class);
                }


                    name1.setText(user.getName());
                    email1.setText(user.getEmail());
                    phoneNumber1.setText(user.getPhone());

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
