package com.example.chuak.projectapplication;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chuak on 3/11/2017.
 */

public class Quiz4Question {
    // declare list of Question objects
    List<Question> list = new ArrayList<>();
    MyDatabaseHelper myDatabaseHelper;

    // method returns number of questions in list
    public int getLength() {
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
        return list.get(index).getChoice(num - 1);
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
            myDatabaseHelper.addInitialQuestion(new Question("1. What do you need to extend in the DatabaseHelper to use SQLite?",
                    new String[]{"SQLite", "DatabaseHelper", "SQLiteExtend", "SQLiteOpenHelper"}, "SQLiteOpenHelper"));
            myDatabaseHelper.addInitialQuestion(new Question("2. What do you need to define for the database?",
                    new String[]{"Database Name", "Table Name", "Column Names", "All of the above"}, "All of the above"));
            //get list from database again
            list = myDatabaseHelper.getAllQuestionsList();
        }
    }

    public void clearQuestion() {
        myDatabaseHelper.clearTable();
    }
}