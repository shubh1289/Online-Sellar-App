package com.example.onlineseller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.onlineseller.activity.HomeActivity;

public class InvoiceActivity extends AppCompatActivity {

    TextView price,total,prname;
    Button Orderok;

    TextView Billtotal,tax,servisetax,finalprice;
    ImageView  cartimage;
    int q=1;
    Double Total;
    int p=0;
    int st;
    Double t,fp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
        Intent intent= getIntent();


        price=findViewById(R.id.cartprice);

        total=findViewById(R.id.carttotal);
        prname=findViewById(R.id.cartname);
        cartimage=findViewById(R.id.cartimage);

        Billtotal=findViewById(R.id.billtotal);
        tax=findViewById(R.id.tax);
        servisetax=findViewById(R.id.servisetax);
        finalprice=findViewById(R.id.finaltotal);

        Orderok=findViewById(R.id.orderok);
        Orderok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert=new AlertDialog.Builder(InvoiceActivity.this);
                alert.setTitle("Order Confirm");
                alert.setMessage( "are you confirm this order ? ");
                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(InvoiceActivity.this,"Thank you for order..",Toast.LENGTH_SHORT).show();
                        Intent intent1=new Intent(InvoiceActivity.this, HomeActivity.class);
                        startActivity(intent1);
                        finish();
                    }
                });
                alert.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(InvoiceActivity.this,"cancel order",Toast.LENGTH_SHORT).show();
                    }
                });
                alert.setCancelable(false);
                AlertDialog dialog=alert.create();
                dialog.show();
            }
        });
        prname.setText(intent.getStringExtra("NAME"));
        price.setText(intent.getStringExtra("PRICE"));
        total.setText(intent.getStringExtra("TOTAL"));
//        Glide.with(this).load(intent.getStringExtra("URL")).into(cartimage);
//        Picasso.get().load(intent.getStringExtra("URL")).into(cartimage);
//        p=Integer.parseInt(price.getText().toString());
        Total=Double.valueOf(price.getText().toString());
        t=Total*0.10;
        String FTOTAL=String.valueOf(Total+t);

        tax.setText(String.valueOf(t));
        servisetax.setText(String.valueOf(Total*0.10));
        finalprice.setText(Total.toString());
        Billtotal.setText(String.valueOf(FTOTAL));



    }
}