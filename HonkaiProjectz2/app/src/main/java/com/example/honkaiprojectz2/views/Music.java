package com.example.honkaiprojectz2.views;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.honkaiprojectz2.MainActivity;
import com.example.honkaiprojectz2.R;

public class Music extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private Spinner spinner;
    private ImageView imageView, imageViewPlay, imageViewStop;
    private Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_music);
        spinner = findViewById(R.id.spinnerTheme);
        imageView = findViewById(R.id.ivMusic);

        imageViewPlay = findViewById(R.id.ivPlay);
        imageViewPlay.setImageResource(R.drawable.play);

        imageViewStop = findViewById(R.id.ivStop);

        btnBack = findViewById(R.id.btnBack);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.theme, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(mediaPlayer!=null){
                    mediaPlayer.release();
                }
                switch (position){
                    case 0:
                        imageView.setImageResource(R.drawable.album);
                        break;
                    case 1:
                        imageViewPlay.setImageResource(R.drawable.play);
                        imageView.setImageResource(R.drawable.jarilo);
                        mediaPlayer = MediaPlayer.create(getApplicationContext() ,R.raw.embers);
                        imageViewStop.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(mediaPlayer!=null){
                                    mediaPlayer.stop();
                                    mediaPlayer = MediaPlayer.create(getApplicationContext() ,R.raw.embers);
                                    imageViewPlay.setImageResource(R.drawable.play);
                                }
                            }
                        });
                        break;
                    case 2:
                        imageViewPlay.setImageResource(R.drawable.play);
                        imageView.setImageResource(R.drawable.xianzhou);
                        mediaPlayer = MediaPlayer.create(getApplicationContext() ,R.raw.blade);
                        imageViewStop.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(mediaPlayer!=null){
                                    mediaPlayer.stop();
                                    mediaPlayer = MediaPlayer.create(getApplicationContext() ,R.raw.blade);
                                    imageViewPlay.setImageResource(R.drawable.play);
                                }
                            }
                        });
                        break;
                    case 3:
                        imageViewPlay.setImageResource(R.drawable.play);
                        imageView.setImageResource(R.drawable.penacony);
                        mediaPlayer = MediaPlayer.create(getApplicationContext() ,R.raw.goldenhour);
                        imageViewStop.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(mediaPlayer!=null){
                                    mediaPlayer.stop();
                                    mediaPlayer = MediaPlayer.create(getApplicationContext() ,R.raw.goldenhour);
                                    imageViewPlay.setImageResource(R.drawable.play);
                                }
                            }
                        });
                        break;
                    case 4:
                        imageViewPlay.setImageResource(R.drawable.play);
                        imageView.setImageResource(R.drawable.outofcontrol);
                        mediaPlayer = MediaPlayer.create(getApplicationContext() ,R.raw.starrail);
                        imageViewStop.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(mediaPlayer!=null){
                                    mediaPlayer.stop();
                                    mediaPlayer = MediaPlayer.create(getApplicationContext() ,R.raw.starrail);
                                    imageViewPlay.setImageResource(R.drawable.play);
                                }
                            }
                        });
                        break;
                    case 5:
                        imageViewPlay.setImageResource(R.drawable.play);
                        imageView.setImageResource(R.drawable.outofcontrol);
                        mediaPlayer = MediaPlayer.create(getApplicationContext() ,R.raw.spacewalk);
                        imageViewStop.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(mediaPlayer!=null){
                                    mediaPlayer.stop();
                                    mediaPlayer = MediaPlayer.create(getApplicationContext() ,R.raw.spacewalk);
                                    imageViewPlay.setImageResource(R.drawable.play);
                                }
                            }
                        });
                        break;
                    case 6:
                        imageViewPlay.setImageResource(R.drawable.play);
                        imageView.setImageResource(R.drawable.outofcontrol);
                        mediaPlayer = MediaPlayer.create(getApplicationContext() ,R.raw.takethejourney);
                        imageViewStop.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(mediaPlayer!=null){
                                    mediaPlayer.stop();
                                    mediaPlayer = MediaPlayer.create(getApplicationContext() ,R.raw.takethejourney);
                                    imageViewPlay.setImageResource(R.drawable.play);
                                }
                            }
                        });
                        break;
                    case 7:
                        imageViewPlay.setImageResource(R.drawable.play);
                        imageView.setImageResource(R.drawable.ofsnowandember);
                        mediaPlayer = MediaPlayer.create(getApplicationContext() ,R.raw.conflictundraped);
                        imageViewStop.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(mediaPlayer!=null){
                                    mediaPlayer.stop();
                                    mediaPlayer = MediaPlayer.create(getApplicationContext() ,R.raw.conflictundraped);
                                    imageViewPlay.setImageResource(R.drawable.play);
                                }
                            }
                        });
                        break;
                    case 8:
                        imageViewPlay.setImageResource(R.drawable.play);
                        imageView.setImageResource(R.drawable.ofsnowandember);
                        mediaPlayer = MediaPlayer.create(getApplicationContext() ,R.raw.fate);
                        imageViewStop.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(mediaPlayer!=null){
                                    mediaPlayer.stop();
                                    mediaPlayer = MediaPlayer.create(getApplicationContext() ,R.raw.fate);
                                    imageViewPlay.setImageResource(R.drawable.play);
                                }
                            }
                        });
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        imageViewPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer!=null){
                    if(mediaPlayer.isPlaying()){
                        mediaPlayer.pause();
                        imageViewPlay.setImageResource(R.drawable.play);
                    }
                    else{
                        mediaPlayer.start();
                        imageViewPlay.setImageResource(R.drawable.pause);
                    }
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer!=null){
                    mediaPlayer.stop();
                }
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}