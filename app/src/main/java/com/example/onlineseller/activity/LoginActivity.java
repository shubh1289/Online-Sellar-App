package com.example.onlineseller.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.onlineseller.R;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {
    Button button;
    EditText txtpassword;
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button = findViewById(R.id.Login);
        login = findViewById(R.id.login);
        txtpassword = findViewById(R.id.txtpassword);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validData())
                {
                    startActivity((new Intent(LoginActivity.this,HomeActivity.class)));
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