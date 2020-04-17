package com.whisper.tally.viewmodel;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.whisper.tally.data.dao.CategoryDao;
import com.whisper.tally.data.database.CategoryDatabase;
import com.whisper.tally.data.database.MyDataBase;
import com.whisper.tally.data.entity.Category;

import java.util.List;

public class CategoryViewModel extends AndroidViewModel {
    public MyDataBase myDataBase;
    public CategoryDao categoryDao;
    public LiveData<List<Category>> allCategories;

    public LiveData<List<Category>> getAllCategories() {
        return  allCategories;
    }


    public CategoryViewModel(Application application) {
        super(application);
        myDataBase = MyDataBase.getInstance(application);
        categoryDao = myDataBase.getCategoryDao();
        allCategories=  categoryDao.getAllCategories();
    //categoryDao.getAllCategories();
    }

    public void insertCategory(Category... categories) {
        new InsertAsyncTask(categoryDao).execute(categories);
    }

    public void updateCategory(Category... categories) {
        new UpdatetAsyncTask(categoryDao).execute(categories);
    }

    public void deleteCategory(Category... categories) {
        new DeleteAsyncTask(categoryDao).execute(categories);
    }

    public void deleteAllCategory() {
        new DeleteAlltAsyncTask(categoryDao).execute();
    }

    static class InsertAsyncTask extends AsyncTask<Category, Void, Void> {
        private CategoryDao categoryDao;

        public InsertAsyncTask(CategoryDao categoryDao) {
            this.categoryDao = categoryDao;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            categoryDao.insertCategories(categories);
            return null;
        }
    }

    static class DeleteAsyncTask extends AsyncTask<Category, Void, Void> {
        private CategoryDao categoryDao;

        public DeleteAsyncTask(CategoryDao categoryDao) {
            this.categoryDao = categoryDao;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            categoryDao.deleteCategories(categories);
            return null;
        }
    }

    static class UpdatetAsyncTask extends AsyncTask<Category, Void, Void> {
        private CategoryDao categoryDao;

        public UpdatetAsyncTask(CategoryDao categoryDao) {
            this.categoryDao = categoryDao;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            categoryDao.updateCategories(categories);
            return null;
        }
    }

    static class DeleteAlltAsyncTask extends AsyncTask<Void, Void, Void> {
        private CategoryDao categoryDao;

        public DeleteAlltAsyncTask(CategoryDao categoryDao) {
            this.categoryDao = categoryDao;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            categoryDao.deleteAllCategories();
            return null;
        }
    }
}
