package edu.whut.liziyan.muse;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class PlayerActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer = new MediaPlayer();
    private TextView tv_start;
    private TextView tv_end;
    private SeekBar seekbar;
    private Button stop,pre,play,next;
    private Boolean isSeekBarChanging = false;
    private String songURL;
    private Timer timer;
    private TimerTask timerTask;
    private Song song;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_play);

        song = this.getIntent().getParcelableExtra("song");
        songURL = song.getSongPath();

        tv_start = findViewById(R.id.music_start);
        tv_end = findViewById(R.id.music_stop);
        stop = findViewById(R.id.stop);
        pre = findViewById(R.id.pre);
        play = findViewById(R.id.play);
        next = findViewById(R.id.next);
        seekbar = findViewById(R.id.audio_bar);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int duration=mediaPlayer.getDuration()/1000;
                int position=mediaPlayer.getCurrentPosition()/1000;
                tv_start.setText(calculateTime(position));
                tv_end.setText(calculateTime(duration));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isSeekBarChanging = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress=seekBar.getProgress();
                mediaPlayer.seekTo(progress);
                tv_start.setText(calculateTime(mediaPlayer.getCurrentPosition()/1000));
                isSeekBarChanging = false;
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initMediaPlayer();
                playMusic();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetMusic();
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.seekTo(0);
            }
        });

    }

    private void initMediaPlayer(){
        try{
            if(song.getSongPath()==null)
                Toast.makeText(PlayerActivity.this, "songPath is null", Toast.LENGTH_SHORT).show();
            mediaPlayer.setDataSource(songURL);
            mediaPlayer.prepare();
        }catch (Exception e){
            e.printStackTrace();
        }
        int duration = mediaPlayer.getDuration()/1000;
        int position = mediaPlayer.getCurrentPosition();
        tv_start.setText(calculateTime(position/1000));
        tv_end.setText(calculateTime(duration));
    }

    private void playMusic() {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            int duration = mediaPlayer.getDuration();
            seekbar.setMax(duration);
            timer = new Timer();
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    if (isSeekBarChanging)
                        return;
                    seekbar.setProgress(mediaPlayer.getCurrentPosition());
                }
            };
            timer.schedule(timerTask, 0, 10);
        }else{
            mediaPlayer.pause();
        }
    }

    private void resetMusic(){
        if(mediaPlayer.isPlaying())
            mediaPlayer.pause();
        mediaPlayer.seekTo(0);
    }

    public String calculateTime(int time){
        int minute;
        int second;
        if(time>=60){
            minute=time/60;
            second=time%60;
            return minute+":"+second;
        }else if(time<60){
            second=time;
            return "0:"+second;
        }
        return null;
    }

    protected void onDestroy(){
        super.onDestroy();
        if(timer!=null){
            timer.cancel();
            timer.purge();
            timer = null;
        }
        if(mediaPlayer!=null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}