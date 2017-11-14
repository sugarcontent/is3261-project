package com.example.chuak.projectapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class Quiz3Activity extends Activity {

    private Quiz3Question mQuestionLibrary = new Quiz3Question();

    private TextView mScoreView;   // view for current total score
    private TextView mQuestionView;  //current question to answer
    private Button mButtonChoice1; // multiple choice 1 for mQuestionView
    private Button mButtonChoice2; // multiple choice 2 for mQuestionView
    private Button mButtonChoice3; // multiple choice 3 for mQuestionView
    private Button mButtonChoice4; // multiple choice 4 for mQuestionView
    private Button quitButton;
    private ProgressBar pbar;

    private String mAnswer;  // correct answer for question in mQuestionView
    private int mScore = 0;  // current total score
    private int mQuestionNumber = 0; // current question number

    private int maxNumOfQuestions = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz3);

        // setup screen for the first question with four alternative to answer
        mScoreView = findViewById(R.id.score3);
        mQuestionView = findViewById(R.id.question3);
        mButtonChoice1 = findViewById(R.id.choice31);
        mButtonChoice2 = findViewById(R.id.choice32);
        mButtonChoice3 = findViewById(R.id.choice33);
        mButtonChoice4 = findViewById(R.id.choice34);

        quitButton = findViewById(R.id.quit_button3);

        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mQuestionLibrary.clearQuestion();
                Intent i = new Intent(view.getContext(), MainMenuActivity.class);
                startActivity(i);
                finish();
            }
        });

        pbar = findViewById(R.id.progressBar3);

        pbar.setMax(maxNumOfQuestions);
        pbar.setProgress(mQuestionNumber + 1);

        mQuestionLibrary.initQuestions(getApplicationContext());
        updateQuestion();
        // show current total score for the user
        updateScore(mScore);
    }

    private void updateQuestion(){
        // check if we are not outside array bounds for questions
        if(mQuestionNumber < mQuestionLibrary.getLength() ){

            pbar = findViewById(R.id.progressBar3);
            pbar.setProgress(mQuestionNumber + 1);
            // set the text for new question,
            // and new 4 alternative to answer on four buttons
            mQuestionView.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
            mButtonChoice1.setText(mQuestionLibrary.getChoice(mQuestionNumber, 1));
            mButtonChoice2.setText(mQuestionLibrary.getChoice(mQuestionNumber, 2));
            mButtonChoice3.setText(mQuestionLibrary.getChoice(mQuestionNumber, 3));
            mButtonChoice4.setText(mQuestionLibrary.getChoice(mQuestionNumber,4));
            mAnswer = mQuestionLibrary.getCorrectAnswer(mQuestionNumber);
            mQuestionNumber++;
        }
        else {
            mButtonChoice1.setEnabled(false);
            mButtonChoice2.setEnabled(false);
            mButtonChoice3.setEnabled(false);
            mButtonChoice4.setEnabled(false);
            // delay the start of a new activity to display the Crouton
            // for the last question
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            mQuestionLibrary.clearQuestion();
                            Intent intent = new Intent(Quiz3Activity.this, HighestScoreActivity.class);
                            intent.putExtra("activity", 3);
                            intent.putExtra("questions", maxNumOfQuestions);
                            intent.putExtra("score", mScore); // pass the current score to the second screen
                            startActivity(intent);
                            finish();
                        }
                    }, 1500);
        }
    }

    // show current total score for the user
    private void updateScore(int point) {
        mScoreView.setText(Integer.toString(mScore) + "/" + mQuestionLibrary.getLength());
    }

    public void onClick(View view) {

        //all logic for all answers buttons in one method
        Button answer = (Button) view;
        // if the answer is correct, increase the score
        if (answer.getText().equals(mAnswer)){
            mScore = mScore + 1;
            Crouton.makeText(Quiz3Activity.this, "Correct!", Style.CONFIRM).show();
        }else {
            Crouton.makeText(Quiz3Activity.this, "Wrong!", Style.ALERT).show();
        }
        // show current total score for the user
        updateScore(mScore);
        // once user answer the question, we move on to the next one, if any
        updateQuestion();
    }

    @Override
    public void onDestroy() {
        Crouton.cancelAllCroutons();
        super.onDestroy();
    }
}