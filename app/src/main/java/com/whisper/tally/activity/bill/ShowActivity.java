package com.whisper.tally.activity.bill;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import android.os.Bundle;
import android.util.Log;

import com.lwb.piechart.PieChartView;
import com.whisper.tally.R;
import com.whisper.tally.util.pie.PieData;
import com.whisper.tally.util.pie.PieView;
import com.whisper.tally.viewmodel.BillViewModel;

import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        BillViewModel billViewModel=new BillViewModel(getApplication());
        Log.d("MYTAG",""+billViewModel.getExpend().getValue());
        Log.d("MYTAG",""+billViewModel.getSum().getValue());
        PieChartView pieChartView=findViewById(R.id.pie_chart_view);
        pieChartView.addItemType(new PieChartView.ItemType("支出",Math.round(billViewModel.getExpend().getValue()),0xff20B2AA));
        pieChartView.addItemType(new PieChartView.ItemType("结余",Math.round(billViewModel.getSum().getValue()),0xff68228B));

    }
}
