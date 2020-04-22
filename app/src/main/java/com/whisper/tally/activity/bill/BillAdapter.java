package com.whisper.tally.activity.bill;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.whisper.tally.R;
import com.whisper.tally.activity.category.CategoryAdapter;
import com.whisper.tally.data.entity.Bill;
import com.whisper.tally.data.entity.Category;
import com.whisper.tally.viewmodel.BillViewModel;

import java.util.ArrayList;
import java.util.List;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.BillViewHolder> {
    List<Bill> bills = new ArrayList<>();
    LiveData<List<Bill>> billsForLive = new MutableLiveData<>();
    BillViewModel billViewModel;
    Application application;
    Context context;

    public BillAdapter(Application application, Context context) {
        this.application = application;
        this.context = context;
        this.billViewModel = new BillViewModel(application);
        this.billsForLive=billViewModel.getAllbills();
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    @NonNull
    @Override
    public BillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //return null;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.card_bill, parent, false);
        return new BillViewHolder(itemView);
        //return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BillViewHolder holder, final int position) {
        final Bill bill = bills.get(position);
        holder.billTitle.setText(bill.getTitle());
        holder.billValue.setText(bill.value.toString());
        holder.billTime.setText(bill.getTimeFormated());
        if (bill.type==1){
            holder.billType.setText("收");
        }else{
            holder.billType.setText("支");
        }
        holder.imbBillDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                billViewModel.deleteBill(bill);
                Toast.makeText(context,"删除成功",Toast.LENGTH_SHORT).show();
            }
        });
        holder.imbBillToDetial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Log.d("MYTAG", "bill 被单击  " + position);
                Intent intent = new Intent(context, BillDetialActivity.class);
                intent.putExtra("bill", bills.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bills.size();
    }

    public class BillViewHolder extends RecyclerView.ViewHolder {
        TextView billTitle, billValue, billTime, billType;
        ImageButton imbBillDelete, imbBillToDetial;

        public BillViewHolder(@NonNull View itemView) {

            super(itemView);
            billTitle = itemView.findViewById(R.id.tev_card_bill_title);
            billValue = itemView.findViewById(R.id.tev_card_value);
            billTime = itemView.findViewById(R.id.tev_bill_time);
            imbBillDelete = itemView.findViewById(R.id.imb_delete);
            imbBillToDetial = itemView.findViewById(R.id.imb_to_bill_detial);
            billType = itemView.findViewById(R.id.tev_bill_type);
        }
    }
}
