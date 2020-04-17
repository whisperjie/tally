package com.whisper.tally.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.whisper.tally.data.dao.CategoryDao;
import com.whisper.tally.data.entity.Category;
import com.whisper.tally.util.MyConverter;

@TypeConverters({MyConverter.class})
@Database(entities = {Category.class},version = 1,exportSchema = false)
public abstract class CategoryDatabase extends RoomDatabase {
    private static CategoryDatabase INSTANCE;

    //单例模式，节省能源
    public static synchronized CategoryDatabase getInstance(Context context){
        if (INSTANCE==null){
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(),CategoryDatabase.class,"Category_database")
                    .allowMainThreadQueries()
                    .build();

        }
        return INSTANCE;
    }
    public abstract CategoryDao getCategoryDao();
}
