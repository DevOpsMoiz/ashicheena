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

public class MainActivity extends AppCompatActivity {
    EditText ed1,ed2;
    Button btn;
    TextView tv1;
    FirebaseAuth mauth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed1=findViewById( R.id.editTextLoginUsername);
        ed2=findViewById( R.id.editTextLoginPassword);
        btn=findViewById( R.id.buttonLogin);
        tv1=findViewById( R.id.textViewNewUser);
        mauth=FirebaseAuth.getInstance();

        btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = ed1.getText().toString();
                String password = ed2.getText().toString();

                if (username.length() == 0 || password.length() == 0) {
                    Toast.makeText( getApplicationContext(), "Please Fill All Details", Toast.LENGTH_SHORT ).show();
                } else {
                    if ( username.length()==1  &&password.length()==1){

                        mauth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    Intent intent=new Intent(MainActivity.this,Home_LT.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else
                                {
                                    Toast.makeText(MainActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                        Toast.makeText( getApplicationContext(), "Login Success", Toast.LENGTH_SHORT ).show();

                        startActivity( new Intent(MainActivity.this,Home_LT.class) );
                    }else {
                        Toast.makeText( getApplicationContext(),"Invalid Username and Password",Toast.LENGTH_SHORT ).show();
                    }
                }
            }
        });
        tv1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(MainActivity.this, Signup.class) );
            }
        });

    }
}