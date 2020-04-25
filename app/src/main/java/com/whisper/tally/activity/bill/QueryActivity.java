package com.whisper.tally.activity.bill;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.whisper.tally.R;
import com.whisper.tally.data.entity.Bill;
import com.whisper.tally.viewmodel.BillViewModel;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class QueryActivity extends AppCompatActivity {
    TextView tevQueryText;
    DatePickerDialog datePickerDialog;
    Button button, button1;
    BillAdapter adapter;
    RecyclerView recyclerView;
    Date date;
    BillViewModel billViewModel;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        tevQueryText = findViewById(R.id.tev_query_show_time);
        button = findViewById(R.id.btn_query);
        button1 = findViewById(R.id.btn_query_test);
        recyclerView = findViewById(R.id.rev_query_bill);
        //final String[] timeForMated = new String[1];
        adapter = new BillAdapter(getApplication(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        billViewModel = new BillViewModel(getApplication());
        // adapter.setBills();
        /*billViewModel.getAllbills().observe(this, new Observer<List<Bill>>() {
            @Override
            public void onChanged(List<Bill> bills) {
                adapter.setBills(bills);
            }
        });*/
        List<Bill> bills = billViewModel.billForListDao.getAllBills();
        adapter.setBills(bills);
        recyclerView.setAdapter(adapter);

/*


为什么查询所有，通过类型查询都行？？？？？？？？？？？？？？？？？
就是time和timeformated不行？？？？？？？？？？？
为什么2020/04/23就可以  text 就不行？？？
 *//**/
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // CharSequence text=tevQueryText.getText().toString();
                 Log.d("MYTAG",">>>>>>>>>>>"+tevQueryText.getText().toString());
                final List<Bill> billList = billViewModel.billForListDao.getAllBillsByTimeFormated("2020/04/24");
                // List<Bill> billList=  billViewModel.billForListDao.getAllBillsByType(1);
                Log.d("MYTAG", billList.toString());
                adapter.setBills(billList);
                //adapter.setBills(billList);
                billViewModel.billDao.getAllBillsByTimeFormated(tevQueryText.getText().toString()).observe(QueryActivity.this, new Observer<List<Bill>>() {
                    @Override
                    public void onChanged(List<Bill> bills) {
                         //Log.d("MYTAG",bills.toString());

                       // String text = tevQueryText.getText().toString();
                       // Log.d("MYTAG", ">>>>>>>>>sdsadfsdf>>" + text);

                        /*
                        搞不懂了
                        whyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy?
                         //*/
                       // List<Bill> mybillList = billViewModel.billForListDao.getAllBillsByTimeFormated("2020/4/24");
                      //  Log.d("MYTAG", mybillList.toString());
                        adapter.setBills(billList);
                        adapter.notifyDataSetChanged();//这一句必须有
                    }
                });

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                // String timeForMated;
                datePickerDialog = new DatePickerDialog(QueryActivity.this, DatePickerDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        date = new Date(year, month, dayOfMonth);
                        //  String timeForMated =year+"/"+(month+1)+"/"+dayOfMonth;//为什么要减1900
                        // tevQueryText.setText(year+"/"+(month+1)+"/"+dayOfMonth);
                        tevQueryText.setText(year + "/" + (month + 1) + "/" + dayOfMonth);
                        // Log.d("MYTAG",tevQueryText.getText().toString());
                       /* billViewModel.getBillsForTimeFormated(tevQueryText.getText().toString()).observe(QueryActivity.this, new Observer<List<Bill>>() {
                            @Override
                            public void onChanged(List<Bill> bills) {
                                adapter.setBills(bills);
                            }
                        });*/

                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

              /* // datePickerDialog=new DatePickerDialog()
                final Calendar calendar=Calendar.getInstance();
               // String timeForMated;
                datePickerDialog=new DatePickerDialog(QueryActivity.this, DatePickerDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR,year);
                        calendar.set(Calendar.MONTH,month);
                        calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                     //  String timeForMated =year+"/"+(month+1)+"/"+dayOfMonth;//为什么要减1900
                        tevQueryText.setText(year+"/"+(month+1)+"/"+dayOfMonth);

                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }*/


    }

    /*@Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_query:{
               // datePickerDialog=new DatePickerDialog();
                final Calendar calendar=Calendar.getInstance();
                // String timeForMated;
                datePickerDialog=new DatePickerDialog(QueryActivity.this, DatePickerDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR,year);
                        calendar.set(Calendar.MONTH,month);
                        calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                        //  String timeForMated =year+"/"+(month+1)+"/"+dayOfMonth;//为什么要减1900
                        tevQueryText.setText(year+"/"+(month+1)+"/"+dayOfMonth);
                        Log.d("MYTAG",tevQueryText.getText().toString());

                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }

        }
    }*/
}
