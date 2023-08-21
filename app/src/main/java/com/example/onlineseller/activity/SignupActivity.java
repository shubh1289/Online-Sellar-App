package com.example.onlineseller.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.onlineseller.R;

public class SignupActivity extends AppCompatActivity {
    Button button;
    TextView textView;
    EditText txtusername;
    EditText emailid;
    EditText txtpassword;
    EditText txtcpassword;
    Button signup;
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        button = findViewById(R.id.signup);
        textView = findViewById(R.id.login);
        txtusername = findViewById(R.id.txtusername);
        emailid = findViewById(R.id.emailid);
        txtpassword = findViewById(R.id.txtpassword);
        txtcpassword = findViewById(R.id.txtcpassword);
        signup = findViewById(R.id.signup);
        login = findViewById(R.id.login);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validData()) {
                    startActivity(new Intent(SignupActivity.this, HomeActivity.class));
                }

            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
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