package com.whisper.tally;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    Button mBtnMoney,
            mBtnAdd,
            mBtnIncome,
            mBtnExpend,
            mBtnSort,
            mBtnInfo,
            mBtnCopyright;
    ImageButton mBtnSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findElement();
        setListener();

    }

    private void setListener() {
        Onclick onclick = new Onclick();
        mBtnCopyright.setOnClickListener(onclick);
    }

    private class Onclick implements View.OnClickListener {
        Intent intent=new Intent();
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
               // case R.id.btn_set:intent.setClass(MainActivity.this,);
              //   break;
                case R.id.btn_copyright:
                    intent.setClass(MainActivity.this, CopyrightActivity.class);
                    break;
            }
            startActivity(intent);
        }
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
