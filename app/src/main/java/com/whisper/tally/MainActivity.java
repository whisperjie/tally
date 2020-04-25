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
import com.whisper.tally.activity.SortActivity;
import com.whisper.tally.activity.bill.BillActivity;
import com.whisper.tally.activity.bill.BillAddActivity;
import com.whisper.tally.activity.bill.QueryActivity;
import com.whisper.tally.activity.bill.ShowActivity;
import com.whisper.tally.user.UserData;
import com.whisper.tally.viewmodel.BillViewModel;
import com.whisper.tally.viewmodel.UserViewModel;

/*

livedata 怎么没用？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button mBtnSort,
            mBtnInfo;
    UserData userData;
    UserViewModel userViewModel;
    BillViewModel billViewModel;
    ImageButton mBtnSet,
            mBtnMoney,
            mBtnAdd,
            mBtnQuery,
            mBtnShow,
            mbtnExit,
            mBtnCopyright;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findElement();
        setListener();
        userData = new UserData(getApplicationContext());
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        billViewModel = new BillViewModel(getApplication());
//        mBtnInfo.setText(userViewModel.getName(userData).getValue());
        /*userViewModel.getName(userData).observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mBtnInfo.setText(s);
               // mBtnInfo.notifyAll();
            }
        });*/
       /* billViewModel.getSum().observe(this, new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                mBtnMoney.setText("余额为：" + aDouble.toString());
               // mBtnMoney.notifyAll();
            }
        });*/

    }

    private void setListener() {
             mBtnCopyright.setOnClickListener(this);
        mBtnSet.setOnClickListener(this);
//        mBtnInfo.setOnClickListener(this);
        mBtnAdd.setOnClickListener(this);
        mBtnMoney.setOnClickListener(this);
        // mBtnSort.setOnClickListener(this);
        mBtnQuery.setOnClickListener(this);
        mBtnShow.setOnClickListener(this);
        mbtnExit.setOnClickListener(this);
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
            /*case R.id.btn_info:
                intent.setClass(MainActivity.this, InfoActivity.class);
                break;*/
            case R.id.btn_add:
                intent.setClass(MainActivity.this, BillAddActivity.class);
                break;
            case R.id.btn_all:
                intent.setClass(MainActivity.this, BillActivity.class);
                break;
           /* case R.id.btn_sort:
                intent.setClass(MainActivity.this, SortActivity.class);
                break;*/
            case R.id.btn_query:
                intent.setClass(MainActivity.this, QueryActivity.class);
                break;
            case R.id.btn_show:
                intent.setClass(MainActivity.this, ShowActivity.class);
                break;
            case R.id.btn_exit:
                //   System.exit(0);
                finish();
                break;

        }
        startActivity(intent);

    }

    private void findElement() {
        mBtnMoney = findViewById(R.id.btn_all);
        mBtnAdd = findViewById(R.id.btn_add);
        mBtnQuery = findViewById(R.id.btn_query);
        /*  mBtnInfo = findViewById(R.id.btn_info);*/
        mBtnCopyright = findViewById(R.id.btn_copyright);
        mBtnSet = findViewById(R.id.btn_set);
        mBtnShow = findViewById(R.id.btn_show);
        /*  mBtnSort = findViewById(R.id.btn_sort);*/
        mbtnExit = findViewById(R.id.btn_exit);
    }

}
