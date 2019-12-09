package com.example.maru.base;

import androidx.appcompat.app.AppCompatActivity;

import com.example.maru.repository.ReunionRepository;
import com.example.maru.application.ReuApplication;

public abstract class  BaseActivity extends AppCompatActivity {

    public ReunionRepository getReunionRepository() {
        return ((ReuApplication) getApplication()).getReunionRepository();
    }
}


