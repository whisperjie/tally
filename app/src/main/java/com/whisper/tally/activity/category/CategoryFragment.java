package com.whisper.tally.activity.category;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.whisper.tally.R;
import com.whisper.tally.data.entity.Category;
import com.whisper.tally.viewmodel.CategoryViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {
    Button mBtnClear,mBtnAdd;
    RecyclerView recyclerViewCategory;
    CategoryAdapter categoryAdapter;
    CategoryViewModel categoryViewModel;
    FloatingActionButton floatingActionButton;

    public CategoryFragment() {
        // Required empty public constructor
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.category_menu,menu);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerViewCategory=getView().findViewById(R.id.rcv_category);
        floatingActionButton=requireActivity().findViewById(R.id.fab_category_add);
        categoryAdapter=new CategoryAdapter(getActivity().getApplication());
        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewCategory.setAdapter(categoryAdapter);
        categoryViewModel= ViewModelProviders.of(this).get(CategoryViewModel.class);

       // mBtnClear=getView().findViewById(R.id.btn_category_clear);
       // mBtnAdd=getView().findViewById(R.id.btn_to_category_add);
      /*  categoryViewModel.getAllCategories().observe(getActivity(), new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
              //  Log.d("MYTAG",categories.toString());
                categoryAdapter.setAllCategories(categories);
                categoryAdapter.notifyDataSetChanged();
            }
        });*/
      categoryViewModel.getAllCategories().observe(getActivity(), new Observer<List<Category>>() {
          @Override
          public void onChanged(List<Category> categories) {
              categoryAdapter.setAllCategories(categories);
             categoryAdapter.notifyDataSetChanged();
          }
      });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller= Navigation.findNavController(v);
                controller.navigate(R.id.action_categoryFragment_to_categoryAddFragment);
            }
        });

      /*  mBtnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryViewModel.deleteAllCategory();
            }
        });


        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller= Navigation.findNavController(v);
                controller.navigate(R.id.action_categoryFragment_to_categoryAddFragment);
            }
        });*/
    }
}
