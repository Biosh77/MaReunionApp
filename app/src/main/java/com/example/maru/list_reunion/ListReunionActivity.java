package com.example.maru.list_reunion;


import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


import com.example.maru.R;
import com.example.maru.base.BaseActivity;
import com.example.maru.event.DeleteReunionEvent;
import com.example.maru.model.Reunion;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Arrays;


public class ListReunionActivity extends BaseActivity {


    protected ImageButton add_reunion;
    private ReunionAdapter adapter;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_reunion);
        this.add_reunion = findViewById(R.id.add_reunion);



        add_reunion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListReunionActivity.this, FormActivity.class));
            }
        });


        final ArrayList<Reunion> reunionTest = new ArrayList<>();
        reunionTest.add(new Reunion("16h00", "C", "Blabla", Arrays.asList("blabla@blabla.com"),"lundi 12"));
        reunionTest.add(new Reunion("16h00", "C", "Blabla", Arrays.asList("blabla@blabla.com"),"mardi 17"));
        reunionTest.add(new Reunion("16h00", "C", "Blabla", Arrays.asList("blabla@blabla.com"), "mercredi 22"));
        reunionTest.add(new Reunion("16h00", "C", "Blabla", Arrays.asList("blabla@blabla.com"),"vendred 4"));


        RecyclerView recyclerView = findViewById(R.id.activity_list_reunion_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ReunionAdapter(this, reunionTest);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onDeleteReunion(DeleteReunionEvent event) {

    }
}

