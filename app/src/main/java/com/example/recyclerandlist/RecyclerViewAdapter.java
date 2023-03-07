package com.example.recyclerandlist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {
    ArrayList<RecyclerItem> recyclerItems;
    Context context;

    public RecyclerViewAdapter(ArrayList<RecyclerItem> recyclerItems, Context context) {
        this.recyclerItems = recyclerItems;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent,false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        Log.i("n", "Size:" + recyclerItems.size());
        holder.namel.setText(recyclerItems.get(position).name);
        holder.emaill.setText(recyclerItems.get(position).email);
        holder.commentl.setText(recyclerItems.get(position).comment);
        CharSequence text = recyclerItems.get(position).email;
        if ((text.charAt(0) == 'A') || (text.charAt(0) == 'B')
                || (text.charAt(0) == 'C') || (text.charAt(0) == 'D')
        ) {
            holder.avatars.setImageResource(R.drawable.icon1);
        } else if ((text.charAt(0) == 'E') || (text.charAt(0) == 'F')
                || (text.charAt(0) == 'G') || (text.charAt(0) == 'H')
        ) {
            holder.avatars.setImageResource(R.drawable.icon2);
        } else if ((text.charAt(0) == 'I') || (text.charAt(0) == 'J')
                || (text.charAt(0) == 'K'))
        {
            holder.avatars.setImageResource(R.drawable.icon3);
        } else if ((text.charAt(0) == 'L') || (text.charAt(0) == 'M')
                || (text.charAt(0) == 'N'))
        {
            holder.avatars.setImageResource(R.drawable.icon4);
        } else if ((text.charAt(0) == 'O') || (text.charAt(0) == 'P')
                || (text.charAt(0) == 'Q'))
        {
            holder.avatars.setImageResource(R.drawable.icon5);
        } else if ((text.charAt(0) == 'R') || (text.charAt(0) == 'S')
                || (text.charAt(0) == 'T'))
        {
            holder.avatars.setImageResource(R.drawable.icon6);
        } else {
            holder.avatars.setImageResource(R.drawable.icon7);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Pressed", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return recyclerItems.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{
        TextView namel;
        TextView emaill;
        TextView commentl;
        ImageView avatars;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            namel = itemView.findViewById(R.id.name_text);
            emaill = itemView.findViewById(R.id.email_text);
            commentl = itemView.findViewById(R.id.message_text);
            avatars = itemView.findViewById(R.id.avatar);
        }
    }
}
