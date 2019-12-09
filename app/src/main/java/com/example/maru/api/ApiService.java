package com.example.maru.api;


import com.example.maru.model.Reunion;

import java.util.List;

public interface ApiService {
    List<Reunion> getReunions();
    void addReunion(Reunion reunion);
    void deleteReunion(Reunion reunion);
}
