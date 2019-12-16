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
import com.example.maru.model.Reunion;

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



    ArrayList<Reunion> reunionTest = new ArrayList<>();
        reunionTest.add(new Reunion("16h00","C","Blabla", Arrays.asList("blabla@blabla.com"),"16/12/19"));
        reunionTest.add(new Reunion("16h00","C","Blabla", Arrays.asList("blabla@blabla.com"),"16/12/19"));
        reunionTest.add(new Reunion("16h00","C","Blabla", Arrays.asList("blabla@blabla.com"),"16/12/19"));
        reunionTest.add(new Reunion("16h00","C","Blabla", Arrays.asList("blabla@blabla.com"),"16/12/19"));


    RecyclerView recyclerView = findViewById(R.id.activity_list_reunion_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    adapter = new ReunionAdapter(this, reunionTest);
        recyclerView.setAdapter(adapter);
}
}
