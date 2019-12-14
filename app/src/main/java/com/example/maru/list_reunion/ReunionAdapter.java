package com.example.maru.list_reunion;


import android.view.ViewGroup;



import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.maru.model.Reunion;

import java.util.ArrayList;
import java.util.List;

public class ReunionAdapter extends RecyclerView.Adapter<ReunionViewHolder> {

    private List<Reunion> reunions = new ArrayList<Reunion>();



    public ReunionAdapter(List<Reunion> reunions) {

        this.reunions = reunions;
    }


    @NonNull
    @Override
    public ReunionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ReunionViewHolder holder, int position) {



    }

    @Override
    public int getItemCount() {
        return 0;
    }
}