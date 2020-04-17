package com.whisper.tally.activity.category;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.whisper.tally.R;
import com.whisper.tally.data.dao.CategoryDao;
import com.whisper.tally.data.database.CategoryDatabase;
import com.whisper.tally.data.entity.Category;
import com.whisper.tally.viewmodel.CategoryViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryAddFragment extends Fragment {
    CategoryDatabase categoryDatabase;
    CategoryDao categoryDao;
    Button mBtnAddCategory;
    EditText edtCategoryName;
    CategoryViewModel categoryViewModel;

    public CategoryAddFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        categoryViewModel=new CategoryViewModel(getActivity().getApplication());
        mBtnAddCategory=getView().findViewById(R.id.btn_add_category);
        edtCategoryName=getView().findViewById(R.id.edt_Category_name);
        mBtnAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= String.valueOf(edtCategoryName.getText());
              //  Log.d("TAG",  name);
                Category category=new Category();
                category.name=name;
                categoryViewModel.insertCategory(category);
                Toast.makeText(getContext(),"添加成功",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category_add, container, false);

    }


}
