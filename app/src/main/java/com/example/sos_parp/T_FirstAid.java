package com.example.sos_parp;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class T_FirstAid extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_activity_firstaid);
        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view7);
        getLifecycle().addObserver(youTubePlayerView);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
    public void closeActivity(View view){
        onBackPressed();
    }
}
