package com.whisper.tally.activity.bill;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.whisper.tally.R;
import com.whisper.tally.data.dao.BillDao;
import com.whisper.tally.data.dao.BillForListDao;
import com.whisper.tally.data.database.MyDataBase;
import com.whisper.tally.data.entity.Bill;
import com.whisper.tally.viewmodel.BillViewModel;

import java.util.List;

public class BillActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    FloatingActionButton floatingActionButton;
    TextView showNum;
    Spinner spinnerType;
    RecyclerView recyclerView;
    BillViewModel billViewModel;
    BillAdapter adapter;
    int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        recyclerView = findViewById(R.id.rev_bill);
        floatingActionButton = findViewById(R.id.fab_add_bill);
        showNum = findViewById(R.id.tev_show_bill_by_type);
        spinnerType = findViewById(R.id.spinner_type);
        type = spinnerType.getSelectedItemPosition();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BillActivity.this, BillAddActivity.class);
                //  intent.putExtra("bill",)

                startActivity(intent);
            }
        });

        adapter = new BillAdapter(getApplication(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);
        billViewModel = ViewModelProviders.of(this).get(BillViewModel.class);


        spinnerType.setOnItemSelectedListener(this);
        showNum.setText(billViewModel.sum.getValue().toString());
        billViewModel.update();

       /* billViewModel.getAllbills().observe(this, new Observer<List<Bill>>() {
            @Override
            public void onChanged(List<Bill> bills) {
               // Log.d("MYTAG",bills.toString());
                adapter.setBills(bills);
                adapter.notifyDataSetChanged();
            }
        });*/
      /* adapter.billsForLive.observe(this, new Observer<List<Bill>>() {
           @Override
           public void onChanged(List<Bill> bills) {
               adapter.setBills(bills);
           }
       });*/

    }

   /* public void update() {
        double incomeNum=0.0;
        MyDataBase myDataBase=MyDataBase.getInstance(getApplication());
        BillForListDao billForListDao=myDataBase.getBillForListDao();
        List<Bill> incomeList=billForListDao.getAllBillsByType(1);
        for ( Bill b: incomeList) {
            incomeNum+=b.value;
        }

        billViewModel.income.setValue(incomeNum);
        Log.d("MYTAG",billViewModel.income.getValue().toString());
        double expendNum=0.0;
        List<Bill> expendList=billForListDao.getAllBillsByType(0);
        for ( Bill b: expendList) {
            expendNum+=b.value;
        }
        billViewModel.expend.setValue(expendNum);
        Log.d("MYTAG",billViewModel.expend.getValue().toString());
        billViewModel.sum.setValue(billViewModel.income.getValue()-billViewModel.expend.getValue());
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bill_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            this.billViewModel.allBills.observe(this, new Observer<List<Bill>>() {
                @Override
                public void onChanged(List<Bill> bills) {
                    adapter.setBills(bills);
                    //    adapter.billsForLive=billViewModel.getAllbills();
                    //double num = 0.0;
                    // for (Bill b:bills) {
                    //   num+=b.value;
                    // }
                    // billViewModel.sum.setValue(num);
                    /*billViewModel.sum.setValue(billViewModel.income.getValue() - billViewModel.expend.getValue());*/
                    billViewModel.update();
                    showNum.setText("总额：" + billViewModel.sum.getValue().toString());
                    //   adapter.setBills(billViewModel.allBills.getValue());
                    adapter.notifyDataSetChanged();
                   // billViewModel.update();
                }
            });
        } else if (position == 1) {
            billViewModel.expendBills.observe(this, new Observer<List<Bill>>() {
                @Override
                public void onChanged(List<Bill> bills) {
                    adapter.setBills(bills);
                   /* // adapter.billsForLive=billViewModel.getExpendBills();
                    double num = 0.0;
                    for (Bill b : bills) {
                        num += b.value;
                    }
                    billViewModel.expend.setValue(num);*/
                    billViewModel.update();
                    showNum.setText("支出：" + billViewModel.expend.getValue().toString());
                    //  adapter.setBills(billViewModel.expendBills.getValue());
                    adapter.notifyDataSetChanged();
                   // billViewModel.update();
                }
            });
        } else if (position == 2) {
            billViewModel.incomeBills.observe(this, new Observer<List<Bill>>() {
                @Override
                public void onChanged(List<Bill> bills) {
                    adapter.setBills(bills);
                  /*  // adapter.billsForLive=billViewModel.getIncomeBills();
                    double num = 0.0;
                    for (Bill b : bills) {
                        num += b.value;
                    }
                    billViewModel.income.setValue(num);*/
                    billViewModel.update();
                    // adapter.setBills(billViewModel.incomeBills.getValue());
                    showNum.setText("收入：" + billViewModel.income.getValue().toString());
                    adapter.notifyDataSetChanged();

                }
            });
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        this.billViewModel.allBills.observe(this, new Observer<List<Bill>>() {
            @Override
            public void onChanged(List<Bill> bills) {
                adapter.setBills(bills);
                // adapter.billsForLive=billViewModel.getAllbills();
                // double num = 0.0;
                //   for (Bill b:bills) {
                //     num+=b.value;
                // }
                //   adapter.setBills(billViewModel.allBills.getValue());
                billViewModel.sum.setValue(billViewModel.income.getValue() - billViewModel.expend.getValue());
                adapter.notifyDataSetChanged();
            }
        });
    }
}
