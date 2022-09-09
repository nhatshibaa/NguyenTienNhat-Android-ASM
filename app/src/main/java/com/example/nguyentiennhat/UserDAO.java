package com.example.nguyentiennhat;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert
    long insertUser(User user);

    @Query(" select * from user ")
    List<User> getAllUser();
}
