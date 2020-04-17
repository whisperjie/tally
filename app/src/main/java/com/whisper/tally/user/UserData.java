package com.whisper.tally.user;

import android.content.Context;
import android.content.SharedPreferences;

public class UserData {
    public String name;
    public String password;
    private Context context;
    public  SharedPreferences sharedPreferences ;
    public  SharedPreferences.Editor editor ;

    public UserData(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("MY_USER_DATA", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        name = sharedPreferences.getString("name", "");
        password = sharedPreferences.getString("password", "");
    }

    public void save() {
        editor.putString("name", name);
        editor.putString("password", password);
        editor.apply();
    }


}
