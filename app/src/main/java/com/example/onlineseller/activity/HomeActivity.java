package com.example.onlineseller.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements onClickItem {
    String cat="Mobile";

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
//        addData();
        addAdapter();

        binding.header.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(HomeActivity.this,LoginActivity.class));
                finish();

            }
        });

    }

    private void addAdapter() {
        list.clear();
        FirebaseDatabase.getInstance().getReference().child("Product").child(cat).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot snapshot1:snapshot.getChildren()){
                    Product  product=snapshot1.getValue(Product.class);
                    list.add(product);
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });
      GridLayoutManager layoutManager=new GridLayoutManager(this,2);
      adapter= new ProductAdapter(this,list,this);
      binding.rvView.setLayoutManager(layoutManager);
      binding.rvView.setAdapter(adapter);

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
                cat="Mobile";
                addAdapter();
                binding.catmobile.setBackgroundResource(R.drawable.backcat);
                binding.catwatch.setBackgroundResource(R.drawable.backuncat);
                binding.catlaptop.setBackgroundResource(R.drawable.backuncat);
                binding.catshoes.setBackgroundResource(R.drawable.backuncat);
            }
        });
        binding.catwatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cat="Watch";
                addAdapter();
                binding.catwatch.setBackgroundResource(R.drawable.backcat);
                binding.catmobile.setBackgroundResource(R.drawable.backuncat);
                binding.catlaptop.setBackgroundResource(R.drawable.backuncat);
                binding.catshoes.setBackgroundResource(R.drawable.backuncat);
            }
        });
        binding.catlaptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cat="Laptop";
                addAdapter();
                binding.catlaptop.setBackgroundResource(R.drawable.backcat);
                binding.catwatch.setBackgroundResource(R.drawable.backuncat);
                binding.catmobile.setBackgroundResource(R.drawable.backuncat);
                binding.catshoes.setBackgroundResource(R.drawable.backuncat);
            }
        });
        binding.catshoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cat="Shoes";
                addAdapter();
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