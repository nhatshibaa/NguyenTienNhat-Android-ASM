package com.example.nguyentiennhat;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "fullName")
    public String fullName;
    @ColumnInfo(name = "email")
    public String email;
    @ColumnInfo(name = "gender")
    public String gender;
    @ColumnInfo(name = "description")
    public String description;
}
