package com.whisper.tally;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.whisper.tally.activity.CopyrightActivity;
import com.whisper.tally.activity.InfoActivity;
import com.whisper.tally.activity.SetActivity;
import com.whisper.tally.activity.bill.BillActivity;
import com.whisper.tally.activity.bill.BillAddActivity;
import com.whisper.tally.user.UserData;
import com.whisper.tally.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button mBtnMoney,
            mBtnAdd,
            mBtnIncome,
            mBtnExpend,
            mBtnSort,
            mBtnInfo,
            mBtnCopyright;
    UserData userData;
    UserViewModel userViewModel;
    ImageButton mBtnSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findElement();
        setListener();
        userData=new UserData(getApplicationContext());
        userViewModel= ViewModelProviders.of(this).get(UserViewModel.class);
        mBtnInfo.setText(userViewModel.getName(userData).getValue());
        userViewModel.getName(userData).observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mBtnInfo.setText(userViewModel.getName(userData).getValue());
            }
        });
    }

    private void setListener() {
        mBtnCopyright.setOnClickListener(this);
        mBtnSet.setOnClickListener(this);
        mBtnInfo.setOnClickListener(this);
        mBtnAdd.setOnClickListener(this);
        mBtnMoney.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            // case R.id.btn_set:intent.setClass(MainActivity.this,);
            //   break;
            case R.id.btn_copyright:
                intent.setClass(MainActivity.this, CopyrightActivity.class);
                break;
            case R.id.btn_set:
                intent.setClass(MainActivity.this, SetActivity.class);
                break;
            case R.id.btn_info:
                intent.setClass(MainActivity.this, InfoActivity.class);
                break;
            case R.id.btn_add:
                intent.setClass(MainActivity.this, BillAddActivity.class);
                break;
            case R.id.btn_all:
                intent.setClass(MainActivity.this, BillActivity.class);
        }
        startActivity(intent);

    }
    private void findElement() {
        mBtnMoney = findViewById(R.id.btn_all);
        mBtnAdd = findViewById(R.id.btn_add);
        mBtnIncome = findViewById(R.id.btn_income);
        mBtnInfo = findViewById(R.id.btn_info);
        mBtnCopyright = findViewById(R.id.btn_copyright);
        mBtnSet = findViewById(R.id.btn_set);
        mBtnExpend = findViewById(R.id.btn_expend);
        mBtnSort = findViewById(R.id.btn_sort);
    }

}
