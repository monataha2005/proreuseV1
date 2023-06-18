package com.example.proreusev1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";
    private EditText  userNameET;
    private EditText  passwordET;
    public static final int REGISTER_CODE = 675;
    private SharedPreferences sharedPreferences;
    private Button registerBtn;
    private Button loginBtn;
    private DatabaseReference dbr;
    private String userName;
    private String pasword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userNameET = findViewById(R.id.usernameLgn);
        passwordET = findViewById(R.id.passwordLgn);
        registerBtn = findViewById(R.id.register_btn);
        loginBtn = findViewById(R.id.login_btn);
        sharedPreferences = getSharedPreferences("user.data",MODE_PRIVATE);
        dbr = FirebaseDatabase.getInstance().getReference("Users");
        userName = sharedPreferences.getString("userName","");
        pasword = sharedPreferences.getString("pwd","");
        Log.d(TAG,userName+" "+pasword);
        if( !userName.equals("") ){
            Intent intent = new Intent(this, SharedReuseActivity.class);
            startActivity(intent);
        }else{
            userName = userNameET.getText().toString();
            pasword = passwordET.getText().toString();
        }
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivityForResult(intent,REGISTER_CODE);

            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbr.child(userName).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        userName = userNameET.getText().toString();
                        pasword = passwordET.getText().toString();
                        DataSnapshot ds = task.getResult();
                        if(ds.exists()){
                            if(ds.getValue(User.class).getPassword().equals(pasword)){
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("userName",userName);
                                editor.putString("pwd",pasword);
                                editor.commit();
                                Intent intent = new Intent(MainActivity.this, SharedReuseActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(MainActivity.this,"password Error",Toast.LENGTH_LONG).show();
                            }
                        }else{
                            Toast.makeText(MainActivity.this,"User Name doesn't exist",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        userName = data.getStringExtra("UserName");
        userNameET.setText(userName);

        pasword = data.getStringExtra("pwd");
        passwordET.setText(pasword);
    }
}