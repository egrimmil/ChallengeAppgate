package com.elkin.challengeappgate.data.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.elkin.challengeappgate.data.db.entities.Attempts;

import java.util.List;

@Dao
public interface AttemptDao {

    @Query("SELECT * FROM attempts")
    List<Attempts> getAllAttempts();

    @Insert
    void insertAttempt(Attempts attempts);

    @Query("SELECT * FROM attempts WHERE userId == :id")
    Attempts findByUserId(int id);
}