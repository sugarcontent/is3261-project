package com.example.chuak.projectapplication;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chuak on 3/11/2017.
 */

public class Quiz2Question {
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
            myDatabaseHelper.addInitialQuestion(new Question("1. What is the purpose of setOnClickListener?",
                    new String[]{"No inherent purpose", "Makes the button clickable", "Calls a function after clicking button", "Listen for clicks"}, "Calls a function after clicking button"));
            myDatabaseHelper.addInitialQuestion(new Question("2. What is the first parameter in the Intent constructor?",
                    new String[]{"Activity class", "Context", "String", "Integer"}, "Context"));
            myDatabaseHelper.addInitialQuestion(new Question("3. What do you use Intent for?",
                    new String[]{"Transition from one activity to another", "State your intention", "Create a new layout", "Adding a widget"}, "Transition from one activity to another"));
            //get list from database again
            list = myDatabaseHelper.getAllQuestionsList();
        }
    }

    public void clearQuestion() {
        myDatabaseHelper.clearTable();
    }
}
