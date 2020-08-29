package com.oc.rss.nihongo.Activities;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.icu.text.MessagePattern;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.oc.rss.nihongo.Adapter.KanjiListAdapter;
import com.oc.rss.nihongo.Adapter.VocabularyListAdapter;
import com.oc.rss.nihongo.AsynchTask.HNQueryTask;
import com.oc.rss.nihongo.AsynchTask.HNQueryTaskVocabulary;
import com.oc.rss.nihongo.R;
import com.oc.rss.nihongo.entities.Kanji;
import com.oc.rss.nihongo.entities.PartOfString;
import com.oc.rss.nihongo.entities.Vocabulary;
import com.oc.rss.nihongo.helper.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Antoine on 25/09/2016.
 */
public class FicheVocabularyVisuActivity extends AppCompatActivity {
    // Database Helper
    DatabaseHandler dbOpenHelper;

    private HNQueryTaskVocabulary taskTemp = null;

    private HNQueryTask taskKanji = null;

    //Composant de la page

    private RecyclerView recyclerView;
    private RecyclerView associatedKanji;
    private VocabularyListAdapter _adapter;
    private KanjiListAdapter _adapterKanji;
    private List<Vocabulary> vocabularyList = new ArrayList<>();
    private List<Kanji> kanjiList = new ArrayList<>();

    private Button buttonRechercheAssociated;
    private Button buttonAddVocToString;

    private Integer position;
    private List<PartOfString> listOfVoc = null;
    private String stringToTranslate ="";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche_vocabulaire_visu);

        setTitle("Vocabulary informations");

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        position = bundle.getInt("position");
        listOfVoc = getIntent().getParcelableArrayListExtra("listOfVoc");
        stringToTranslate = getIntent().getStringExtra("stringToTranslate");
        if (stringToTranslate ==null){
            stringToTranslate = "";
        }

        if (listOfVoc == null){
            listOfVoc = new ArrayList<>();
        }

        dbOpenHelper = new DatabaseHandler(this);
        try {
            dbOpenHelper.checkAndCopyDatabase();
            dbOpenHelper.openDatabase();
        }catch (SQLiteException e){
            e.printStackTrace();
        }

        // On récupère toutes les vues dont on a besoin

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewVocabularyVisu);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        _adapter = new VocabularyListAdapter(this,this);
        recyclerView.setAdapter(_adapter);

        final ProgressBar progress = (ProgressBar) findViewById(R.id.progress);
        _adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                progress.setVisibility(View.GONE);
            }
        });

        taskTemp = new HNQueryTaskVocabulary(_adapter);
        taskTemp.execute(dbOpenHelper,"Id",position.toString(),vocabularyList);


        associatedKanji = (RecyclerView) findViewById(R.id.vocabularyAssociatedKanji);
        associatedKanji.setLayoutManager(new LinearLayoutManager(this));
        _adapterKanji = new KanjiListAdapter(this,this);
        associatedKanji.setAdapter(_adapterKanji);


        buttonRechercheAssociated = (Button) findViewById(R.id.buttonRechercheAssociatedKanji);
        buttonRechercheAssociated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kanjiList = new ArrayList<Kanji>();
                _adapterKanji.resetList();
                taskKanji = new HNQueryTask(_adapterKanji);
                taskKanji.execute(dbOpenHelper,"Associated",vocabularyList.get(0).getWord(),kanjiList);
            }
        });

        buttonAddVocToString = (Button) findViewById(R.id.buttonAddVocToString);
        buttonAddVocToString.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PartOfString partOfString = new PartOfString();
                partOfString.setPartieInconnu(false);
                partOfString.setWrittenInKanji(true);
                partOfString.setLongueur(vocabularyList.get(0).getWord().length());
                partOfString.setWordAssociated(vocabularyList.get(0));
                partOfString.setPositionInTheString(stringToTranslate.length());
                stringToTranslate = stringToTranslate + vocabularyList.get(0).getWord();
                listOfVoc.add(partOfString);
                Intent intent = new Intent(FicheVocabularyVisuActivity.this, TraductionActivity.class);
                intent.putExtra("categorie","語彙");
                intent.putExtra("stringToTranslate",stringToTranslate);
                intent.putParcelableArrayListExtra("listOfVoc", (ArrayList<? extends Parcelable>) listOfVoc);
                startActivity(intent);
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        dbOpenHelper.openDatabase();
    }

    @Override
    protected void onPause() {
        super.onPause();
        dbOpenHelper.closeDB();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbOpenHelper.closeDB();
        if(taskTemp != null){
            taskTemp.cancel(true);
        }
        if(taskKanji != null){
            taskKanji.cancel(true);
        }
    }
}
