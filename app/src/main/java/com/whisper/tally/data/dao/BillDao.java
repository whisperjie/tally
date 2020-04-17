package com.whisper.tally.data.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.whisper.tally.data.entity.Bill;
import com.whisper.tally.data.entity.Category;

import java.util.List;

@Dao
public interface BillDao {
    @Insert
    void insertBills(Bill... bills);
    @Update
    void updateBills(Bill... bills);
    @Delete
    void deleteBills(Bill... bills);
    @Query("DELETE FROM Bill")
    void deleteAllBills();
    @Query("SELECT * FROM Bill ORDER BY ID ")
    LiveData<List<Bill>> getAllBills();
}
