package com.elkin.challengeappgate.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.elkin.challengeappgate.data.db.dao.AttemptDao;
import com.elkin.challengeappgate.data.db.dao.UserDao;
import com.elkin.challengeappgate.data.db.entities.Attempts;
import com.elkin.challengeappgate.data.db.entities.User;

@Database(entities = {User.class, Attempts.class}, version = 1)
public abstract class ChallengeDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract AttemptDao attemptDao();

    private static ChallengeDatabase instanceDb = null;

    public static ChallengeDatabase getDataBase(Context context) {
        if (instanceDb == null) {
            synchronized (context) {
                instanceDb = buildDataBase(context);
            }
        }
        return instanceDb;
    }

    private static ChallengeDatabase buildDataBase(Context context) {
        return Room.databaseBuilder(context.getApplicationContext(), ChallengeDatabase.class, "challengeAppGate").build();
    }
}
