package com.whisper.tally.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;

import com.whisper.tally.R;

public class CategoryActivity extends AppCompatActivity {
   // Button mBtnToCategoryAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
       // mBtnToCategoryAdd=findViewById(R.id.btn_to_category_add);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.category_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }*/
}
