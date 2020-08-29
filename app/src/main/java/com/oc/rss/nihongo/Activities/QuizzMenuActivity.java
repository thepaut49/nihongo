package com.oc.rss.nihongo.Activities;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.oc.rss.nihongo.R;
import com.oc.rss.nihongo.helper.DatabaseHandler;
import com.oc.rss.nihongo.quizz.KanjiQuizzBuilder;
import com.oc.rss.nihongo.quizz.QuestionLibrary;

import java.util.concurrent.ExecutionException;

/**
 * Created by Antoine on 15/12/2017.
 */

public class QuizzMenuActivity extends AppCompatActivity {

    private Button quizzKanjiEngToJap;

    private Button quizzKanjiJapToEng;

    private Button quizzKanjiPronunciation;

    private Button quizzVocEngToJap;

    private Button quizzVocJapToEng;

    private Button quizzVocPronunciation;

    private KanjiQuizzBuilder quizzBuilder = null;

    private QuestionLibrary questions;

    // Database Helper
    DatabaseHandler dbOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz_menu);

        dbOpenHelper = new DatabaseHandler(this);
        try {
            dbOpenHelper.checkAndCopyDatabase();
            dbOpenHelper.openDatabase();
        }catch (SQLiteException e){
            e.printStackTrace();
        }

        quizzKanjiEngToJap = (Button) findViewById(R.id.quizzKanjiEngToJap);
        quizzKanjiEngToJap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                questions = new QuestionLibrary();
                quizzBuilder = new KanjiQuizzBuilder();
                try {
                    questions = quizzBuilder.execute(dbOpenHelper,"quizzKanjiEngToJap").get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(QuizzMenuActivity.this, QuizzActivity.class);
                intent.putExtra("questions",questions.getmQuestions());
                intent.putExtra("answers",questions.getmCorrectAnswers());
                intent.putExtra("choicesQuestion0",questions.getmChoices()[0]);
                intent.putExtra("choicesQuestion1",questions.getmChoices()[1]);
                intent.putExtra("choicesQuestion2",questions.getmChoices()[2]);
                intent.putExtra("choicesQuestion3",questions.getmChoices()[3]);
                intent.putExtra("choicesQuestion4",questions.getmChoices()[4]);
                intent.putExtra("choicesQuestion5",questions.getmChoices()[5]);
                intent.putExtra("choicesQuestion6",questions.getmChoices()[6]);
                intent.putExtra("choicesQuestion7",questions.getmChoices()[7]);
                intent.putExtra("choicesQuestion8",questions.getmChoices()[8]);
                intent.putExtra("choicesQuestion9",questions.getmChoices()[9]);
                intent.putExtra("choicesQuestion10",questions.getmChoices()[10]);
                intent.putExtra("choicesQuestion11",questions.getmChoices()[11]);
                intent.putExtra("choicesQuestion12",questions.getmChoices()[12]);
                intent.putExtra("choicesQuestion13",questions.getmChoices()[13]);
                intent.putExtra("choicesQuestion14",questions.getmChoices()[14]);
                intent.putExtra("choicesQuestion15",questions.getmChoices()[15]);
                intent.putExtra("choicesQuestion16",questions.getmChoices()[16]);
                intent.putExtra("choicesQuestion17",questions.getmChoices()[17]);
                intent.putExtra("choicesQuestion18",questions.getmChoices()[18]);
                intent.putExtra("choicesQuestion19",questions.getmChoices()[19]);
                startActivity(intent);
            }
        });

        quizzKanjiJapToEng = (Button) findViewById(R.id.quizzKanjiJapToEng);
        quizzKanjiJapToEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quizzBuilder = new KanjiQuizzBuilder();
                try {
                    questions = quizzBuilder.execute(dbOpenHelper,"quizzKanjiJapToEng").get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(QuizzMenuActivity.this, QuizzActivity.class);
                intent.putExtra("questions",questions.getmQuestions());
                intent.putExtra("answers",questions.getmCorrectAnswers());
                intent.putExtra("choicesQuestion0",questions.getmChoices()[0]);
                intent.putExtra("choicesQuestion1",questions.getmChoices()[1]);
                intent.putExtra("choicesQuestion2",questions.getmChoices()[2]);
                intent.putExtra("choicesQuestion3",questions.getmChoices()[3]);
                intent.putExtra("choicesQuestion4",questions.getmChoices()[4]);
                intent.putExtra("choicesQuestion5",questions.getmChoices()[5]);
                intent.putExtra("choicesQuestion6",questions.getmChoices()[6]);
                intent.putExtra("choicesQuestion7",questions.getmChoices()[7]);
                intent.putExtra("choicesQuestion8",questions.getmChoices()[8]);
                intent.putExtra("choicesQuestion9",questions.getmChoices()[9]);
                intent.putExtra("choicesQuestion10",questions.getmChoices()[10]);
                intent.putExtra("choicesQuestion11",questions.getmChoices()[11]);
                intent.putExtra("choicesQuestion12",questions.getmChoices()[12]);
                intent.putExtra("choicesQuestion13",questions.getmChoices()[13]);
                intent.putExtra("choicesQuestion14",questions.getmChoices()[14]);
                intent.putExtra("choicesQuestion15",questions.getmChoices()[15]);
                intent.putExtra("choicesQuestion16",questions.getmChoices()[16]);
                intent.putExtra("choicesQuestion17",questions.getmChoices()[17]);
                intent.putExtra("choicesQuestion18",questions.getmChoices()[18]);
                intent.putExtra("choicesQuestion19",questions.getmChoices()[19]);
                startActivity(intent);
            }
        });

        quizzKanjiPronunciation = (Button) findViewById(R.id.quizzKanjiPronunciation);
        quizzKanjiPronunciation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quizzBuilder = new KanjiQuizzBuilder();
                try {
                    questions = quizzBuilder.execute(dbOpenHelper,"quizzKanjiPronunciation").get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(QuizzMenuActivity.this, QuizzActivity.class);
                intent.putExtra("questions",questions.getmQuestions());
                intent.putExtra("answers",questions.getmCorrectAnswers());
                intent.putExtra("choicesQuestion0",questions.getmChoices()[0]);
                intent.putExtra("choicesQuestion1",questions.getmChoices()[1]);
                intent.putExtra("choicesQuestion2",questions.getmChoices()[2]);
                intent.putExtra("choicesQuestion3",questions.getmChoices()[3]);
                intent.putExtra("choicesQuestion4",questions.getmChoices()[4]);
                intent.putExtra("choicesQuestion5",questions.getmChoices()[5]);
                intent.putExtra("choicesQuestion6",questions.getmChoices()[6]);
                intent.putExtra("choicesQuestion7",questions.getmChoices()[7]);
                intent.putExtra("choicesQuestion8",questions.getmChoices()[8]);
                intent.putExtra("choicesQuestion9",questions.getmChoices()[9]);
                intent.putExtra("choicesQuestion10",questions.getmChoices()[10]);
                intent.putExtra("choicesQuestion11",questions.getmChoices()[11]);
                intent.putExtra("choicesQuestion12",questions.getmChoices()[12]);
                intent.putExtra("choicesQuestion13",questions.getmChoices()[13]);
                intent.putExtra("choicesQuestion14",questions.getmChoices()[14]);
                intent.putExtra("choicesQuestion15",questions.getmChoices()[15]);
                intent.putExtra("choicesQuestion16",questions.getmChoices()[16]);
                intent.putExtra("choicesQuestion17",questions.getmChoices()[17]);
                intent.putExtra("choicesQuestion18",questions.getmChoices()[18]);
                intent.putExtra("choicesQuestion19",questions.getmChoices()[19]);
                startActivity(intent);
            }
        });

        quizzVocEngToJap = (Button) findViewById(R.id.quizzVocEngToJap);
        quizzVocEngToJap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quizzBuilder = new KanjiQuizzBuilder();
                try {
                    questions = quizzBuilder.execute(dbOpenHelper,"quizzVocEngToJap").get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(QuizzMenuActivity.this, QuizzActivity.class);
                intent.putExtra("questions",questions.getmQuestions());
                intent.putExtra("answers",questions.getmCorrectAnswers());
                intent.putExtra("choicesQuestion0",questions.getmChoices()[0]);
                intent.putExtra("choicesQuestion1",questions.getmChoices()[1]);
                intent.putExtra("choicesQuestion2",questions.getmChoices()[2]);
                intent.putExtra("choicesQuestion3",questions.getmChoices()[3]);
                intent.putExtra("choicesQuestion4",questions.getmChoices()[4]);
                intent.putExtra("choicesQuestion5",questions.getmChoices()[5]);
                intent.putExtra("choicesQuestion6",questions.getmChoices()[6]);
                intent.putExtra("choicesQuestion7",questions.getmChoices()[7]);
                intent.putExtra("choicesQuestion8",questions.getmChoices()[8]);
                intent.putExtra("choicesQuestion9",questions.getmChoices()[9]);
                intent.putExtra("choicesQuestion10",questions.getmChoices()[10]);
                intent.putExtra("choicesQuestion11",questions.getmChoices()[11]);
                intent.putExtra("choicesQuestion12",questions.getmChoices()[12]);
                intent.putExtra("choicesQuestion13",questions.getmChoices()[13]);
                intent.putExtra("choicesQuestion14",questions.getmChoices()[14]);
                intent.putExtra("choicesQuestion15",questions.getmChoices()[15]);
                intent.putExtra("choicesQuestion16",questions.getmChoices()[16]);
                intent.putExtra("choicesQuestion17",questions.getmChoices()[17]);
                intent.putExtra("choicesQuestion18",questions.getmChoices()[18]);
                intent.putExtra("choicesQuestion19",questions.getmChoices()[19]);
                startActivity(intent);
            }
        });

        quizzVocJapToEng = (Button) findViewById(R.id.quizzVocJapToEng);
        quizzVocJapToEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quizzBuilder = new KanjiQuizzBuilder();
                try {
                    questions = quizzBuilder.execute(dbOpenHelper,"quizzVocJapToEng").get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(QuizzMenuActivity.this, QuizzActivity.class);
                intent.putExtra("questions",questions.getmQuestions());
                intent.putExtra("answers",questions.getmCorrectAnswers());
                intent.putExtra("choicesQuestion0",questions.getmChoices()[0]);
                intent.putExtra("choicesQuestion1",questions.getmChoices()[1]);
                intent.putExtra("choicesQuestion2",questions.getmChoices()[2]);
                intent.putExtra("choicesQuestion3",questions.getmChoices()[3]);
                intent.putExtra("choicesQuestion4",questions.getmChoices()[4]);
                intent.putExtra("choicesQuestion5",questions.getmChoices()[5]);
                intent.putExtra("choicesQuestion6",questions.getmChoices()[6]);
                intent.putExtra("choicesQuestion7",questions.getmChoices()[7]);
                intent.putExtra("choicesQuestion8",questions.getmChoices()[8]);
                intent.putExtra("choicesQuestion9",questions.getmChoices()[9]);
                intent.putExtra("choicesQuestion10",questions.getmChoices()[10]);
                intent.putExtra("choicesQuestion11",questions.getmChoices()[11]);
                intent.putExtra("choicesQuestion12",questions.getmChoices()[12]);
                intent.putExtra("choicesQuestion13",questions.getmChoices()[13]);
                intent.putExtra("choicesQuestion14",questions.getmChoices()[14]);
                intent.putExtra("choicesQuestion15",questions.getmChoices()[15]);
                intent.putExtra("choicesQuestion16",questions.getmChoices()[16]);
                intent.putExtra("choicesQuestion17",questions.getmChoices()[17]);
                intent.putExtra("choicesQuestion18",questions.getmChoices()[18]);
                intent.putExtra("choicesQuestion19",questions.getmChoices()[19]);
                startActivity(intent);
            }
        });

        quizzVocPronunciation = (Button) findViewById(R.id.quizzVocPronunciation);
        quizzVocPronunciation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quizzBuilder = new KanjiQuizzBuilder();
                try {
                    questions = quizzBuilder.execute(dbOpenHelper,"quizzVocPronunciation").get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(QuizzMenuActivity.this, QuizzActivity.class);
                intent.putExtra("questions",questions.getmQuestions());
                intent.putExtra("answers",questions.getmCorrectAnswers());
                intent.putExtra("choicesQuestion0",questions.getmChoices()[0]);
                intent.putExtra("choicesQuestion1",questions.getmChoices()[1]);
                intent.putExtra("choicesQuestion2",questions.getmChoices()[2]);
                intent.putExtra("choicesQuestion3",questions.getmChoices()[3]);
                intent.putExtra("choicesQuestion4",questions.getmChoices()[4]);
                intent.putExtra("choicesQuestion5",questions.getmChoices()[5]);
                intent.putExtra("choicesQuestion6",questions.getmChoices()[6]);
                intent.putExtra("choicesQuestion7",questions.getmChoices()[7]);
                intent.putExtra("choicesQuestion8",questions.getmChoices()[8]);
                intent.putExtra("choicesQuestion9",questions.getmChoices()[9]);
                intent.putExtra("choicesQuestion10",questions.getmChoices()[10]);
                intent.putExtra("choicesQuestion11",questions.getmChoices()[11]);
                intent.putExtra("choicesQuestion12",questions.getmChoices()[12]);
                intent.putExtra("choicesQuestion13",questions.getmChoices()[13]);
                intent.putExtra("choicesQuestion14",questions.getmChoices()[14]);
                intent.putExtra("choicesQuestion15",questions.getmChoices()[15]);
                intent.putExtra("choicesQuestion16",questions.getmChoices()[16]);
                intent.putExtra("choicesQuestion17",questions.getmChoices()[17]);
                intent.putExtra("choicesQuestion18",questions.getmChoices()[18]);
                intent.putExtra("choicesQuestion19",questions.getmChoices()[19]);
                startActivity(intent);
            }
        });

        setTitle("Quizz Menu");


    }
}
