package com.whisper.tally.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.whisper.tally.R;
import com.whisper.tally.databinding.ActivityInfoBinding;
import com.whisper.tally.user.UserData;
import com.whisper.tally.viewmodel.UserViewModel;

public class InfoActivity extends AppCompatActivity  {
    Button mBtnName,mBtnPassword,mBtnRepassword;
    EditText mEdtName,mEdtPassword,mEdtRepassword;
    UserViewModel userViewModel;
    UserData userData;
    ActivityInfoBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_info);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_info);
        userViewModel= ViewModelProviders.of(this).get(UserViewModel.class);
        userData=new UserData(getApplicationContext());
        binding.edtName.setText(userViewModel.getName(userData).getValue());
        binding.btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.edtPassword.setText("");
                binding.edtRePassword.setText("");
            }
        });
        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast;
                String name= String.valueOf(binding.edtName.getText());
                String password= String.valueOf(binding.edtPassword.getText());
                String rePassword=String.valueOf(binding.edtRePassword.getText());
                if (name.equals("")){
                   toast=Toast.makeText(InfoActivity.this,"名称为空",Toast.LENGTH_SHORT);
                    toast.show();
                }else if (!password.equals(rePassword)){
                    toast=Toast.makeText(InfoActivity.this,"两次密码不一致",Toast.LENGTH_SHORT);
                    toast.show();
                }else {
                    /*userViewModel.name.setValue(name);
                    userViewModel.password=password;*/
                    userViewModel.save(name,password,userData);
                    toast=Toast.makeText(InfoActivity.this,"修改成功",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

}
