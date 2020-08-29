package com.oc.rss.nihongo.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.oc.rss.nihongo.R;

/**
 * Created by Antoine on 16/12/2017.
 */

public class ResultQuizzActivity extends AppCompatActivity {

    private TextView score;
    private TextView commentary;
    private Button comeBackToQuizzMenu;
    private Button comeBackToMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_quizz);

        setTitle("日本語 quizz results");

        score = (TextView) findViewById(R.id.scoreResult);
        commentary = (TextView) findViewById(R.id.resultCommentary);

        Integer scoreValue = getIntent().getIntExtra("quizzScore",-1);
        score.setText("You scored " + scoreValue + " / 20");

        if (scoreValue >= 15){
            commentary.setText("That's my boy! continue tu crush this quizz !!!");
        } else if (scoreValue > 10){
            commentary.setText("What are you doing work harder!");
        } else {
            commentary.setText("What are you doing moron! start over from scratch !!!!!");
        }

        comeBackToQuizzMenu = (Button) findViewById(R.id.backToQuizzMenu);
        comeBackToQuizzMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultQuizzActivity.this, QuizzMenuActivity.class);
                startActivity(intent);
            }
        });

        comeBackToMainMenu = (Button) findViewById(R.id.backToMainMenu);
        comeBackToMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultQuizzActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
