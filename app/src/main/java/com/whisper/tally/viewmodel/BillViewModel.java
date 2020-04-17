package com.whisper.tally.viewmodel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.whisper.tally.data.dao.BillDao;
import com.whisper.tally.data.database.BillDatabase;
import com.whisper.tally.data.database.CategoryDatabase;
import com.whisper.tally.data.database.MyDataBase;
import com.whisper.tally.data.entity.Bill;

import java.util.List;

public class BillViewModel extends AndroidViewModel {
    public MyDataBase myDataBase;
    public BillDao billDao;
    public LiveData<List<Bill>> allbills;
    public LiveData<Double> sum;
    public LiveData<Double> income;
    public LiveData<Double> expend;

    public BillViewModel(@NonNull Application application) {
        super(application);
        myDataBase = MyDataBase.getInstance(application);
        billDao = myDataBase.getBillDao();
        allbills = billDao.getAllBills();
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
        return allbills;
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
