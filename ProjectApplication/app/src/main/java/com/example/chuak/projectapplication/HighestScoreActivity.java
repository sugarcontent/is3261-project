package com.example.chuak.projectapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import de.keyboardsurfer.android.widget.crouton.Crouton;

public class HighestScoreActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highest_score);

        // receive the score from last activity by Intent
        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);
        int numQuestions = intent.getIntExtra("questions", 0);
        final int currentActivity = intent.getIntExtra("activity", 1);
        String activity = "activity" + currentActivity;

        Button returnButton = findViewById(R.id.return_button);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), MainMenuActivity.class);
                startActivity(i);
                finish();
            }
        });

        Button retryButton = findViewById(R.id.retry_button);
        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (currentActivity) {
                    case 1:
                        Intent intent1 = new Intent(view.getContext(), Quiz1Activity.class);
                        startActivity(intent1);
                        finish();
                        break;

                    case 2:
                        Intent intent2 = new Intent(view.getContext(), Quiz2Activity.class);
                        startActivity(intent2);
                        finish();
                        break;

                    case 3:
                        Intent intent3 = new Intent(view.getContext(), Quiz3Activity.class);
                        startActivity(intent3);
                        finish();
                        break;

                    case 4:
                        Intent intent4 = new Intent(view.getContext(), Quiz4Activity.class);
                        startActivity(intent4);
                        finish();
                        break;

                    default:
                        Intent intentDef = new Intent(view.getContext(), Quiz1Activity.class);
                        startActivity(intentDef);
                        finish();
                        break;
                }
            }
        });

        TextView txtScore = findViewById(R.id.textScore);
        TextView txtHighScore = findViewById(R.id.textHighScore);

        // display current score
        txtScore.setText("Your score: " + score);

        // use Shared preferences to save the best score

        SharedPreferences mypref = getPreferences(MODE_PRIVATE);
        int highscore = mypref.getInt(activity,0);
        if(highscore >= score)
            txtHighScore.setText("High score: "+highscore);
        else {
            txtHighScore.setText("New highscore: "+score);
            SharedPreferences.Editor editor = mypref.edit();
            editor.putInt(activity, score);
            editor.commit();
        }
    }

    @Override
    public void onDestroy() {
        Crouton.cancelAllCroutons();
        super.onDestroy();
    }
}
