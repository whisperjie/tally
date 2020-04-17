package com.whisper.tally.activity.category;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.whisper.tally.R;
import com.whisper.tally.data.entity.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{
    List<Category> allCategories=new ArrayList<>();

    public void setAllCategories(List<Category> allCategories) {
        this.allCategories = allCategories;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //return null;
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View itemView=layoutInflater.inflate(R.layout.cell_category,parent,false);
        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category=allCategories.get(position);
        holder.textViewNumber.setText(String.valueOf(position+1));
        holder.textViewName.setText(String.valueOf(category.name));
    }

    @Override    public int getItemCount() {
        return allCategories.size();
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder{
        TextView textViewNumber,textViewName;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNumber=itemView.findViewById(R.id.tev_category_number);
            textViewName=itemView.findViewById(R.id.tev_category_name);
        }
    }
}
