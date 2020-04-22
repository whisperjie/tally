package com.whisper.tally.data.entity;


import android.text.Editable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


import java.io.Serializable;
import java.util.Date;


import lombok.Data;


@Data
@Entity
public class Bill implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String title="";
    public int type = 0;//1 收入 0 支出
    public String remark = "";//备注
    public Double value = 0.0;//金额
    public String category = "其他分类";//消费种类
    public Date time;//时间戳
    public String timeFormated=""; //时间
}
