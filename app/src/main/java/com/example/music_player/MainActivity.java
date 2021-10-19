package com.example.music_player;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyAdapter.OnRecyclerviewClickListener{
    private MyAdapter adapter;
    private List<AudioDetails> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        loadaudio(this);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(list,getApplicationContext());
        recyclerView.setAdapter(adapter);
        adapter.setOnRecyclerViewClickListener(this);


    }
    private List<AudioDetails> loadaudio(Context context) {
        list = new ArrayList<>();
        Uri uri = MediaStore.Audio.Media.INTERNAL_CONTENT_URI;
        String[] str = {
               MediaStore.Audio.AudioColumns.TITLE,
                MediaStore.Audio.AudioColumns.DATA
          };
        Cursor cursor = context.getContentResolver().query(uri,str,null,null,null);
        if(cursor!=null){
            while (cursor.moveToNext()){
                list.add(new AudioDetails(
                        cursor.getString(0),
                        cursor.getString(1)
                ));
            }
            cursor.close();
        }
        return list;
    }

    @Override
    public void OnItemClick(int position) {
        Intent intent = new Intent(MainActivity.this,MainActivity2.class);
        Bundle bundle= new Bundle();
        bundle.putParcelableArrayList("LIST", (ArrayList<? extends Parcelable>) list);
        AudioDetails item = list.get(position);
        intent.putExtra("data",item.getPath());
        intent.putExtra("name",item.getName());
        intent.putExtra("pos",position);
        intent.putExtras(bundle);
        startActivity(intent);

    }

}