package com.example.chuak.projectapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class TutorialPlayerActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    public String VIDEO_ID = null;
    NotesDatabaseHelper notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // attaching layout xml
        setContentView(R.layout.activity_tutorial_player);
        initializeDatabase();

        String API_KEY = this.getResources().getString(R.string.api_key);

        //https://www.youtube.com/watch?v=<VIDEO_ID>
        final String video_id = getIntent().getStringExtra("key");
        final int videoNum = getIntent().getIntExtra("videoNum", 1);
        setVideoId(video_id);

        // Initializing YouTube player view
        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        youTubePlayerView.initialize(API_KEY, this);

        Button save = findViewById(R.id.save_notes);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText footnotes = findViewById(R.id.footnotes);
                String description = footnotes.getText().toString();
                notes.insertEntry(description, videoNum);
            }
        });
    }

    public void initializeDatabase() {
        notes = new NotesDatabaseHelper(this);
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
