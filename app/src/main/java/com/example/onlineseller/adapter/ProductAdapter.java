package com.example.onlineseller.adapter;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineseller.R;
import com.example.onlineseller.activity.viewProductActivity;
import com.example.onlineseller.modal.Product;
import com.example.onlineseller.onClickItem;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    Context context;
    ArrayList<Product> list;
    private onClickItem onClickItem;

    public ProductAdapter(Context context, ArrayList<Product> list,onClickItem onClickItem) {
        this.context = context;
        this.list = list;
        this.onClickItem=onClickItem;
    }

    @NonNull
    @Override
    public ProductAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v,onClickItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.MyViewHolder holder, int position) {
        Product product =list.get(position);
        holder.name.setText(product.getProductname());
        holder.price.setText(product.getProductprice());
        holder.image.setImageResource(product.getImage());
        // Picasso.get().load(list.get(position).getImage()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,price;
        ImageView image;
        LinearLayout buy;
       // Button order;

        public MyViewHolder(@NonNull View itemView,onClickItem onClickItem) {
            super(itemView);
            name=itemView.findViewById(R.id.itemname);
            price=itemView.findViewById(R.id.itemprice);
            image=itemView.findViewById(R.id.itemimg);
            buy=itemView.findViewById(R.id.buy);

            buy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    Intent intent=new  Intent(context, viewProductActivity.class);
//                    context.startActivity(intent);
                    if (ProductAdapter.this.onClickItem != null){
                        int pos=getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION){
                            Log.d("TAG","click");
                            ProductAdapter.this.onClickItem.onClick(pos);
                        }
                    }
                }
            });
            //order=itemView.findViewById(R.id.order);

        }
    }
}
