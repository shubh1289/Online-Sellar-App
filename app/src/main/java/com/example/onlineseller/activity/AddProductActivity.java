package com.example.onlineseller.activity;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.onlineseller.R;
import com.example.onlineseller.adapter.ProductAdapter;
import com.example.onlineseller.databinding.ActivityAddProductBinding;
import com.example.onlineseller.modal.Product;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class AddProductActivity extends AppCompatActivity {
    ActivityAddProductBinding binding;
    ActivityResultLauncher<String> launcher;
    FirebaseDatabase database;
//    FirebaseStorage storage;
    String UserId;

    Uri resultok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAddProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database=FirebaseDatabase.getInstance();
//        storage=FirebaseStorage.getInstance();

       binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(binding.productname.getText().toString().isEmpty() && binding.productprice.getText().toString().isEmpty()){
                    Toast.makeText(AddProductActivity.this,"Field can't be blank",Toast.LENGTH_SHORT).show();
                }else{
//                    final StorageReference reference=storage.getReference().child(UserId);
//                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                        @Override
//                        public void onSuccess(Uri uri) {
//                        database.getReference().child("Product").child().setValue(uri.toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void unused) {
//                                Toast.makeText(MainActivity.this, "Image Uploded", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                            FirebaseDatabase.getInstance().getReference().child("Product").child(UserId).child("image").setValue(uri.toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    Toast.makeText(MainActivity.this,"Inserted", Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                        }
//                    });

                    HashMap<String, Object> m = new HashMap<String, Object>();
                    m.put("productname", binding.productname.getText().toString());
                    m.put("productprice",binding.productprice.getText().toString());
                    m.put("productid",UserId);

//                    FirebaseDatabase.getInstance().getReference().child("Product").child(UserId).setValue(m).addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            name.setText("");
//                            price.setText("");
//                            Toast.makeText(MainActivity.this,"Inserted", Toast.LENGTH_SHORT).show();
//                        }
//                    });

                }
            }
        });
        uplodeimage();
        displayimage();
        binding.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserId=FirebaseDatabase.getInstance().getReference().child("Product").push().getKey();
                launcher.launch("image/*");

            }
        });

        binding.enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageUplode();
            }
        });
    }

    private void displayimage() {
        database.getReference().child("image").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String img=snapshot.getValue(String.class);
//                Picasso.get().load(img).into(imageView);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void imageUplode(){
//        final StorageReference reference=storage.getReference().child(UserId);
//        reference.putFile(resultok).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                Toast.makeText(MainActivity.this,"store completed",Toast.LENGTH_SHORT).show();
//            }
//        });

    }
    private void uplodeimage() {

        launcher=registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                binding.image.setImageURI(result);
                resultok=result;

            }
        });

    }


}