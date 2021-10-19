package com.example.music_player;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    TextView name,starttime,stoptime;
    SeekBar seekmusic;
    Button btnpause,btnnext,btnprev;


    List<AudioDetails> mysongs;
    String sname;
    static MediaPlayer mediaPlayer;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        name = findViewById(R.id.txtsn);
        btnpause = findViewById(R.id.btnpause);
        btnnext = findViewById(R.id.btnnext);
        btnprev = findViewById(R.id.btnprev);
        seekmusic = findViewById(R.id.seekbar);
        starttime = findViewById(R.id.txtsstart);
        stoptime = findViewById(R.id.txtsstop);


       if(mediaPlayer!=null)
        {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        mysongs = new ArrayList<>();

        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        mysongs = bundle.getParcelableArrayList("LIST");
        String sn = i.getStringExtra("name");
        position = bundle.getInt("pos");
        String data = i.getStringExtra("data");
        Uri uri = Uri.parse(mysongs.get(position).getPath());
        name.setText(sn);

        mediaPlayer = MediaPlayer.create(getApplicationContext(),uri);
        mediaPlayer.start();
        btnpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    btnpause.setBackgroundResource(R.drawable.ic_play);
                    mediaPlayer.pause();
                }
                else{
                    btnpause.setBackgroundResource(R.drawable.ic_pause);
                    mediaPlayer.start();
                }
            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                btnnext.performClick();
            }
        });
        seekmusic.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());

            }
        });
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.release();
                position = (position+1)%mysongs.size();
                Uri u = Uri.parse(mysongs.get(position).getPath());
                mediaPlayer = MediaPlayer.create(getApplicationContext(),u);
                String sn1 = mysongs.get(position).getName();
                name.setText(sn1);
                btnpause.setBackgroundResource(R.drawable.ic_pause);
                mediaPlayer.start();

            }
        });
        btnprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.release();
                position = ((position-1)<0)?(mysongs.size()-1):(position-1)%mysongs.size();
                Uri u = Uri.parse(mysongs.get(position).getPath());
                mediaPlayer = MediaPlayer.create(getApplicationContext(),u);
                String sn1 = mysongs.get(position).getName();
                name.setText(sn1);
                btnpause.setBackgroundResource(R.drawable.ic_pause);
                mediaPlayer.start();
            }
        });

    }
    @Override
    public void onBackPressed() {
        mediaPlayer.stop();
        finish();
    }
}