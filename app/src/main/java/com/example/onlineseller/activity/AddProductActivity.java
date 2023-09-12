package com.example.onlineseller.activity;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.Toast;

import com.example.onlineseller.R;
import com.example.onlineseller.adapter.ProductAdapter;
import com.example.onlineseller.databinding.ActivityAddProductBinding;
import com.example.onlineseller.modal.Product;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;

public class AddProductActivity extends AppCompatActivity {
    ActivityAddProductBinding binding;
    ActivityResultLauncher<String> launcher;
    FirebaseDatabase database;
    FirebaseStorage storage;
    String UserId,imageName;
    Uri resultok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAddProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String id = FirebaseDatabase.getInstance().getReference().child("Product").push().getKey();
        UserId=FirebaseDatabase.getInstance().getReference().child("Product").push().getKey();

        database=FirebaseDatabase.getInstance();
        storage=FirebaseStorage.getInstance();

        binding.gohomebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

       binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(binding.productname.getText().toString().isEmpty() && binding.productprice.getText().toString().isEmpty()){
                    Toast.makeText(AddProductActivity.this,"Field can't be blank",Toast.LENGTH_SHORT).show();
                }else{
                    StorageReference reference=storage.getReference().child(imageName);
                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            resultok=uri;
//                            Product item=new Product(resultok.toString());

                            Product item=new Product(id,binding.productname.getText().toString(),binding.productprice.getText().toString(),binding.productCat.getSelectedItem().toString(),resultok.toString());
                            FirebaseDatabase.getInstance().getReference().child("Product").child(binding.productCat.getSelectedItem().toString()).child(id).setValue(item).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isComplete()){
                                                binding.productname.setText("");
                                                binding.productprice.setText("");
                                                Toast.makeText(AddProductActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
                                                onBackPressed();
                                            }
                                            else {

                                                Toast.makeText(AddProductActivity.this, "Failed Insert", Toast.LENGTH_SHORT).show();
                                            }

                                        }
                                    });


                        }
                    });

                }
            }
        });
        uplodeimage();
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



    private void imageUplode(){
        final StorageReference reference=storage.getReference().child(imageName);
        reference.putFile(resultok).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(AddProductActivity.this,"store completed",Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddProductActivity.this,"store Failed",Toast.LENGTH_SHORT).show();

            }
        });

    }
    private void uplodeimage() {

        launcher=registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                binding.image.setImageURI(result);
                imageName=getFileName(result);
                resultok=result;
            }
        });

    }
    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }


}