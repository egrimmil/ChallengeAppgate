package com.elkin.challengeappgate.data.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.elkin.challengeappgate.data.db.entities.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM users")
    List<User> getAllUsers();

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    Long insertAllUsers(User users);

    @Delete
    void deleteUser(User user);

    @Query("SELECT * FROM users WHERE uid == :id")
    User findById(int id);

    @Query("SELECT * FROM users WHERE name == :name")
    User findByName(String name);
}
