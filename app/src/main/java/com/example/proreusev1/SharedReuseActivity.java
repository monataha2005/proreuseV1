package com.example.proreusev1;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SharedReuseActivity extends Activity {
    private Button addReuseItem;
    LinearLayout inputLinearLayout;
    private Button saveItemToDatabase;

    private EditText productNameET;
    private EditText dateET;
    private EditText priceET;
    private EditText descriptionET;

    private DatabaseReference dbr;
    private String userName;

    private ListView productsListView;
    private List<Info> infoList;
    ListViewAdapter listViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_reuse);

        SharedPreferences sharedPreferences = getSharedPreferences("user.data",MODE_PRIVATE);
        userName = sharedPreferences.getString("userName","");

        addReuseItem = findViewById(R.id.addReuseItem);
        inputLinearLayout = findViewById(R.id.inputLinerLayout);
        saveItemToDatabase = findViewById(R.id.save);

        productNameET = findViewById(R.id.product_nameInp);
        dateET = findViewById(R.id.dateInp);
        priceET = findViewById(R.id.priceInp);
        descriptionET = findViewById(R.id.descriptionInp);
        productsListView = findViewById(R.id.lstView);
        infoList = new ArrayList<Info>();
        listViewAdapter = new ListViewAdapter(this,R.layout.list_item,infoList);
        productsListView.setAdapter(listViewAdapter);

        dbr = FirebaseDatabase.getInstance().getReference("ReuseItems");
        dbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                infoList.clear();
                for( DataSnapshot userSnapshot : snapshot.getChildren()){
                    for( DataSnapshot productSnapshot : userSnapshot.getChildren() ){
                        Info info = productSnapshot.getValue(Info.class);
                        infoList.add(info);
                    }
                }
                listViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        addReuseItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputLinearLayout.setVisibility(View.VISIBLE);
            }
        });



        saveItemToDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String producName = productNameET.getText().toString();
                float price = Float.parseFloat( priceET.getText().toString());
                String date = dateET.getText().toString();
                String description = descriptionET.getText().toString();
                Info info = new Info(userName,producName,date,price,description);
                dbr.child(userName).push().setValue(info).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(SharedReuseActivity.this,"Product Saved",Toast.LENGTH_LONG).show();
                        inputLinearLayout.setVisibility(View.GONE);
                    }
                });


            }
        });

    }
}