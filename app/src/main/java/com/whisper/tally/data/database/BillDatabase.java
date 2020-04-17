package com.whisper.tally.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.whisper.tally.data.dao.BillDao;
import com.whisper.tally.data.dao.CategoryDao;
import com.whisper.tally.data.entity.Bill;
import com.whisper.tally.data.entity.Category;
import com.whisper.tally.util.MyConverter;


@TypeConverters({MyConverter.class})
@Database(entities = {Bill.class},version = 1,exportSchema = false)
public abstract class BillDatabase extends RoomDatabase {
    private static BillDatabase INSTANCE;

    //单例模式，节省能源
    public static synchronized BillDatabase getInstance(Context context){
        if (INSTANCE==null){
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(), BillDatabase.class,"Bill_database")
                    .allowMainThreadQueries()
                    .build();

        }
        return INSTANCE;
    }
    public abstract BillDao getBillDao();
}
