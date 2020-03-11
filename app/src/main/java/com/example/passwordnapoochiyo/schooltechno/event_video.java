package com.example.passwordnapoochiyo.schooltechno;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;

public class event_video extends AppCompatActivity {
    VideoView video;
    String video_url = "";
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_video);
        video = (VideoView)findViewById(R.id.video);

        pd = new ProgressDialog(event_video.this);
        pd.setMessage("Buffering video please wait...");
        pd.show();

        Uri uri = Uri.parse(video_url);
        video.setVideoURI(uri);
        video.start();

        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                //close the progress dialog when buffering is done
                pd.dismiss();
            }
        });
    }
}
