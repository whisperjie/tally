package com.whisper.tally.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.whisper.tally.MainActivity;
import com.whisper.tally.R;
import com.whisper.tally.user.UserData;

import java.util.HashSet;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button mBtnLogin, mBtnLoginDefault;
    EditText mIptName, mIptPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        UserData userData = new UserData(getApplicationContext());//不能传递this
        mBtnLogin = findViewById(R.id.btn_login);
        mBtnLoginDefault = findViewById(R.id.btn_login_default);
        mIptName = findViewById(R.id.input_name);
        mIptPassword = findViewById(R.id.input_password);
        String correctName = userData.name;
        mIptName.setText(correctName);
        mBtnLoginDefault.setOnClickListener(this);
        mBtnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login: {
                UserData userData = new UserData(getApplicationContext());//不能传递this
                Log.d("MyTag",userData.name+userData.password);

                String iptName = String.valueOf(mIptName.getText());
                String iptPassword = String.valueOf(mIptPassword.getText());
                String correctUserName = userData.name;
                if (correctUserName.equals("")) {
                    Toast.makeText(this, "还未注册，正在进行注册，以后以相同账户和密码登录", Toast.LENGTH_LONG).show();
                    userData.name = iptName;
                    userData.password = iptPassword;
                    userData.save();
                } else {
                    String correctPassword = userData.password;
                    if (correctPassword.equals(iptPassword)) {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    } else {
                        Toast.makeText(this, "密码错误", Toast.LENGTH_LONG).show();
                    }
                }
            }
            break;
            case R.id.btn_login_default: {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
            break;
        }
    }
}
