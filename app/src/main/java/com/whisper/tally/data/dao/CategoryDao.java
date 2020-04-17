package com.whisper.tally.data.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.whisper.tally.data.entity.Category;

import java.util.List;

@Dao
public interface CategoryDao {
    @Insert
    void insertCategories(Category... categories);
    @Update
    void updateCategories(Category... categories);
    @Delete
    void deleteCategories(Category... categories);
    @Query("DELETE FROM Category")
    void deleteAllCategories();
    @Query("SELECT * FROM Category ORDER BY ID ")
    LiveData<List<Category>> getAllCategories();
}
