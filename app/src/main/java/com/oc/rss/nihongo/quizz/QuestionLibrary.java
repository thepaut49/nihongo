package com.oc.rss.nihongo.quizz;

/**
 * Created by Antoine on 15/12/2017.
 */

public class QuestionLibrary {

    private String mQuestions [];

    private String mChoices [][];

    private String mCorrectAnswers[];

    public String getQuestion(int a) {
        String question = mQuestions[a];
        return question;
    }

    public void setQuestion(int a,String question) {
        mQuestions[a] = question;
    }

    public String getChoice1(int a) {
        String choice0 = mChoices[a][0];
        return choice0;
    }

    public void setChoice1(int a,String choice1) {
        mChoices[a][0] = choice1;
    }

    public String getChoice2(int a) {
        String choice1 = mChoices[a][1];
        return choice1;
    }

    public void setChoice2(int a,String choice2) {
        mChoices[a][1] = choice2;
    }

    public String getChoice3(int a) {
        String choice2 = mChoices[a][2];
        return choice2;
    }

    public void setChoice3(int a,String choice3) {
        mChoices[a][2] = choice3;
    }

    public String getCorrectAnswer(int a) {
        String answer = mCorrectAnswers[a];
        return answer;
    }

    public void setCorrectAnswer(int a,String correctAnswer) {
        mCorrectAnswers[a] = correctAnswer;
    }

    public String[] getmQuestions() {
        return mQuestions;
    }

    public void setmQuestions(String[] mQuestions) {
        this.mQuestions = mQuestions;
    }

    public String[][] getmChoices() {
        return mChoices;
    }

    public void setmChoices(String[][] mChoices) {
        this.mChoices = mChoices;
    }

    public void setmChoicesLine(int i, String[] mChoices) {
        this.mChoices[i] = mChoices;
    }

    public String[] getmCorrectAnswers() {
        return mCorrectAnswers;
    }

    public void setmCorrectAnswers(String[] mCorrectAnswers) {
        this.mCorrectAnswers = mCorrectAnswers;
    }

    public QuestionLibrary() {
        this.mQuestions = new String[20];
        this.mChoices = new String[20][3];
        this.mCorrectAnswers = new String[20];
    }
}
