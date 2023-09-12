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

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {
    Button button;
    EditText txtpassword;
    EditText email;
    TextView login;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button = findViewById(R.id.Login);
        login = findViewById(R.id.login);
        txtpassword = findViewById(R.id.txtpassword);
        email = findViewById(R.id.txtemail);

        auth=FirebaseAuth.getInstance();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validData())
                {
                    auth.signInWithEmailAndPassword(email.getText().toString().trim(),txtpassword.getText().toString().trim())
                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            startActivity((new Intent(LoginActivity.this,HomeActivity.class)));
                                            finish();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(LoginActivity.this, "Login Failed...", Toast.LENGTH_SHORT).show();
                                                }
                                            });

                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignupActivity.class));
            }
        });
    }

    private boolean validData() {
        if (txtpassword.getText().toString().isEmpty()) {
            txtpassword.setError("Please Enter Password");
            return false;
        } if (txtpassword.getText().toString().length() < 6) {
            txtpassword.setError("Please Enter Minimum 6 character Password");
            return false;
        }
        return true;
    }
}