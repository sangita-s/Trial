package generisches.lab.trial;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Add extends AppCompatActivity implements View.OnClickListener{

    Button play, pause, stop;
    MediaPlayer mMediaPlayer;
    int pauseCurrentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        play = findViewById(R.id.btnPlayMusik);
        pause = findViewById(R.id.btn_pause);
        stop = findViewById(R.id.btn_stop);

        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPlayMusik:
                if(mMediaPlayer == null) {
                    mMediaPlayer = MediaPlayer.create(this, R.raw.capitalletters);
                    mMediaPlayer.start();
                }
                else if(!mMediaPlayer.isPlaying()){
                    mMediaPlayer.seekTo(pauseCurrentPosition);
                    mMediaPlayer.start();
                }
                break;

            case R.id.btn_pause:
                if(mMediaPlayer != null){
                    mMediaPlayer.pause();
                    pauseCurrentPosition = mMediaPlayer.getCurrentPosition();
                }
                break;

            case R.id.btn_stop:
                if(mMediaPlayer != null) {
                    mMediaPlayer.stop();
                    mMediaPlayer = null;
                }
                break;
        }
    }
}
