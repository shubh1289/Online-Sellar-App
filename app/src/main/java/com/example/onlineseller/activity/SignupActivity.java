package com.example.onlineseller.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlineseller.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {
    Button signup;
    TextView login;
    EditText txtusername;
    EditText emailid;
    EditText txtpassword;
    EditText txtcpassword;


   FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signup = findViewById(R.id.signup);
        login = findViewById(R.id.login);
        txtusername = findViewById(R.id.txtusername);
        emailid = findViewById(R.id.emailid);
        txtpassword = findViewById(R.id.txtpassword);
        txtcpassword = findViewById(R.id.txtcpassword);
        signup = findViewById(R.id.signup);
        login = findViewById(R.id.login);

        auth=FirebaseAuth.getInstance();


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validData()) {
                    auth.createUserWithEmailAndPassword(emailid.getText().toString().trim(),txtpassword.getText().toString().trim())
                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            startActivity(new Intent(SignupActivity.this, HomeActivity.class));
                                            finish();
                                        }
                                    })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(SignupActivity.this, "failed Signup..", Toast.LENGTH_SHORT).show();
                                                }
                                            });

                }

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            }
        });
    }

    private boolean validData() {
        if (txtusername.getText().toString().isEmpty()) {
            txtusername.setError("Please Enter UserName");
            return false;
        }
        if (emailid.getText().toString().isEmpty()) {
            emailid.setError("Please Enter Email-id");
            return false;
        } if (!(emailid.getText().toString().endsWith("@gmail.com"))) {
            emailid.setError("Please Enter Correct Formate Email-id");
            return false;
        } if (txtpassword.getText().toString().isEmpty()) {
            txtpassword.setError("Please Enter Password");
            return false;
        } if (txtpassword.getText().toString().length() < 6) {
            txtpassword.setError("Please Enter Minimum 6 character Password");
            return false;
        } if (txtcpassword.getText().toString().isEmpty()) {
            txtcpassword.setError("Please Enter Confirm Password");
            return false;
        } if (txtcpassword.getText().toString().length() < 6) {
            txtcpassword.setError("Please Enter Minimum 6 character Password");
            return false;
        }
        if (!(txtcpassword.getText().toString().equals(txtpassword.getText().toString()))) {
            txtcpassword.setError("Please Enter Sem Password");
            return false;
        }


        return true;
    }

}