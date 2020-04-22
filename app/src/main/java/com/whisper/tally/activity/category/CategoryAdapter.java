package com.whisper.tally.activity.category;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.whisper.tally.R;
import com.whisper.tally.data.entity.Category;
import com.whisper.tally.viewmodel.CategoryViewModel;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    List<Category> allCategories = new ArrayList<>();
    CategoryViewModel categoryViewModel;
    Application application;

    public void setAllCategories(List<Category> allCategories) {
        this.allCategories = allCategories;
    }

    public CategoryAdapter(Application application) {
        this.application = application;
        categoryViewModel = new CategoryViewModel(application);
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //return null;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.cell_category, parent, false);
        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
         final Category category = allCategories.get(position);
        holder.textViewNumber.setText(String.valueOf(position + 1));
        holder.textViewName.setText(String.valueOf(category.name));
        holder.imageButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryViewModel.deleteCategory(category);
            }
        });
        holder.imageButtonToDetial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //categoryViewModel.deleteCategory(category);
                //Intent intent=new Intent()
                Log.d("MYTAG","被点击");
            }
        });
    }

    @Override
    public int getItemCount() {
        return allCategories.size();
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNumber, textViewName;
        ImageButton imageButtonToDetial, imageButtonDelete;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNumber = itemView.findViewById(R.id.tev_category_number);
            textViewName = itemView.findViewById(R.id.tev_category_name);
            imageButtonToDetial = itemView.findViewById(R.id.imb_category_to_detial);
            imageButtonDelete = itemView.findViewById(R.id.imb_category_delete);
            imageButtonToDetial = itemView.findViewById(R.id.imb_category_to_detial);
            imageButtonDelete = itemView.findViewById(R.id.imb_category_delete);
        }
    }
}
