package com.example.chuak.projectapplication;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chuak on 20/10/2017.
 */

public class Quiz1Question {

    // declare list of Question objects
    List<Question> list = new ArrayList<>();
    MyDatabaseHelper myDatabaseHelper;

    // method returns number of questions in list
    public int getLength(){
        return list.size();
    }

    // method returns question from list based on list index
    public String getQuestion(int a) {
        return list.get(a).getQuestion();
    }

    // method return a single multiple choice item for question based on list index,
    // based on number of multiple choice item in the list - 1, 2, 3 or 4
    // as an argument
    public String getChoice(int index, int num) {
        return list.get(index).getChoice(num-1);
    }

    //  method returns correct answer for the question based on list index
    public String getCorrectAnswer(int a) {
        return list.get(a).getAnswer();
    }



    public void initQuestions(Context context) {
        myDatabaseHelper = new MyDatabaseHelper(context);
        //get questions/choices/answers from database
        list = myDatabaseHelper.getAllQuestionsList();

        // add questions here
        if (list.isEmpty()) {
            myDatabaseHelper.addInitialQuestion(new Question("1. Where are the images for app icons located?",
                    new String[]{"Layout folder", "Mipmap folder", "Drawable folder", "Values folder"}, "Mipmap folder"));
            myDatabaseHelper.addInitialQuestion(new Question("2. What is the name of build toolkit for Android Studio?",
                    new String[]{"JVM", "Gradle", "Dalvik", "HAXM"}, "Gradle"));
            myDatabaseHelper.addInitialQuestion(new Question("3. How do you align text in TextView to be in the center?",
                    new String[]{"gravity:center", "position:center", "text:center", "moveText:center"}, "gravity:center"));
            myDatabaseHelper.addInitialQuestion(new Question("4. What is a widget in Android app?",
                    new String[]{"reusable GUI element", "Layout for Activity", "device placed in cans of beer", "build toolkit"}, "reusable GUI element"));
            myDatabaseHelper.addInitialQuestion(new Question("5. What format are layout files in?",
                    new String[]{"Java", "HTML", "CSS", "XML"}, "XML"));

            //get list from database again
            list = myDatabaseHelper.getAllQuestionsList();

        }
    }

    public void clearQuestion() {
        myDatabaseHelper.clearTable();
    }
}
