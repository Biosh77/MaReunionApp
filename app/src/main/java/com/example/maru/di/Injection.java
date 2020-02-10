package com.example.maru.di;


import com.example.maru.api.ApiService;
import com.example.maru.api.FakeApiService;
import com.example.maru.repository.ReunionRepository;

public class Injection {

    public static ReunionRepository createReunionRepository() {
        return new ReunionRepository(new FakeApiService());
    }

    public static ApiService getNewInstanceApiService() {
        return new FakeApiService();
    }
}


