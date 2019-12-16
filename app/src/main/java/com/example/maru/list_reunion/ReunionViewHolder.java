package com.example.maru.list_reunion;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maru.R;

public class ReunionViewHolder extends RecyclerView.ViewHolder {

    ImageButton deleteButton;
    TextView myTextView;


    public ReunionViewHolder(@NonNull View itemView) {
        super(itemView);

        deleteButton = itemView.findViewById(R.id.item_list_user_delete_button);
        myTextView = itemView.findViewById(R.id.fragment_main_item_reunion);
    }

}
