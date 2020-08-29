package com.oc.rss.nihongo.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.oc.rss.nihongo.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button buttonHiragana = (Button) findViewById(R.id.buttonHiragana);
        buttonHiragana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("categorie","ひらがな");
                startActivity(intent);
            }
        });

        Button buttonKatakana = (Button) findViewById(R.id.buttonKatakana);
        buttonKatakana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("categorie","カタカナ");
                startActivity(intent);
            }
        });

        Button buttonKanji = (Button) findViewById(R.id.buttonKanji);
        buttonKanji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("categorie","漢字");
                startActivity(intent);
            }
        });

        Button buttonVocabulaire = (Button) findViewById(R.id.buttonVocabulaire);
        buttonVocabulaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("categorie","語彙");
                startActivity(intent);
            }
        });

        Button buttonTrad = (Button) findViewById(R.id.buttonTrad);
        buttonTrad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TraductionActivity.class);
               // intent.putExtra("stringToTranslate","2000年に日本の医療制度が世界一だが、2003年に研修医制度が始まって目に見えて悪くなった。");
                startActivity(intent);
            }
        });

        Button buttonQuizz = (Button) findViewById(R.id.buttonQuizz);
        buttonQuizz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, QuizzMenuActivity.class);
                startActivity(intent);
            }
        });

        Button buttonTraining = (Button) findViewById(R.id.buttonTraining);
        buttonQuizz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TrainingMenuActivity.class);
                startActivity(intent);
            }
        });

        setTitle("日本語");


    }

}
