package com.example.chuak.projectapplication;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class TutorialPlayerActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    public static final String API_KEY = "AIzaSyBx7v0YOb140fDO7EbfMx4l87raxezDWFw";

    public String VIDEO_ID = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // attaching layout xml
        setContentView(R.layout.activity_tutorial_player);

        //https://www.youtube.com/watch?v=<VIDEO_ID>
        String video_id = getIntent().getStringExtra("key");
        setVideoId(video_id);

        // Initializing YouTube player view
        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        youTubePlayerView.initialize(API_KEY, this);

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult result) {
        Toast.makeText(this, "Failed to initialize.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        if (null == player) return;

        // Start buffering
        if (!wasRestored) {
            player.cueVideo(getVideoId());
        }

    }

    public void setVideoId(String id) {
        this.VIDEO_ID = id;
    }

    public String getVideoId() {
        return this.VIDEO_ID;
    }

}
