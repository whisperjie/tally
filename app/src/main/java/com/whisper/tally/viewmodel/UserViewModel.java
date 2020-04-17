package com.whisper.tally.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.whisper.tally.user.UserData;

public class UserViewModel extends ViewModel {
    public MutableLiveData<String> name;
    public String password;



    public MutableLiveData<String> getName(UserData userData) {
        if (name == null) {
            name=new MutableLiveData<>();
            if (userData.name.equals("")) {
                name.setValue("默认账户");
            }else{
                name.setValue(userData.name);
            }
        }
        return name;
}

    public String getPassword(UserData userData) {
        if (password == null) {
            password=userData.password;
        }
        return password;
    }

    public void save(String name1, String password1,UserData userData) {
        name.setValue(name1);
        password=password1;
        userData.name=name1;
        userData.password=password1;
        userData.save();
    }
}
