package com.example.maru.api;

import com.example.maru.model.Reunion;

import java.util.ArrayList;
import java.util.List;



public class FakeApiService implements ApiService {


    private List<Reunion> reunions = new ArrayList<Reunion>();



    @Override
    public List<Reunion> getReunions() {
        return reunions;
    }

    @Override
    public void addReunion( Reunion reunion) {
        reunions.add(reunion);
    }

    @Override
    public void deleteReunion(Reunion reunion) {
        reunions.remove(reunion);
    }
}
