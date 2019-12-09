package com.example.maru.event;

import com.example.maru.model.Reunion;

public class DeleteReunionEvent {


        public Reunion reunion;


        public DeleteReunionEvent(Reunion reunion) {
            this.reunion = reunion;
        }
}


//public void onDeleteReunion(DeleteReunionEvent event) {
////    xxxx.deleteReunion(event.reunion);
////

// Event

// holder.xxxxxx.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EventBus.getDefault().post(new DeleteReunionEvent(reunion));
//            }
//        });

// onClick pour lancer la suppression quand on appuie