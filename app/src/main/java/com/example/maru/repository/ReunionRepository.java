package com.example.maru.repository;

import com.example.maru.api.ApiService;
import com.example.maru.model.Reunion;

import java.util.List;

public class ReunionRepository {

    private final ApiService apiService;

    public ReunionRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public List<Reunion> getReunions() {
        return apiService.getReunions();
    }

    public void generateRandomReunion() {
        apiService.generateRandomReunion();
    }

    public void deleteReunion(Reunion reunion) {
        apiService.deleteReunion(reunion);
    }
}


