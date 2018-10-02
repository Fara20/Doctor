package com.example.farahreza.demo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.ProviderQueryResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class PatientEditInfo extends AppCompatActivity {

    EditText Name1,Email,Pass,RePass,Phone,OldPass;
    String Placename,name,email,phone,pass,oldpass,name1,email1,phone1,Place1,pass1,repass1;
    DatabaseReference reference,reff;
    Query usrqry,qry;
    PatientUsers user;
    Button btn;
    Users usr;
    Session session;
    FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    TextWatcher textWatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_edit_info);


       Name1 = findViewById(R.id.Name);
        Email = findViewById(R.id.SignupEmail);

        Phone = findViewById(R.id.signupPhoneNumber);
        btn = findViewById(R.id.SaveButton);
        mAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);
        Place1=null;

        session = new Session(this);
        reference = FirebaseDatabase.getInstance().getReference().child("PatientUsers");
        usrqry = reference.orderByKey().equalTo(session.getusename());

        usrqry.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot value : dataSnapshot.getChildren()) {
                    user = value.getValue(PatientUsers.class);
                }

                name = user.getName();
                email = user.getEmail();
                pass = user.getPassword();
                phone = user.getPassword();
                Name1.setHint(name);
                Email.setHint(email);
                Phone.setHint(phone);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        });

        Name1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Name1.setHint(null);
                }
            }
        });

        Email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Email.setHint(null);
                }
            }
        });

        Phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Phone.setHint(null);
                }
            }
        });






        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SaveInfo();


            }
        });


    }

    void SaveInfo()
    {


        email1=Email.getText().toString().trim();
        name1=Name1.getText().toString();
        phone1=Phone.getText().toString();


        if(!email1.isEmpty())
        {

            AuthCredential credential= EmailAuthProvider.getCredential(mAuth.getCurrentUser().getEmail(),pass);

            mAuth.getCurrentUser().reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                    {
                        //Toast.makeText(getApplicationContext(), "ReAuthenticated Successfully", Toast.LENGTH_LONG).show();
                        mAuth.fetchProvidersForEmail(email1).addOnCompleteListener(new OnCompleteListener<ProviderQueryResult>() {
                            @Override
                            public void onComplete(@NonNull Task<ProviderQueryResult> task) {
                                if(task.isSuccessful()) {
                                    try{
                                        if (task.getResult().getProviders().size() == 1) {
                                            Toast.makeText(getApplicationContext(), "Email already in use.Please enter Another one", Toast.LENGTH_LONG).show();

                                        } else if (task.getResult().getProviders().size() == 0) {
                                            email = email1;
                                            Toast.makeText(getApplicationContext(), "Email Updated", Toast.LENGTH_LONG).show();

                                            mAuth.getCurrentUser().updateEmail(email)
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful()) {
                                                                //Toast.makeText(getApplicationContext(), "Email Updated", Toast.LENGTH_LONG).show();
                                                            }
                                                        }
                                                    });

                                        }
                                    }catch (NullPointerException e)
                                    {
                                        Toast.makeText(getApplicationContext(), " "+e.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        });
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Failed to ReAuthenticate", Toast.LENGTH_LONG).show();
                    }

                }
            });
        }
        if(!name1.isEmpty())
        {
            name=name1;
            Toast.makeText(getApplicationContext(),"Name Updated",Toast.LENGTH_SHORT).show();

        }
        if(!phone1.isEmpty())
        {
            phone=phone1;
            Toast.makeText(getApplicationContext(),"Phone number Updated",Toast.LENGTH_SHORT).show();
        }


        FirebaseUser user = mAuth.getCurrentUser();
        
        String userid=user.getUid();
        // Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();

        PatientUsers newuser=new PatientUsers(name,phone,email,pass);

        reference.child(userid).setValue(newuser);





    }
}
