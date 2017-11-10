package com.example.chuak.projectapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

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
        final EditText footnotes = findViewById(R.id.footnotes);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String description = footnotes.getText().toString();
                long success = notes.insertEntry(description, videoNum);
                if (success == -1) {
                    Crouton.makeText(TutorialPlayerActivity.this, "Notes saved successfully!",
                            Style.ALERT).show();
                } else {
                    footnotes.setText("");
                    Crouton.makeText(TutorialPlayerActivity.this, "Notes saved successfully!",
                            Style.CONFIRM).show();
                }
            }
        });

    }

    public void initializeDatabase() {
        notes = new NotesDatabaseHelper(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
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

    @Override
    public void onDestroy() {
        Crouton.cancelAllCroutons();
        super.onDestroy();
    }

}
