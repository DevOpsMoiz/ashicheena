package com.example.languagetranslater;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {
    EditText ed1,edEmail,ed2,edconfirm;
    Button btn;
    TextView tv;
    FirebaseAuth mauth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ed1=findViewById( R.id.editTextRegUsername);
        ed2=findViewById( R.id.editTextRegPassword);
        edEmail=findViewById( R.id.editTextRegEmail);
        edconfirm=findViewById( R.id.editTextRegConfirmPassword);
        btn=findViewById( R.id.buttonReg);
        tv=findViewById( R.id.textViewExistingUser);
        mauth= FirebaseAuth.getInstance();

        tv.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(Signup.this,MainActivity.class) );
            }
        } );
        btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = ed1.getText().toString();
                String email = edEmail.getText().toString();
                String password = ed2.getText().toString();
                String confirm = edconfirm.getText().toString();
//                Database db = new Database(getApplicationContext(),"healthcare",null,1 );
                if (username.length()==0 || email.length()==0 || password.length()==0 || confirm.length()==0){
                    Toast.makeText( getApplicationContext(),"Please fill All details",Toast.LENGTH_SHORT).show();
                }
                else {
                    if (password.compareTo( confirm )==0) {
                        if (isValid( password)){
                            mauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful())
                                    {

                                        Toast.makeText( getApplicationContext(),"Record Inserted",Toast.LENGTH_SHORT).show();
                                        startActivity( new Intent(Signup.this,MainActivity.class) );}
                                    else
                                    {
                                        Toast.makeText(Signup.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Signup.this, "Failed do it Again", Toast.LENGTH_SHORT).show();

                                }
                            });

                        }
                        else {
                            Toast.makeText( getApplicationContext(),"Password must contain at least 8 characters, having letter,digit and special symbols",Toast.LENGTH_SHORT).show();

                        }
                    }else {
                        Toast.makeText( getApplicationContext(),"Password and Confirm password did't match",Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });
    }
    public static boolean isValid (String Passwordhere){
        int f1=0,f2=0,f3=0;
        if (Passwordhere.length() < 8){
            return false;
        }else {
            for (int p=0; p < Passwordhere.length();p++){
                if (Character.isLetter( Passwordhere.charAt( p ) )) {
                    f1=1;
                }
            }
            for (int r=0; r < Passwordhere.length();r++){
                if (Character.isDigit( Passwordhere.charAt( r ) )){
                    f2=1;
                }
            }
            for (int s=0; s < Passwordhere.length(); s++){
                char c = Passwordhere.charAt( s );
                if (c>=33&&c<=46 || c==64){
                    f3=1;
                }
            }
            if (f1==1 && f2==1 && f3==1)
                return  true;
            return false;}
    }
}
