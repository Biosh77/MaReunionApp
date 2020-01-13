package com.example.maru.list_reunion;


import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.maru.R;
import com.example.maru.api.ApiService;
import com.example.maru.base.BaseActivity;
import com.example.maru.event.DeleteReunionEvent;
import com.example.maru.model.Reunion;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import static com.example.maru.list_reunion.FormActivity.KEY_REUNION;


public class ListReunionActivity extends BaseActivity {


    protected ImageButton add_reunion;
    private ReunionAdapter adapter;
    private final static int REQUEST_DETAIL=1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_reunion);
        this.add_reunion = findViewById(R.id.add_reunion);


        add_reunion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(ListReunionActivity.this, FormActivity.class),REQUEST_DETAIL);
            }
        });


        final ArrayList<Reunion> reunions = new ArrayList<>();



        RecyclerView recyclerView = findViewById(R.id.activity_list_reunion_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ReunionAdapter(this, reunions);
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
        //ApiService.deleteReunion(event.reunion);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_DETAIL && resultCode==RESULT_OK){
            
        }
    }
}

