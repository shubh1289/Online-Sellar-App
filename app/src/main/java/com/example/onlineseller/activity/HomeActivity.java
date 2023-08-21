package com.example.onlineseller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.onlineseller.R;
import com.example.onlineseller.adapter.ProductAdapter;
import com.example.onlineseller.databinding.ActivityHomeBinding;
import com.example.onlineseller.modal.Product;
import com.example.onlineseller.onClickItem;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements onClickItem {

    private ProductAdapter adapter;
    private Product product;
    private ArrayList<Product> list;
    private String pid;
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding=ActivityHomeBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        onClick();
        list=new ArrayList<Product>();
        addData();

        addAdapter();



    }

    private void addAdapter() {


      GridLayoutManager layoutManager=new GridLayoutManager(this,2);
      adapter= new ProductAdapter(this,list,this);

      binding.rvView.setLayoutManager(layoutManager);
      binding.rvView.setAdapter(adapter);

    }

    private void addData() {
        list.add(new Product(R.drawable.vivo,"vivo V27 5G","32999"));
        list.add(new Product(R.drawable.samsung,"Samsung Galaxy","124999"));
        list.add(new Product(R.drawable.realme,"realme 11Pro","24599"));
        list.add(new Product(R.drawable.oppo,"oppo Reno 10Pro","39999"));
        list.add(new Product(R.drawable.tecno,"Tacno camon","19999"));
        list.add(new Product(R.drawable.apple,"iPhone 14Pro","177999"));
        list.add(new Product(R.drawable.nothing,"Nothing Phone1","32999"));
        list.add(new Product(R.drawable.redmi,"redmi Note12","26999"));
        list.add(new Product(R.drawable.yuva,"Lava Yuva 2Pro","8999"));
        list.add(new Product(R.drawable.motorola,"Motarola Edge30","20000"));
        list.add(new Product(R.drawable.iqoo,"oppo Find N2","89999"));
        list.add(new Product(R.drawable.xioami,"Xiaomi POCO","26800"));
        list.add(new Product(R.drawable.huawei,"Huawei Nova11","118000"));
        list.add(new Product(R.drawable.poco,"Poco M5","12499"));
        list.add(new Product(R.drawable.rolexw,"Rolex","1790"));
        list.add(new Product(R.drawable.luxury,"Luxury Man's","3900"));
        list.add(new Product(R.drawable.fossil,"Fossil ME","19995"));
        list.add(new Product(R.drawable.titan,"Titan","1450"));
        list.add(new Product(R.drawable.arrival,"Casio Edifice","1250"));
        list.add(new Product(R.drawable.superw,"Super Seller","14150"));
        list.add(new Product(R.drawable.bezal,"Bezel & Wrist","500"));
        list.add(new Product(R.drawable.swatch,"Swatch","10621"));
        list.add(new Product(R.drawable.rado,"Rado","148000"));
        list.add(new Product(R.drawable.curren,"cureen","5700"));
        list.add(new Product(R.drawable.casio,"Casio","12900"));
        list.add(new Product(R.drawable.pintime,"PinTime","4999"));
        list.add(new Product(R.drawable.sonata,"Sonata","1500"));
        list.add(new Product(R.drawable.sapphero,"SappHero","3200"));
    }



    private void onClick() {
        binding.header.addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,AddProductActivity.class));
            }
        });

        binding.catmobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.catmobile.setBackgroundResource(R.drawable.backcat);
                binding.catwatch.setBackgroundResource(R.drawable.backuncat);
                binding.catlaptop.setBackgroundResource(R.drawable.backuncat);
                binding.catshoes.setBackgroundResource(R.drawable.backuncat);
            }
        });
        binding.catwatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.catwatch.setBackgroundResource(R.drawable.backcat);
                binding.catmobile.setBackgroundResource(R.drawable.backuncat);
                binding.catlaptop.setBackgroundResource(R.drawable.backuncat);
                binding.catshoes.setBackgroundResource(R.drawable.backuncat);
            }
        });
        binding.catlaptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.catlaptop.setBackgroundResource(R.drawable.backcat);
                binding.catwatch.setBackgroundResource(R.drawable.backuncat);
                binding.catmobile.setBackgroundResource(R.drawable.backuncat);
                binding.catshoes.setBackgroundResource(R.drawable.backuncat);
            }
        });
        binding.catshoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.catshoes.setBackgroundResource(R.drawable.backcat);
                binding.catwatch.setBackgroundResource(R.drawable.backuncat);
                binding.catlaptop.setBackgroundResource(R.drawable.backuncat);
                binding.catmobile.setBackgroundResource(R.drawable.backuncat);
            }
        });
    }

    @Override
    public void onClick(int pos) {

        Intent intent=new Intent(HomeActivity.this,viewProductActivity.class);
        intent.putExtra("PNAME",list.get(pos).getProductname());
        intent.putExtra("PPRICE",list.get(pos).getProductprice());
        intent.putExtra("PIMAGE",list.get(pos).getImage());
        startActivity(intent);

//        startActivity(new Intent(HomeActivity.this,viewProductActivity.class));
    }
}