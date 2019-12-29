package com.example.maru.event;

import com.example.maru.model.Reunion;

public class DeleteReunionEvent {


        public Reunion reunion;


        public DeleteReunionEvent(Reunion reunion) {
            this.reunion = reunion;
        }
}

