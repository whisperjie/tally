package com.whisper.tally.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.whisper.tally.data.entity.Bill;

import java.util.Date;
import java.util.List;


@Dao
public interface BillForListDao {
    @Insert
    void insertBills(Bill... bills);
    @Update
    void updateBills(Bill... bills);
    @Delete
    void deleteBills(Bill... bills);
    @Query("DELETE FROM Bill")
    void deleteAllBills();
    @Query("SELECT * FROM Bill ORDER BY time DESC")
    List<Bill> getAllBills();
    @Query("SELECT * From Bill WHERE type=:type ORDER BY time DESC")
    List<Bill> getAllBillsByType(int type);
    @Query("SELECT * From Bill WHERE category=:category ORDER BY time DESC")
    List<Bill> getAllBillsByCategory(String category);
    @Query("SELECT * From Bill WHERE timeFormated= :timeFormated")
    List<Bill> getAllBillsByTimeFormated(String timeFormated);
    @Query("SELECT * From Bill WHERE time= :time ORDER BY time DESC")
    List<Bill> getAllBillsByTime(Date time);
}
