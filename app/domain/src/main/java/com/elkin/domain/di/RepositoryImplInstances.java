package com.elkin.domain.di;

import android.content.Context;

import com.elkin.domain.repository.AttemptsRepositoryImpl;
import com.elkin.domain.repository.UserRepositoryImpl;

public abstract class RepositoryImplInstances {
    private static UserRepositoryImpl instanceRepoImplIns = null;
    private static AttemptsRepositoryImpl instanceAttemptRepoIns = null;

    public static UserRepositoryImpl getUserRepositoryImpl(Context context) {
        if (instanceRepoImplIns == null) {
            synchronized (context) {
                instanceRepoImplIns = getUserRepositoryImplInstance(context);
            }
        }
        return instanceRepoImplIns;
    }

    public static AttemptsRepositoryImpl getAttemptRepositoryImpl(Context context) {
        if (instanceAttemptRepoIns == null) {
            synchronized (context) {
                instanceAttemptRepoIns = getAttemptRepositoryImplInstance(context);
            }
        }
        return instanceAttemptRepoIns;
    }

    private static UserRepositoryImpl getUserRepositoryImplInstance(Context context){
        return new UserRepositoryImpl(context);
    }

    private static AttemptsRepositoryImpl getAttemptRepositoryImplInstance(Context context){
        return new AttemptsRepositoryImpl(context);
    }
}