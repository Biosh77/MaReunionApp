package com.example.maru.list_reunion;


import androidx.annotation.Nullable;


import android.os.Bundle;
import android.widget.ImageButton;
import com.example.maru.R;
import com.example.maru.base.BaseActivity;


public class ListReunionActivity extends BaseActivity {


    protected ImageButton add_reunion;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_reunion);

        this.add_reunion = findViewById(R.id.add_reunion);
    }


}
