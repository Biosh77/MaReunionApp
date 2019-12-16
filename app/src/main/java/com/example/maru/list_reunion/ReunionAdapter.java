package com.example.maru.list_reunion;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.maru.R;
import com.example.maru.model.Reunion;

import java.util.ArrayList;
import java.util.List;

public class ReunionAdapter extends RecyclerView.Adapter<ReunionViewHolder> {

    private List<Reunion> reunions;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;





    public ReunionAdapter(Context context, ArrayList<Reunion> reunions) {

        this.reunions = reunions;
        this.mInflater = LayoutInflater.from(context);

    }


    @NonNull
    @Override
    public ReunionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.cell_reunion, parent, false);
        return new ReunionViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ReunionViewHolder holder, int position) {
        Reunion reunion = reunions.get(position);
        holder.myTextView.setText(reunion.getDate()+" " + reunion.getHour()+" "+reunion.getPlace()+" "+reunion.getSubject());


    }
    @Override
    public int getItemCount() {
        return reunions.size();
    }



    Reunion getItem(int id) {
        return reunions.get(id);
    }


    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}