package com.example.farahreza.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ClinicBloodd extends AppCompatActivity {

    Button btn;
    EditText aplus, bplus,abplus,oplus,aminus,bminus,abminus,ominus;
    String straplus, strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus;
    TextView aplusadd,bplusadd,abplusadd,oplusadd,aminusadd,bminusadd,abminusadd,ominusadd;
    TextView aplussub, bplussub,abplussub,oplussub,aminussub,bminussub,abminussub,ominussub;
    int c1, c2,c3,c4,c5,c6,c7,c8;
    int a,b,c,d,e,f,g,h;
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
                straplus=Integer.toString(a);//then converting to string
                strbplus=bplus.getText().toString().trim();
                strabplus=abplus.getText().toString().trim();
                stroplus=oplus.getText().toString().trim();
                straminus=aminus.getText().toString().trim();
                strbminus=bminus.getText().toString().trim();
                strabminus=abminus.getText().toString().trim();
                strominus=ominus.getText().toString().trim();

                BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                dRef.child(clinicname).setValue(newuser);



            }
        });

        aplussub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                c1--;
                String test=   Integer.toString(c1);

                //  BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                //dRef.child(clinicname).setValue(newuser);

                Toast.makeText(getApplicationContext(), test , Toast.LENGTH_SHORT).show();

                straplus=aplus.getText().toString().trim(); //prev val nisi
                a= Integer.parseInt(straplus);   //int a te rakhsi
                a=a+c1;                           // koybar plus chapse add korsi
                if (a<0){
                    Toast.makeText(ClinicBloodd.this, "Already empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{ straplus=Integer.toString(a);    //then converting to string
                strbplus=bplus.getText().toString().trim();
                strabplus=abplus.getText().toString().trim();
                stroplus=oplus.getText().toString().trim();
                straminus=aminus.getText().toString().trim();
                strbminus=bminus.getText().toString().trim();
                strabminus=abminus.getText().toString().trim();
                strominus=ominus.getText().toString().trim();

                BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                dRef.child(clinicname).setValue(newuser);}


            }
        });

        bplusadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                c2++;
                String testt=   Integer.toString(c2);



                //  BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                //dRef.child(clinicname).setValue(newuser);

                Toast.makeText(getApplicationContext(), testt+"++" , Toast.LENGTH_SHORT).show();

                strbplus=bplus.getText().toString().trim(); //prev val nisi
                b= Integer.parseInt(strbplus);   //int a te rakhsi
                b=b+c2;                           // koybar plus chapse add korsi
                strbplus=Integer.toString(b);    //then converting to string


                straplus=aplus.getText().toString().trim();
               // strbplus=bplus.getText().toString().trim();
                strabplus=abplus.getText().toString().trim();
                stroplus=oplus.getText().toString().trim();
                straminus=aminus.getText().toString().trim();
                strbminus=bminus.getText().toString().trim();
                strabminus=abminus.getText().toString().trim();
                strominus=ominus.getText().toString().trim();

                BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                dRef.child(clinicname).setValue(newuser);

            }
        });

        bplussub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                c2--;
                String test=   Integer.toString(c2);

                //  BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                //dRef.child(clinicname).setValue(newuser);

                Toast.makeText(getApplicationContext(), test , Toast.LENGTH_SHORT).show();

                strbplus=bplus.getText().toString().trim(); //prev val nisi
                b= Integer.parseInt(strbplus);   //int b te rakhsi
                b=b+c2;                           // koybar plus chapse add korsi

                if (b<0){
                    Toast.makeText(ClinicBloodd.this, "Already Empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                strbplus=Integer.toString(b);    //then converting to string


                straplus=aplus.getText().toString().trim();
                // strbplus=bplus.getText().toString().trim();
                strabplus=abplus.getText().toString().trim();
                stroplus=oplus.getText().toString().trim();
                straminus=aminus.getText().toString().trim();
                strbminus=bminus.getText().toString().trim();
                strabminus=abminus.getText().toString().trim();
                strominus=ominus.getText().toString().trim();

                BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                dRef.child(clinicname).setValue(newuser);}



            }
        });



        abplusadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                c3++;
                String testt=   Integer.toString(c3);



                //  BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                //dRef.child(clinicname).setValue(newuser);

                Toast.makeText(getApplicationContext(), testt+"++" , Toast.LENGTH_SHORT).show();

                strabplus=abplus.getText().toString().trim(); //prev val nisi
                c= Integer.parseInt(strabplus);   //int a te rakhsi
                c=c+c3;                           // koybar plus chapse add korsi
                strabplus=Integer.toString(c);    //then converting to string


                straplus=aplus.getText().toString().trim();
                strbplus=bplus.getText().toString().trim();
                //strabplus=abplus.getText().toString().trim();
                stroplus=oplus.getText().toString().trim();
                straminus=aminus.getText().toString().trim();
                strbminus=bminus.getText().toString().trim();
                strabminus=abminus.getText().toString().trim();
                strominus=ominus.getText().toString().trim();

                BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                dRef.child(clinicname).setValue(newuser);

            }
        });

        abplussub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                c3--;
                String testt=   Integer.toString(c3);

                //  BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                //dRef.child(clinicname).setValue(newuser);

                Toast.makeText(getApplicationContext(), testt , Toast.LENGTH_SHORT).show();

                strabplus=abplus.getText().toString().trim(); //prev val nisi
                c= Integer.parseInt(strabplus);   //int b te rakhsi
                c=c+c3;                           // koybar plus chapse add korsi

                if (c<0){
                    Toast.makeText(ClinicBloodd.this, "Already Empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                strabplus=Integer.toString(c);    //then converting to string


                straplus=aplus.getText().toString().trim();
                strbplus=bplus.getText().toString().trim();
                //strabplus=abplus.getText().toString().trim();
                stroplus=oplus.getText().toString().trim();
                straminus=aminus.getText().toString().trim();
                strbminus=bminus.getText().toString().trim();
                strabminus=abminus.getText().toString().trim();
                strominus=ominus.getText().toString().trim();

                BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                dRef.child(clinicname).setValue(newuser);}



            }
        });



        oplusadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                c4++;
                String testt=   Integer.toString(c4);



                //  BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                //dRef.child(clinicname).setValue(newuser);

                Toast.makeText(getApplicationContext(), testt+"++" , Toast.LENGTH_SHORT).show();

                stroplus=oplus.getText().toString().trim(); //prev val nisi
                d= Integer.parseInt(stroplus);   //int a te rakhsi
                d=d+c4;                           // koybar plus chapse add korsi
                stroplus=Integer.toString(d);    //then converting to string


                straplus=aplus.getText().toString().trim();
                strbplus=bplus.getText().toString().trim();
                strabplus=abplus.getText().toString().trim();
               // stroplus=oplus.getText().toString().trim();
                straminus=aminus.getText().toString().trim();
                strbminus=bminus.getText().toString().trim();
                strabminus=abminus.getText().toString().trim();
                strominus=ominus.getText().toString().trim();

                BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                dRef.child(clinicname).setValue(newuser);

            }
        });


        oplussub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                c4--;
                String testt=   Integer.toString(c4);



                //  BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                //dRef.child(clinicname).setValue(newuser);

                Toast.makeText(getApplicationContext(), testt , Toast.LENGTH_SHORT).show();

                stroplus=oplus.getText().toString().trim(); //prev val nisi
                d= Integer.parseInt(stroplus);   //int a te rakhsi
                d=d+c4;                           // koybar plus chapse add korsi

                if (d<0){
                    Toast.makeText(ClinicBloodd.this, "Already Empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                stroplus=Integer.toString(d);    //then converting to string


                straplus=aplus.getText().toString().trim();
                strbplus=bplus.getText().toString().trim();
                strabplus=abplus.getText().toString().trim();
               // stroplus=oplus.getText().toString().trim();
                straminus=aminus.getText().toString().trim();
                strbminus=bminus.getText().toString().trim();
                strabminus=abminus.getText().toString().trim();
                strominus=ominus.getText().toString().trim();

                BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                dRef.child(clinicname).setValue(newuser);}

            }
        });



        aminusadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                c5++;
                String testt=   Integer.toString(c5);



                //  BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                //dRef.child(clinicname).setValue(newuser);

                Toast.makeText(getApplicationContext(), testt+"++" , Toast.LENGTH_SHORT).show();

                straminus=aminus.getText().toString().trim(); //prev val nisi
                e= Integer.parseInt(straminus);   //int a te rakhsi
                e=e+c5;                           // koybar plus chapse add korsi
                straminus=Integer.toString(e);    //then converting to string


                straplus=aplus.getText().toString().trim();
                strbplus=bplus.getText().toString().trim();
                strabplus=abplus.getText().toString().trim();
                stroplus=oplus.getText().toString().trim();
               // straminus=aminus.getText().toString().trim();
                strbminus=bminus.getText().toString().trim();
                strabminus=abminus.getText().toString().trim();
                strominus=ominus.getText().toString().trim();

                BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                dRef.child(clinicname).setValue(newuser);

            }
        });



        aminussub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                c5--;
                String testt=   Integer.toString(c5);



                //  BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                //dRef.child(clinicname).setValue(newuser);

                Toast.makeText(getApplicationContext(), testt , Toast.LENGTH_SHORT).show();

                straminus=aminus.getText().toString().trim(); //prev val nisi
                e= Integer.parseInt(straminus);   //int a te rakhsi
                e=e+c5;                           // koybar plus chapse add korsi

                if (e<0){
                    Toast.makeText(ClinicBloodd.this, "Already Empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                straminus=Integer.toString(e);    //then converting to string


                straplus=aplus.getText().toString().trim();
                strbplus=bplus.getText().toString().trim();
                strabplus=abplus.getText().toString().trim();
                stroplus=oplus.getText().toString().trim();
                //straminus=aminus.getText().toString().trim();
                strbminus=bminus.getText().toString().trim();
                strabminus=abminus.getText().toString().trim();
                strominus=ominus.getText().toString().trim();

                BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                dRef.child(clinicname).setValue(newuser);}

            }
        });



        bminusadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                c6++;
                String testt=   Integer.toString(c6);



                //  BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                //dRef.child(clinicname).setValue(newuser);

                Toast.makeText(getApplicationContext(), testt+"++" , Toast.LENGTH_SHORT).show();

                strbminus=bminus.getText().toString().trim(); //prev val nisi
                f= Integer.parseInt(strbminus);   //int a te rakhsi
                f=f+c6;                           // koybar plus chapse add korsi
                strbminus=Integer.toString(f);    //then converting to string


                straplus=aplus.getText().toString().trim();
                strbplus=bplus.getText().toString().trim();
                strabplus=abplus.getText().toString().trim();
                stroplus=oplus.getText().toString().trim();
                straminus=aminus.getText().toString().trim();
                //strbminus=bminus.getText().toString().trim();
                strabminus=abminus.getText().toString().trim();
                strominus=ominus.getText().toString().trim();

                BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                dRef.child(clinicname).setValue(newuser);

            }
        });


        bminussub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                c6--;
                String testt=   Integer.toString(c6);



                //  BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                //dRef.child(clinicname).setValue(newuser);

                Toast.makeText(getApplicationContext(), testt , Toast.LENGTH_SHORT).show();

                strbminus=bminus.getText().toString().trim(); //prev val nisi
                f= Integer.parseInt(strbminus);   //int te rakhsi
                f=f+c6;                           // koybar plus chapse add korsi
                if (f<0){
                    Toast.makeText(ClinicBloodd.this, "Already Empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                strbplus=Integer.toString(f);    //then converting to string


                straplus=aplus.getText().toString().trim();
                strbplus=bplus.getText().toString().trim();
                strabplus=abplus.getText().toString().trim();
                stroplus=oplus.getText().toString().trim();
                straminus=aminus.getText().toString().trim();
               // strbminus=bminus.getText().toString().trim();
                strabminus=abminus.getText().toString().trim();
                strominus=ominus.getText().toString().trim();

                BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                dRef.child(clinicname).setValue(newuser);}

            }
        });



        abminusadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                c7++;
                String testt=   Integer.toString(c7);



                //  BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                //dRef.child(clinicname).setValue(newuser);

                Toast.makeText(getApplicationContext(), testt+"++" , Toast.LENGTH_SHORT).show();

                strabminus=abminus.getText().toString().trim(); //prev val nisi
                g= Integer.parseInt(strabminus);   //int a te rakhsi
                g=g+c7;                           // koybar plus chapse add korsi
                strabminus=Integer.toString(g);    //then converting to string


                straplus=aplus.getText().toString().trim();
                strbplus=bplus.getText().toString().trim();
                strabplus=abplus.getText().toString().trim();
                stroplus=oplus.getText().toString().trim();
                straminus=aminus.getText().toString().trim();
                strbminus=bminus.getText().toString().trim();
               // strabminus=abminus.getText().toString().trim();
                strominus=ominus.getText().toString().trim();

                BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                dRef.child(clinicname).setValue(newuser);

            }
        });


        abminussub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                c7--;
                String testt=   Integer.toString(c7);



                //  BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                //dRef.child(clinicname).setValue(newuser);

                Toast.makeText(getApplicationContext(), testt , Toast.LENGTH_SHORT).show();

                strabminus=abminus.getText().toString().trim(); //prev val nisi
                g= Integer.parseInt(strabminus);   //int a te rakhsi
                g=g+c7;                           // koybar plus chapse add korsi

                if (g<0){
                    Toast.makeText(ClinicBloodd.this, "Already Empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {

                strabminus=Integer.toString(g);    //then converting to string


                straplus=aplus.getText().toString().trim();
                strbplus=bplus.getText().toString().trim();
                strabplus=abplus.getText().toString().trim();
                stroplus=oplus.getText().toString().trim();
                straminus=aminus.getText().toString().trim();
                strbminus=bminus.getText().toString().trim();
                //strabminus=abminus.getText().toString().trim();
                strominus=ominus.getText().toString().trim();

                BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                dRef.child(clinicname).setValue(newuser);}

            }
        });



        ominusadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                c8++;
                String testt=   Integer.toString(c8);



                //  BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                //dRef.child(clinicname).setValue(newuser);

                Toast.makeText(getApplicationContext(), testt+"++" , Toast.LENGTH_SHORT).show();

                strominus=ominus.getText().toString().trim(); //prev val nisi
                h= Integer.parseInt(strominus);   //int a te rakhsi
                h=h+c8;                           // koybar plus chapse add korsi
                strominus=Integer.toString(h);    //then converting to string


                straplus=aplus.getText().toString().trim();
                strbplus=bplus.getText().toString().trim();
                strabplus=abplus.getText().toString().trim();
                stroplus=oplus.getText().toString().trim();
                straminus=aminus.getText().toString().trim();
                strbminus=bminus.getText().toString().trim();
                strabminus=abminus.getText().toString().trim();
              //  strominus=ominus.getText().toString().trim();

                BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                dRef.child(clinicname).setValue(newuser);

            }
        });


        ominussub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /*  */

                 c8--;
                String testt=   Integer.toString(c8);



                //  BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                //dRef.child(clinicname).setValue(newuser);

                Toast.makeText(getApplicationContext(), testt , Toast.LENGTH_SHORT).show();

                strominus=ominus.getText().toString().trim(); //prev val nisi
                h= Integer.parseInt(strominus);   //int a te rakhsi


                h=h+c8;
                if (h<0){

                    Toast.makeText(ClinicBloodd.this, "Already empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                strominus=Integer.toString(h);    //then converting to string


                straplus=aplus.getText().toString().trim();
                strbplus=bplus.getText().toString().trim();
                strabplus=abplus.getText().toString().trim();
                stroplus=oplus.getText().toString().trim();
                straminus=aminus.getText().toString().trim();
                strbminus=bminus.getText().toString().trim();
                strabminus=abminus.getText().toString().trim();
               // strominus=ominus.getText().toString().trim();

                BloodBankInfo newuser=new BloodBankInfo(straplus,strbplus,strabplus,stroplus,straminus,strbminus,strabminus,strominus,type);
                dRef.child(clinicname).setValue(newuser);}



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
