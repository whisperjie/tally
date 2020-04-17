package com.whisper.tally.data.entity;


import android.text.Editable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


import java.util.Date;


import lombok.Data;


@Data
@Entity
public class Bill {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int type = 0;//1 收入 0 支出
    public String remark = "";//备注
    public Double value = 0.0;//金额
    public String category = "其他种类";//消费种类
    public Date time=new Date();//时间戳
}
