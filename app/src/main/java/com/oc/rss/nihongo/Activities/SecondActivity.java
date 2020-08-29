package com.oc.rss.nihongo.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.oc.rss.nihongo.R;
import com.oc.rss.nihongo.entities.PartOfString;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Antoine on 28/08/2016.
 */
public class SecondActivity extends AppCompatActivity {

    private String categorie;
    private List<PartOfString> listOfVoc = null;
    private String stringToTranslate ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seconde);


        categorie = getIntent().getStringExtra("categorie");
        setTitle(categorie);
        stringToTranslate = getIntent().getStringExtra("stringToTranslate");
        if (stringToTranslate ==null){
            stringToTranslate = "";
        }

        if (listOfVoc == null){
            listOfVoc = new ArrayList<>();
        }

        Button buttonListe= (Button) findViewById(R.id.buttonListe);
        buttonListe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (categorie.equals("ひらがな")) {
                    Intent intent = new Intent(SecondActivity.this, ListeHiraganaActivity.class);
                    startActivity(intent);
                } else if (categorie.equals("カタカナ")) {
                    Intent intent = new Intent(SecondActivity.this, ListeKatakanaActivity.class);
                    startActivity(intent);
                } else if (categorie.equals("漢字")) {
                    Intent intent = new Intent(SecondActivity.this, KanjiListActivity.class);
                    intent.putExtra("categorie",categorie);
                    intent.putExtra("stringToTranslate",stringToTranslate);
                    intent.putParcelableArrayListExtra("listOfVoc", (ArrayList<? extends Parcelable>) listOfVoc);
                    startActivity(intent);
                } else if (categorie.equals("語彙")){
                    Intent intent = new Intent(SecondActivity.this, VocabularyListActivity.class);
                    intent.putExtra("categorie",categorie);
                    intent.putExtra("stringToTranslate",stringToTranslate);
                    intent.putParcelableArrayListExtra("listOfVoc", (ArrayList<? extends Parcelable>) listOfVoc);
                    startActivity(intent);
                }
            }
        });

        Button buttonRecherche= (Button) findViewById(R.id.buttonRecherche);
        if (categorie.equals("カタカナ") || categorie.equals("ひらがな")) {
            buttonRecherche.setVisibility(View.GONE);
        } else {
            buttonRecherche.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (categorie.equals("漢字")) {
                        Intent intent = new Intent(SecondActivity.this, RechercheActivity.class);
                        intent.putExtra("categorie",categorie);
                        intent.putExtra("stringToTranslate",stringToTranslate);
                        intent.putParcelableArrayListExtra("listOfVoc", (ArrayList<? extends Parcelable>) listOfVoc);
                        startActivity(intent);
                    } else if (categorie.equals("語彙")){
                        Intent intent = new Intent(SecondActivity.this, RechercheVocabulaireActivity.class);
                        intent.putExtra("categorie",categorie);
                        intent.putExtra("stringToTranslate",stringToTranslate);
                        intent.putParcelableArrayListExtra("listOfVoc", (ArrayList<? extends Parcelable>) listOfVoc);
                        startActivity(intent);
                    }
                }
            });
        }
    }
}
