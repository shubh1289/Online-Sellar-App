package com.example.onlineseller.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.onlineseller.R;
import com.example.onlineseller.adapter.ProductAdapter;
import com.example.onlineseller.modal.Product;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AddProductActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);


    }


}