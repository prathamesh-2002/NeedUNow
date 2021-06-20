package com.example.sos_parp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class T_EarthquakeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_activity_earthquake);
        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view5);
        getLifecycle().addObserver(youTubePlayerView);
    }

    public void callEarthquakeHelpline(View view)
    {
        String number = "011-24363260";
        Uri call = Uri.parse("tel:" + number);
        Intent intent = new Intent(Intent.ACTION_DIAL, call);
        startActivity(intent);
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
