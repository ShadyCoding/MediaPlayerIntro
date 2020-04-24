package com.shady.mediaplayerintro;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;
    private Button playButton;
    private SeekBar musicSB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        musicSB = findViewById(R.id.musicSB);

        mMediaPlayer = new MediaPlayer();
        mMediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.music);
        musicSB.setMax(mMediaPlayer.getDuration());

        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                int duration = mp.getDuration();
                String length = String.valueOf(duration/1000);
                Toast.makeText(getApplicationContext(), "music lenght "+ length, Toast.LENGTH_SHORT).show();
            }
        });

        musicSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    mMediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        playButton = findViewById(R.id.button);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mMediaPlayer.isPlaying()) {
                    pause();
                }else {
                    play();
                }
            }
        });
    }

    public void pause(){
        if (mMediaPlayer != null){
            mMediaPlayer.pause();
            playButton.setText("Play");
        }
    }

    public void play(){
        if (mMediaPlayer != null){
            mMediaPlayer.start();
            playButton.setText("Pause");
        }
    }

    @Override
    protected void onDestroy() {
        if(mMediaPlayer != null && mMediaPlayer.isPlaying()){
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
        super.onDestroy();
    }
}
