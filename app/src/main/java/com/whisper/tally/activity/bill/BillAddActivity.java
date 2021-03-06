package com.whisper.tally.activity.bill;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.whisper.tally.R;
import com.whisper.tally.data.entity.Bill;
import com.whisper.tally.data.entity.Category;
import com.whisper.tally.viewmodel.BillViewModel;
import com.whisper.tally.viewmodel.CategoryViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/*
做个下拉框花了三四个小时，我也是服，艹
 */
public class BillAddActivity extends AppCompatActivity {
    CategoryViewModel categoryViewModel;
    Spinner spinnerInOrOut, spinnerCategory;
    //MySpinnerAdapter spinnerAdapter;
    EditText edtMoney, edtRemark,edtBillTitle;
    TextView tevDate;
    Button btnAddBill,btnShowDatePicker;
    Bill bill;
    DatePickerDialog datePickerDialog;
    BillViewModel billViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_add);
        spinnerCategory = findViewById(R.id.spinner_category);
        spinnerInOrOut = findViewById(R.id.spinner_in_or_out);
        edtMoney = findViewById(R.id.edt_money);
        edtRemark = findViewById(R.id.edt_remark);
        edtBillTitle=findViewById(R.id.edt_bill_title);
        btnAddBill = findViewById(R.id.btn_add_bill);
        tevDate=findViewById(R.id.tev_show_date);
        billViewModel=new BillViewModel(getApplication());
        bill = new Bill();


        btnShowDatePicker=findViewById(R.id.btn_show_datepicker);
        btnShowDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar=Calendar.getInstance();
                datePickerDialog=new DatePickerDialog(BillAddActivity.this, DatePickerDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR,year);
                        calendar.set(Calendar.MONTH,month);
                        calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                        bill.time=new Date(year-1900,month,dayOfMonth);//为什么要减1900
                        tevDate.setText(year+"/"+(month+1)+"/"+dayOfMonth);

                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }

        });
        categoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);
        final List<String> list = new ArrayList<>();
        final ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, list);

        //spinnerAdapter = new MySpinnerAdapter(this);
        // spinnerAdapter.categories=categoryViewModel.getAllCategories().getValue();
        spinnerCategory.setAdapter(adapter);
        categoryViewModel.getAllCategories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                //Log.d("MYTAG", ">>>>>>>hello>>" + categories.toString());
                list.clear();
                for (Category c : categories) {
                    list.add(c.name);
                }
                spinnerCategory.setAdapter(adapter);
            }
        });



        btnAddBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bill.type = spinnerInOrOut.getSelectedItemPosition();
               // Log.d("MYTAG", ">>>>>>" + spinnerInOrOut.getSelectedItemPosition());
                //有可能空指针异常
                bill.category = list.get(spinnerCategory.getSelectedItemPosition());
               // Log.d("MYTAG", ">>>>>>" + bill.category);
                bill.value = Double.valueOf(edtMoney.getText().toString());
                bill.remark=edtRemark.getText().toString();
                bill.title=edtBillTitle.getText().toString();
                //bill.time=new Date();
                SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd");
                //dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                bill.timeFormated=dateFormat.format(bill.time);//utc时间戳转为北京时间
              //  Log.d("MYTAG",bill.timeFormated);
                try {
                    billViewModel.insertBill(bill);
                    Toast.makeText(getApplicationContext(),"添加账单成功",Toast.LENGTH_SHORT).show();
                   // Log.d("MYTAG??????",billViewModel1.getAllbills().toString());
                   /* billViewModel.getAllbills().observe(BillAddActivity.this, new Observer<List<Bill>>() {
                        @Override
                        public void onChanged(List<Bill> bills) {
                            Log.d("MYTAG",">>>>>>"+bills.toString());
                        }
                    });*/
                }catch (Exception e){
                    Log.d("MYTAG","插入失败》》");
                    e.printStackTrace();
                }
                //Log.d("MYTAG", ">>>>>>" + bill.value);
               // Log.d("MYTAG", ">>>>>>" + edtRemark.getText().toString());

               // bill.type = spinnerInOrOut.getSelectedItemPosition();
                //有可能空指针异常
                // bill.category=categoryViewModel.allCategories.getValue().get(spinnerCategory.getSelectedItemPosition()).name;
                /*if (edtMoney.getText().toString().equals("")) {
                    bill.value = 0.0;
                } else {
                    bill.value = Double.valueOf(edtMoney.getText().toString());
                }*/
                //Log.d("MYTAG", ">>>>>>" + bill.value);
                //这句问什么要报错？？？？？？？？？？？？？？？？？？
                // Log.d("MYTAG", ">>>>>>" + categoryViewModel.getAllCategories().getValue().get(0));
                // List<Category> categories=categoryViewModel.getAllCategories().getValue();
                // Log.d("MYTAG",">>>>>>"+categories.toString());
        /*for (Category c:categories) {
            list.add(c.name);
        }*/
            }
        });


    }
}
