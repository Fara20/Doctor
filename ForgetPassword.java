package com.example.farahreza.demo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassword extends AppCompatActivity {
    EditText Email;
    Button send;
    FirebaseAuth mAuth;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        Email=findViewById(R.id.Email);
        send=findViewById(R.id.button);

        mAuth=FirebaseAuth.getInstance();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=Email.getText().toString().trim();
                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(getApplicationContext(), "Please Enter Email", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(getApplicationContext(), "Please Check Email", Toast.LENGTH_LONG).show();
                                Intent pp=new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(pp);
                            }
                            else
                            {   String message=task.getException().getMessage();
                                Toast.makeText(getApplicationContext(), " "+message, Toast.LENGTH_LONG).show();

                            }

                        }
                    });
                }

            }
        });
    }
}
