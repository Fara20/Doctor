package com.example.farahreza.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class ClinicBlood extends AppCompatActivity {

    Button btn;
    EditText aplus, bplus,abplus,oplus,aminus,bminus,abminus,ominus;
    TextView aplusadd,bplusadd,abplusadd,oplusadd,aminusadd,bminusadd,abminusadd,ominusadd;
    TextView aplussub, bplussub,abplussub,oplussub,aminussub,bminussub,abminussub,ominussub;
    int c1, c2,c3,c4,c5,c6,c7,c8;
    int a,b,c,d,e,f,g,h;
    String straplus, strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus;
    ClinicSignUpInformation user;
    BloodBankInfo bloodBankInfouser;
    FirebaseAuth mAuth,fa;
    DatabaseReference dRef, dRef1,dRef2;
    String clinicname,useId;
    Query usrqry,q,qry;
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


        aplusadd=findViewById(R.id.aplusadd);
        bplusadd=findViewById(R.id.bplusadd);
        abplusadd=findViewById(R.id.abplusadd);
        oplusadd=findViewById(R.id.oplusadd);
        aminusadd=findViewById(R.id.aminusadd);
        bminusadd=findViewById(R.id.bminusadd);
        abminusadd=findViewById(R.id.abminusadd);
        ominusadd=findViewById(R.id.ominusadd);


        aplussub=findViewById(R.id.aplussub);
        bplussub=findViewById(R.id.bplussub);
        abplussub=findViewById(R.id.abplussub);
        oplussub=findViewById(R.id.oplussub);
        aminussub=findViewById(R.id.aminussub);
        bminussub=findViewById(R.id.bminussub);
        abminussub=findViewById(R.id.abminussub);
        ominussub=findViewById(R.id.ominussub);


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
                            oplus.setText(user1.getOplus());
                            aminus.setText(user1.getAminus());


                            bminus.setText(user1.getBminus());


                            abminus.setText(user1.getAbminus());


                            ominus.setText(user1.getOminus());



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
        aplusadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                c1++;
             String test=   Integer.toString(c1);



                //  BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                //dRef.child(clinicname).setValue(newuser);

                Toast.makeText(getApplicationContext(), test+"++" , Toast.LENGTH_SHORT).show();

                straplus=aplus.getText().toString().trim(); //prev val nisi
                a= Integer.parseInt(straplus);   //int a te rakhsi
                a=c1+a;                           // koybar plus chapse add korsi
                straplus=Integer.toString(a);    //then converting to string


            }
        });

        aplussub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                c1--;
                String test=   Integer.toString(c1);

                //  BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                //dRef.child(clinicname).setValue(newuser);

                Toast.makeText(getApplicationContext(), test+"--" , Toast.LENGTH_SHORT).show();

                straplus=aplus.getText().toString().trim(); //prev val nisi
                a= Integer.parseInt(straplus);   //int a te rakhsi
                a=a-c1;                           // koybar plus chapse add korsi
                straplus=Integer.toString(a);    //then converting to string


            }
        });

     /*   bplusadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                c2++;
                String test=   Integer.toString(c2);



                //  BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                //dRef.child(clinicname).setValue(newuser);

                Toast.makeText(getApplicationContext(), test+"++" , Toast.LENGTH_SHORT).show();

                strbplus=bplus.getText().toString().trim(); //prev val nisi
                b= Integer.parseInt(strbplus);   //int a te rakhsi
                b=b+c2;                           // koybar plus chapse add korsi
                strbplus=Integer.toString(b);    //then converting to string

            }
        });

        bplussub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                c2--;
                String test=   Integer.toString(c2);

                //  BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                //dRef.child(clinicname).setValue(newuser);

                Toast.makeText(getApplicationContext(), test+"--" , Toast.LENGTH_SHORT).show();

                strbplus=bplus.getText().toString().trim(); //prev val nisi
                b= Integer.parseInt(strbplus);   //int b te rakhsi
                b=b-c2;                           // koybar plus chapse add korsi
                strbplus=Integer.toString(b);    //then converting to string


            }
        });

*/





        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





             //   String r=Double.toString(a);
             //   Toast.makeText(ClinicBlood.this, r, Toast.LENGTH_SHORT).show();
                strbplus=bplus.getText().toString().trim();

                strabplus=abplus.getText().toString().trim();
                stroplus=oplus.getText().toString().trim();
                straminus=aminus.getText().toString().trim();
                strbminus=bminus.getText().toString().trim();
                strabminus=abminus.getText().toString().trim();
                strominus=ominus.getText().toString().trim();

                BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                dRef.child(clinicname).setValue(newuser);

                Toast.makeText(getApplicationContext(), straplus, Toast.LENGTH_SHORT).show();


            }


        });


       // BloodBank has been updated successfully!

      /*  dRef2=FirebaseDatabase.getInstance().getReference().child("BloodBankInfo");
        fa=FirebaseAuth.getInstance();
        FirebaseUser user1=fa.getCurrentUser();
        useId=user1.getUid();
        q=dRef.orderByKey().equalTo(clinicname);
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot value:dataSnapshot.getChildren()){
                    bloodBankInfouser=value.getValue(BloodBankInfo.class);
                }
                aplus.setText(bloodBankInfouser.getAplus());   //EditText e nicchi
                a=Double.parseDouble(aplus.getText().toString()); //double type variable e nicchi

                bplus.setText(bloodBankInfouser.getBplus());
                b=Double.parseDouble(bplus.getText().toString());

                abplus.setText(bloodBankInfouser.getAbplus());
                c=Double.parseDouble(abplus.getText().toString());

                oplus.setText(bloodBankInfouser.getOplus());
                d=Double.parseDouble(oplus.getText().toString());

                aminus.setText(bloodBankInfouser.getAminus());
                e=Double.parseDouble(aminus.getText().toString());

                bminus.setText(bloodBankInfouser.getBminus());
                f=Double.parseDouble(bminus.getText().toString());

                abminus.setText(bloodBankInfouser.getAbminus());
                g=Double.parseDouble(abminus.getText().toString());

                ominus.setText(bloodBankInfouser.getOminus());
                h=Double.parseDouble(ominus.getText().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });  */




      /*

        aplussub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (a!=0){
                a=a-1;
                straplus=Double.toString(a);

                BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                dRef.child(clinicname).setValue(newuser);

                Toast.makeText(getApplicationContext(), "A+ updated successfully!", Toast.LENGTH_SHORT).show();}
                else {
                    Toast.makeText(getApplicationContext(), "A+ already finished!", Toast.LENGTH_SHORT).show();
                }


            }
        });

        bplusadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b=b++;
                strbplus=Double.toString(b);

                BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                dRef.child(clinicname).setValue(newuser);

                Toast.makeText(getApplicationContext(), "B+ updated successfully!", Toast.LENGTH_SHORT).show();


            }
        });

        bplussub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b!=0){
                b=b--;
                strbplus=Double.toString(b);

                BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                dRef.child(clinicname).setValue(newuser);

                Toast.makeText(getApplicationContext(), "B+ updated successfully!", Toast.LENGTH_SHORT).show();}
                else {
                    Toast.makeText(getApplicationContext(), "B+ already finished!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        abplusadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                c=c++;
                strabplus=Double.toString(c);

                BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                dRef.child(clinicname).setValue(newuser);

                Toast.makeText(getApplicationContext(), "AB+ updated successfully!", Toast.LENGTH_SHORT).show();
            }
        });
        abplussub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(c!=0){
                c=c--;
                strabplus=Double.toString(c);

                BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                dRef.child(clinicname).setValue(newuser);

                Toast.makeText(getApplicationContext(), "AB+ updated successfully!", Toast.LENGTH_SHORT).show();}
                else {
                    Toast.makeText(getApplicationContext(), "AB+ already finished!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        oplusadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                d=d++;
                stroplus=Double.toString(d);

                BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                dRef.child(clinicname).setValue(newuser);

                Toast.makeText(getApplicationContext(), "O+ updated successfully!", Toast.LENGTH_SHORT).show();
            }
        });

        oplussub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(d!=0){
                d=d--;
                stroplus=Double.toString(d);

                BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                dRef.child(clinicname).setValue(newuser);

                Toast.makeText(getApplicationContext(), "O+ updated successfully!", Toast.LENGTH_SHORT).show();}
                else {
                    Toast.makeText(getApplicationContext(), "O- already empty!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        aminusadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                e=e++;
                straminus=Double.toString(e);

                BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                dRef.child(clinicname).setValue(newuser);

                Toast.makeText(getApplicationContext(), "A- updated successfully!", Toast.LENGTH_SHORT).show();
            }
        });

        aminussub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
if (e!=0){
                e=e--;
                straminus=Double.toString(e);

                BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                dRef.child(clinicname).setValue(newuser);

                Toast.makeText(getApplicationContext(), "A- updated successfully!", Toast.LENGTH_SHORT).show();}
                else {
    Toast.makeText(getApplicationContext(), "A- already finished!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        bminusadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                f=f++;
                strbminus=Double.toString(f);

                BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                dRef.child(clinicname).setValue(newuser);

                Toast.makeText(getApplicationContext(), "B- updated successfully!", Toast.LENGTH_SHORT).show();
            }
        });

        bminussub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
if (f!=0){
                f=f--;
                strbminus=Double.toString(f);

                BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                dRef.child(clinicname).setValue(newuser);

                Toast.makeText(getApplicationContext(), "B- updated successfully!", Toast.LENGTH_SHORT).show();}
                else {
    Toast.makeText(getApplicationContext(), "B- already finished!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        abminusadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                g=g++;
                strabminus=Double.toString(g);

                BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                dRef.child(clinicname).setValue(newuser);

                Toast.makeText(getApplicationContext(), "AB- updated successfully!", Toast.LENGTH_SHORT).show();
            }
        });

        abminussub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
if(g!=0){
                g=g--;
                strabminus=Double.toString(g);

                BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                dRef.child(clinicname).setValue(newuser);

                Toast.makeText(getApplicationContext(), "AB- updated successfully!", Toast.LENGTH_SHORT).show();}
                else {
    Toast.makeText(getApplicationContext(), "AB- already empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        ominusadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                h=h++;
                strominus=Double.toString(h);

                BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                dRef.child(clinicname).setValue(newuser);

                Toast.makeText(getApplicationContext(), "O- updated successfully!", Toast.LENGTH_SHORT).show();
            }
        });

        ominussub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
if(h!=0){
                h=h--;
                strominus=Double.toString(h);

                BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                dRef.child(clinicname).setValue(newuser);

                Toast.makeText(getApplicationContext(), "O- updated successfully!", Toast.LENGTH_SHORT).show();}

                else {
    Toast.makeText(getApplicationContext(), "O- already finished!", Toast.LENGTH_SHORT).show();
                }
            }
        });*/

    }
}
