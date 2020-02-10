package com.example.maru.list_reunion;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.maru.R;
import com.example.maru.event.DeleteReunionEvent;
import com.example.maru.model.Reunion;
import org.greenrobot.eventbus.EventBus;
import java.util.List;
import java.util.Random;

public class ReunionAdapter extends RecyclerView.Adapter<ReunionViewHolder> {

    private List<Reunion> reunions;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;





    public ReunionAdapter(Context context, List<Reunion> reunions) {

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
        final Reunion reunion = reunions.get(position);
        holder.myTextView.setText(reunion.getPlace()+" - "+reunion.getHour()+" - "+reunion.getSubject()+" - "+ reunion.getDate());
        holder.myTextView2.setText(reunion.getMail()+", ");

        holder.myTextView.setTypeface(null, Typeface.BOLD);

        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        holder.myImageView.setColorFilter(color);

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteReunionEvent(reunion));
            }
        });

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