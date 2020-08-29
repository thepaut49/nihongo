package com.oc.rss.nihongo.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.oc.rss.nihongo.R;
import com.oc.rss.nihongo.quizz.QuestionLibrary;

/**
 * Created by Antoine on 15/12/2017.
 */

public class QuizzActivity extends AppCompatActivity {

    private QuestionLibrary mQuestionLibrary = new QuestionLibrary();

    private TextView questionNumber;
    private TextView mScoreView;
    private TextView mQuestionView;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;
    //private Button mButtonChoice4;

    private String mAnswer;
    private int mScore = 0;
    private int mQuestionNumber = 0;
    private QuestionLibrary questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);

        questions = new QuestionLibrary();

        questions.setmQuestions(getIntent().getStringArrayExtra("questions"));
        questions.setmCorrectAnswers(getIntent().getStringArrayExtra("answers"));
        questions.setmChoicesLine(0,getIntent().getStringArrayExtra("choicesQuestion0"));
        questions.setmChoicesLine(1,getIntent().getStringArrayExtra("choicesQuestion1"));
        questions.setmChoicesLine(2,getIntent().getStringArrayExtra("choicesQuestion2"));
        questions.setmChoicesLine(3,getIntent().getStringArrayExtra("choicesQuestion3"));
        questions.setmChoicesLine(4,getIntent().getStringArrayExtra("choicesQuestion4"));
        questions.setmChoicesLine(5,getIntent().getStringArrayExtra("choicesQuestion5"));
        questions.setmChoicesLine(6,getIntent().getStringArrayExtra("choicesQuestion6"));
        questions.setmChoicesLine(7,getIntent().getStringArrayExtra("choicesQuestion7"));
        questions.setmChoicesLine(8,getIntent().getStringArrayExtra("choicesQuestion8"));
        questions.setmChoicesLine(9,getIntent().getStringArrayExtra("choicesQuestion9"));
        questions.setmChoicesLine(10,getIntent().getStringArrayExtra("choicesQuestion10"));
        questions.setmChoicesLine(11,getIntent().getStringArrayExtra("choicesQuestion11"));
        questions.setmChoicesLine(12,getIntent().getStringArrayExtra("choicesQuestion12"));
        questions.setmChoicesLine(13,getIntent().getStringArrayExtra("choicesQuestion13"));
        questions.setmChoicesLine(14,getIntent().getStringArrayExtra("choicesQuestion14"));
        questions.setmChoicesLine(15,getIntent().getStringArrayExtra("choicesQuestion15"));
        questions.setmChoicesLine(16,getIntent().getStringArrayExtra("choicesQuestion16"));
        questions.setmChoicesLine(17,getIntent().getStringArrayExtra("choicesQuestion17"));
        questions.setmChoicesLine(18,getIntent().getStringArrayExtra("choicesQuestion18"));
        questions.setmChoicesLine(19,getIntent().getStringArrayExtra("choicesQuestion19"));


        questionNumber = (TextView)findViewById(R.id.questionNumber);
        mScoreView = (TextView)findViewById(R.id.score);
        mQuestionView = (TextView)findViewById(R.id.question);
        mButtonChoice1 = (Button)findViewById(R.id.choice1);
        mButtonChoice2 = (Button)findViewById(R.id.choice2);
        mButtonChoice3 = (Button)findViewById(R.id.choice3);
        //mButtonChoice4 = (Button)findViewById(R.id.choice4);

        updateQuestion();

        //Start of Button Listener for Button1
        mButtonChoice1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //My logic for Button goes in here

                if (mButtonChoice1.getText().equals(mAnswer)){
                    mScore = mScore + 1;
                    updateScore(mScore);
                    updateQuestion();
                    //This line of code is optiona
                    Toast.makeText(QuizzActivity.this, "Correct", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(QuizzActivity.this, "Wrong the correct answer was : " + mAnswer, Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });

        //End of Button Listener for Button1

        //Start of Button Listener for Button2
        mButtonChoice2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //My logic for Button goes in here

                if (mButtonChoice2.getText().equals(mAnswer)){
                    mScore = mScore + 1;
                    updateScore(mScore);
                    updateQuestion();
                    //This line of code is optiona
                    Toast.makeText(QuizzActivity.this, "Correct", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(QuizzActivity.this, "Wrong the correct answer was : "+ mAnswer, Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });

        //End of Button Listener for Button2


        //Start of Button Listener for Button3
        mButtonChoice3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //My logic for Button goes in here

                if (mButtonChoice3.getText().equals(mAnswer)){
                    mScore = mScore + 1;
                    updateScore(mScore);
                    updateQuestion();
                    //This line of code is optiona
                    Toast.makeText(QuizzActivity.this, "Correct", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(QuizzActivity.this, "Wrong the correct answer was : "+ mAnswer, Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });

        //End of Button Listener for Button3

        //Start of Button Listener for Button4
        //mButtonChoice4.setOnClickListener(new View.OnClickListener(){
         //   @Override
          //  public void onClick(View view){
        //        //My logic for Button goes in here

         //       if (mButtonChoice4.getText() == mAnswer){
        //            mScore = mScore + 1;
        //            updateScore(mScore);
        //            updateQuestion();
                    //This line of code is optiona
         //           Toast.makeText(QuizzActivity.this, "correct", Toast.LENGTH_SHORT).show();
//
        //        }else {
        //            Toast.makeText(QuizzActivity.this, "wrong", Toast.LENGTH_SHORT).show();
         //           updateQuestion();
         //       }
       //     }
       // });

        //End of Button Listener for Button4





    }

    private void updateQuestion(){
        if (mQuestionNumber == 20){
            Intent intent = new Intent(QuizzActivity.this, ResultQuizzActivity.class);
            intent.putExtra("quizzScore",mScore);
            startActivity(intent);
        } else {
            int nbQuestion = mQuestionNumber + 1;
            questionNumber.setText( nbQuestion + ". ");
            mQuestionView.setText(questions.getQuestion(mQuestionNumber));
            mButtonChoice1.setText(questions.getChoice1(mQuestionNumber));
            mButtonChoice2.setText(questions.getChoice2(mQuestionNumber));
            mButtonChoice3.setText(questions.getChoice3(mQuestionNumber));

            mAnswer = questions.getCorrectAnswer(mQuestionNumber);

            mQuestionNumber++;
        }
    }


    private void updateScore(int point) {
        mScoreView.setText("" + mScore);
    }


}
