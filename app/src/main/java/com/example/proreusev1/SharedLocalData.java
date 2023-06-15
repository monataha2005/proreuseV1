package com.example.proreusev1;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedLocalData {
    public static SharedPreferences sharedPreferences;

    public SharedLocalData(Context context){
        sharedPreferences = context.getSharedPreferences("user.data",Context.MODE_PRIVATE);
    }

    public static void writeUser(User user){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userName",user.getUserName());
        editor.putString("email", user.getEmail());
        editor.putString("location",user.getLocation());
    }

    public static String getUserName(){
        return sharedPreferences.getString("userName",null);
    }
}
