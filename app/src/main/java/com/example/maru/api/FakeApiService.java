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
    public void addReunion(Reunion reunion) {
        reunions.add(reunion);
    }

    @Override
    public void deleteReunion(Reunion reunion) {
        reunions.remove(reunion);
    }

    @Override
    public List<Reunion> filterReunion(String salle, String date) {
        List<Reunion> reunionFilter = new ArrayList<>();
        for (int i = 0; i < reunions.size(); i++) {
            if (salle.equals(reunions.get(i).getPlace()) && date.equals(reunions.get(i).getDate())) {
                reunionFilter.add(reunions.get(i));
            } else if (salle.equals(reunions.get(i).getPlace()) && date.isEmpty()) {
                reunionFilter.add(reunions.get(i));
            } else if (date.equals(reunions.get(i).getDate()) && salle.isEmpty()) {
                reunionFilter.add(reunions.get(i));
            } else if (date.isEmpty() && salle.isEmpty()) {
                reunionFilter.add(reunions.get(i));
            }
        }
        return reunionFilter;
    }
}



