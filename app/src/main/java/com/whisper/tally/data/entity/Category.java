package com.whisper.tally.data.entity;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Data;


@Data
@Entity
public class Category {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name="";//得使用public关键字
 }
