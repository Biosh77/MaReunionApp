package com.example.maru.event;

import com.example.maru.model.Reunion;

public class AddReunionEvent {


    public Reunion reunion;


    public AddReunionEvent(Reunion reunion) {
        this.reunion = reunion;
    }
}
