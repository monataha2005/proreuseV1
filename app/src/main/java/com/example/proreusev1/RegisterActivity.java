package com.example.proreusev1;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends Activity {
    private EditText userNameET;
    private EditText passwordET;
    private EditText getPasswordRetypeET;
    private EditText emailET;
    private EditText locationET;
    private Button done;


    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userNameET = findViewById(R.id.usernameReg);
        passwordET = findViewById(R.id.passwordReg);
        getPasswordRetypeET = findViewById(R.id.passwordRetype);
        emailET = findViewById(R.id.emailReg);
        locationET = findViewById(R.id.locationReg);
        done = findViewById(R.id.doneReg);




        databaseReference = FirebaseDatabase.getInstance().getReference();


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = userNameET.getText().toString();
                String password = passwordET.getText().toString();
                String email = emailET.getText().toString();
                String location = locationET.getText().toString();
                if(isUserNameValid(userName) && ispasswordValid(password)){
                    databaseReference.child("Users").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            DataSnapshot dataSnapshot = task.getResult();
                            if( dataSnapshot.child(userName).exists())
                                Toast.makeText(RegisterActivity.this,"User Name Exist",Toast.LENGTH_LONG).show();
                            else{

                                User user = new User(userName,password,email,location);
                                databaseReference.child("Users").child(userName).setValue(user);
                                Intent intent = new Intent();
                                intent.putExtra("UserName",userName);
                                intent.putExtra("pwd",password);
                                setResult(RESULT_OK,intent);
                                finish();
                            }

                        }
                    });
                } else{
                    Toast.makeText(RegisterActivity.this,"Error ..",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    public boolean isUserNameValid(String userName){
        return true;
    }
    public boolean ispasswordValid(String userName){
        return true;
    }


}