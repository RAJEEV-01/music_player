package com.example.music_player;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private List<AudioDetails> list;
    private Context context;
    private OnRecyclerviewClickListener listener;

    public interface OnRecyclerviewClickListener{
        void OnItemClick(int position);
    }
    public void setOnRecyclerViewClickListener(OnRecyclerviewClickListener listener){
        this.listener = listener;
    }

    public MyAdapter(List<AudioDetails> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.music_list,parent,false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.textView.setText(list.get(position).getName());
            /*e->{
                MediaPlayer player=MediaPlayer.create(e.getContext(),Uri.parse(list.get(position).getPath()));
                player.start();*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        RelativeLayout relativeLayout;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.songname);
            relativeLayout =itemView.findViewById(R.id.relativelayout);
            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!=null){
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.OnItemClick(position);
                        }

                    }
                }
            });

        }
    }
}
