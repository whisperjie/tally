package com.whisper.tally.data.entity;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Bill {
    @PrimaryKey(autoGenerate = true)
    int id;
    int type;//1 收入 0 支出
    String remark;//备注
    double value;//金额
    String category;//消费种类
}
