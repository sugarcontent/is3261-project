package com.example.chuak.projectapplication;

/**
 * Created by chuak on 20/10/2017.
 */

public class Question {
    private String qn;
    private String[] choice = new String[4];
    private String answer;

    public Question() {

    }
    public Question(String question, String[] choices, String answer) {
        this.qn = question;
        this.choice[0] = choices[0];
        this.choice[1] = choices[1];
        this.choice[2] = choices[2];
        this.choice[3] = choices[3];
        this.answer = answer;
    }

    public String getQuestion() {
        return qn;
    }

    public String getChoice(int i) {
        return choice[i];
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setChoice(int i, String choice) {
        this.choice[i] = choice;
    }

    public void setQuestion(String question) {
        this.qn = question;
    }

}
