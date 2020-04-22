package com.whisper.tally.viewmodel;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.whisper.tally.data.dao.BillDao;
import com.whisper.tally.data.dao.BillForListDao;
import com.whisper.tally.data.database.BillDatabase;
import com.whisper.tally.data.database.CategoryDatabase;
import com.whisper.tally.data.database.MyDataBase;
import com.whisper.tally.data.entity.Bill;

import java.util.List;

/*
为什么使用MutableLiveData不行。就无法动态改变列表


 */
public class BillViewModel extends AndroidViewModel {
    public MyDataBase myDataBase;
    public BillDao billDao;
    public BillForListDao billForListDao;
    public LiveData<List<Bill>> allBills;
    public LiveData<List<Bill>> incomeBills;
    public LiveData<List<Bill>> expendBills;
    public LiveData<List<Bill>> categoryBills;
    public static MutableLiveData<Double> sum;
    public static MutableLiveData<Double> income;
    public static MutableLiveData<Double> expend;

    public BillViewModel(@NonNull Application application) {
        super(application);
        myDataBase = MyDataBase.getInstance(application);
        billDao = myDataBase.getBillDao();
        billForListDao = myDataBase.getBillForListDao();
        allBills = new MutableLiveData<List<Bill>>();
        incomeBills = new MutableLiveData<>();
        expendBills = new MutableLiveData<>();
        categoryBills = new MutableLiveData<>();
        income = new MutableLiveData<>();
        expend = new MutableLiveData<>();
        sum = new MutableLiveData<>();
        /*if (income==null){
            income=new MutableLiveData<>();
            income.setValue(0.0);
        }else{
            income.setValue(income.getValue());
        }
        if (expend==null){
            expend=new MutableLiveData<>();
            expend.setValue(0.0);
        }else{
            expend.setValue(expend.getValue());
        }
        if (sum==null){
            sum=new MutableLiveData<>();
            sum.setValue(0.0);
        }else{
            sum.setValue(income.getValue()-expend.getValue());
        }*/
        /*double incomeNum=0.0;
        List<Bill> incomeList=billForListDao.getAllBillsByType(1);
        for ( Bill b: incomeList) {
            incomeNum+=b.value;
        }

        income.setValue(incomeNum);
        Log.d("MYTAG",income.getValue().toString());
        double expendNum=0.0;
        List<Bill> expendList=billForListDao.getAllBillsByType(0);
        for ( Bill b: expendList) {
            expendNum+=b.value;
        }
        expend.setValue(expendNum);
        Log.d("MYTAG",expend.getValue().toString());
        sum.setValue(income.getValue()-expend.getValue());
        Log.d("MYTAG",sum.getValue().toString());*/
        update();
        incomeBills = billDao.getAllBillsByType(1);
        expendBills = billDao.getAllBillsByType(0);
        allBills = billDao.getAllBills();
       /* for (Bill b:billDao.getAllBillsByType(0)) {
            double num = 0.0;
            num+=b.value;
            expend.setValue(num);
        }
        for (Bill b:billDao.getAllBillsByType(1)) {
            double num = 0.0;
            num+=b.value;
            income.setValue(num);
        }*/

        // sum.setValue(income.getValue()-expend.getValue());
        //  sum.setValue(income.getValue()-expend.getValue());
        //  Log.d("MYTAG",billDao.getAllBillsByType(1).toString());
        //  Log.d("MYTAG",billDao.getAllBills().toString());

    }

    public void update() {
        double incomeNum = 0.0;
        MyDataBase myDataBase = MyDataBase.getInstance(getApplication());
        BillForListDao billForListDao = myDataBase.getBillForListDao();
        List<Bill> incomeList = billForListDao.getAllBillsByType(1);
        for (Bill b : incomeList) {
            incomeNum += b.value;
        }

        income.setValue(incomeNum);
        Log.d("MYTAG", income.getValue().toString());
        double expendNum = 0.0;
        List<Bill> expendList = billForListDao.getAllBillsByType(0);
        for (Bill b : expendList) {
            expendNum += b.value;
        }
        expend.setValue(expendNum);
        Log.d("MYTAG", expend.getValue().toString());
        sum.setValue(income.getValue() - expend.getValue());
    }

    public LiveData<Double> getSum() {
        return sum;
    }

    public LiveData<Double> getIncome() {
        return income;
    }

    public LiveData<Double> getExpend() {
        return expend;
    }

    public LiveData<List<Bill>> getAllbills() {
        return allBills;
    }

    public LiveData<List<Bill>> getIncomeBills() {
        return incomeBills;
    }

    public LiveData<List<Bill>> getExpendBills() {
        return expendBills;
    }

    public void insertBill(Bill... bills) {
        new InsertAsyncTask(billDao).execute(bills);
    }

    public void updateBill(Bill... bills) {
        new UpdatetAsyncTask(billDao).execute(bills);
    }

    public void deleteBill(Bill... bills) {
        new DeleteAsyncTask(billDao).execute(bills);
    }

    public void deleteAllCategory() {
        new DeleteAlltAsyncTask(billDao).execute();
    }

    static class InsertAsyncTask extends AsyncTask<Bill, Void, Void> {
        private BillDao billDao;

        public InsertAsyncTask(BillDao billDao) {
            this.billDao = billDao;
        }

        @Override
        protected Void doInBackground(Bill... bills) {
            billDao.insertBills(bills);
            return null;
        }
    }

    static class DeleteAsyncTask extends AsyncTask<Bill, Void, Void> {
        private BillDao billDao;

        public DeleteAsyncTask(BillDao billDao) {
            this.billDao = billDao;
        }

        @Override
        protected Void doInBackground(Bill... bills) {
            billDao.deleteBills(bills);
            return null;
        }
    }

    static class UpdatetAsyncTask extends AsyncTask<Bill, Void, Void> {
        private BillDao billDao;

        public UpdatetAsyncTask(BillDao billDao) {
            this.billDao = billDao;
        }

        @Override
        protected Void doInBackground(Bill... bills) {
            billDao.updateBills(bills);
            return null;
        }
    }

    static class DeleteAlltAsyncTask extends AsyncTask<Void, Void, Void> {
        private BillDao billDao;

        public DeleteAlltAsyncTask(BillDao billDao) {
            this.billDao = billDao;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            billDao.deleteAllBills();
            return null;
        }
    }
}
