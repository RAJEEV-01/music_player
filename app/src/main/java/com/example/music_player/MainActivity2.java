package com.example.music_player;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.audiofx.Visualizer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.gauravk.audiovisualizer.visualizer.BarVisualizer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    TextView name,starttime,stoptime;
    SeekBar seekmusic;
    Button btnpause,btnnext,btnprev;
    BarVisualizer visualizer;

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
        visualizer = findViewById(R.id.blast);

       if(mediaPlayer!=null)
        {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        mysongs = new ArrayList<>();

        Intent i = getIntent();
        String sn = i.getStringExtra("name");
        position = i.getIntExtra("pos",0);
        String data = i.getStringExtra("data");
        Uri uri = Uri.parse(data);
        name.setText(sn);
        String sn1 = i.getStringExtra("name1");
        String data1 = i.getStringExtra("data1");

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
        /*btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.release();
                Uri u = Uri.parse(data1);
                mediaPlayer = MediaPlayer.create(getApplicationContext(),u);
                name.setText(sn1);
                btnpause.setBackgroundResource(R.drawable.ic_pause);
                mediaPlayer.start();

            }
        });*/

    }
    @Override
    public void onBackPressed() {
        mediaPlayer.stop();
        finish();
    }
}