package com.example.onlineseller.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onlineseller.InvoiceActivity;
import com.example.onlineseller.R;
import com.example.onlineseller.databinding.ActivityHomeBinding;
import com.example.onlineseller.databinding.ActivityViewProductBinding;

public class viewProductActivity extends AppCompatActivity {

    String name,price;
   int image,tp;

  static int  count=1;


    private ActivityViewProductBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityViewProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
            setData();

        onClick();

    }

    private void setData() {
        Intent intent=getIntent();
        name= intent.getStringExtra("PNAME");
        price=intent.getStringExtra("PPRICE") ;
        image=intent.getIntExtra("PIMAGE",0);

        binding.pName.setText(name);
        binding.countQty.setText(String.valueOf(count));
        binding.price.setText(price);
        binding.pImage.setImageResource(image);

    }

    private void onClick() {

        binding.viewhed.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(viewProductActivity.this, InvoiceActivity.class));
                Intent intent= new Intent(viewProductActivity.this,InvoiceActivity.class);
                intent.putExtra("TOTAL",String.valueOf(tp));
                intent.putExtra("PRICE",price);
                intent.putExtra("NAME",name);
                intent.putExtra("QUN",String.valueOf(count));
                startActivity(intent);

            }
        });

        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count>=0){
                    count++;

                    int tp =Integer.parseInt(price)*count;
                    binding.countQty.setText(String.valueOf(count));
                    binding.TotalPrice.setText(String.valueOf(tp));
                }

            }
        });
        binding.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count>=1){
                    count--;
                     tp =Integer.parseInt(price)*count;
                    binding.countQty.setText(String.valueOf(count));
                    binding.TotalPrice.setText(String.valueOf(tp));
                }

            }
        });

    }
}