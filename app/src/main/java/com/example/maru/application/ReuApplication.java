package com.example.maru.application;

import android.app.Application;

import androidx.annotation.VisibleForTesting;

import com.example.maru.di.Injection;
import com.example.maru.repository.ReunionRepository;

public class ReuApplication extends Application {

    private ReunionRepository reunionRepository;

    // ---

    public ReunionRepository getReunionRepository() {
        if (reunionRepository == null) reunionRepository = Injection.createReunionRepository();
        return reunionRepository;
    }

    @VisibleForTesting
    public void resetReunionRepository() {
        reunionRepository = null;
    }

}
