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

public class Quiz2Activity extends Activity {

    private Quiz2Question mQuestionLibrary = new Quiz2Question();

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

    private int maxNumOfQuestions = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz2);

        // setup screen for the first question with four alternative to answer
        mScoreView = findViewById(R.id.score2);
        mQuestionView = findViewById(R.id.question2);
        mButtonChoice1 = findViewById(R.id.choice21);
        mButtonChoice2 = findViewById(R.id.choice22);
        mButtonChoice3 = findViewById(R.id.choice23);
        mButtonChoice4 = findViewById(R.id.choice24);

        quitButton = findViewById(R.id.quit_button2);

        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mQuestionLibrary.clearQuestion();
                Intent i = new Intent(view.getContext(), MainMenuActivity.class);
                startActivity(i);
            }
        });

        pbar = findViewById(R.id.progressBar2);

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

            pbar = findViewById(R.id.progressBar2);
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
            // delay the start of a new activity to display the Crouton
            // for the last question
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            mQuestionLibrary.clearQuestion();
                            Intent intent = new Intent(Quiz2Activity.this, HighestScoreActivity.class);
                            intent.putExtra("activity", 2);
                            intent.putExtra("questions", maxNumOfQuestions);
                            intent.putExtra("score", mScore); // pass the current score to the second screen
                            startActivity(intent);
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
            Crouton.makeText(Quiz2Activity.this, "Correct!", Style.CONFIRM).show();
        }else {
            Crouton.makeText(Quiz2Activity.this, "Wrong!", Style.ALERT).show();
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
