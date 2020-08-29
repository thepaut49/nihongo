package com.oc.rss.nihongo.Activities;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
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
public class FicheKanjiVisuActivity extends AppCompatActivity {

    // Database Helper
    DatabaseHandler dbOpenHelper;

    private List<Kanji> kanjiList = new ArrayList<>();
    private List<Vocabulary> vocabularyList = new ArrayList<>();

    private HNQueryTask taskTemp = null;

    private HNQueryTaskVocabulary taskVocabulary = null;

    private List<PartOfString> listOfVoc = null;
    private String stringToTranslate ="";


    //Composant de la page

    private RecyclerView recyclerView;

    private RecyclerView associatedVocabulary;
    private KanjiListAdapter _adapter;
    private VocabularyListAdapter _adapterVocabulary;

    private HNQueryTask.HNArticleList list = new HNQueryTask.HNArticleList();

    private Button buttonRechercheAssociated;

    private Button buttonAddToString;


    private Integer position;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche_kanji_visu);

        setTitle("Kanji informations");

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

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewKanjiVisu);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        _adapter = new KanjiListAdapter(this,this);
        recyclerView.setAdapter(_adapter);

        final ProgressBar progress = (ProgressBar) findViewById(R.id.progressKanjiVisu);
        _adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                progress.setVisibility(View.GONE);
            }
        });

        taskTemp = new HNQueryTask(_adapter);
        taskTemp.execute(dbOpenHelper,"Id",position.toString(),kanjiList);


        associatedVocabulary = (RecyclerView) findViewById(R.id.associatedVocabularyKanjiVisu);
        associatedVocabulary.setLayoutManager(new LinearLayoutManager(this));
        _adapterVocabulary = new VocabularyListAdapter(this,this);
        associatedVocabulary.setAdapter(_adapterVocabulary);

        buttonRechercheAssociated = (Button) findViewById(R.id.buttonRechercheAssociated);
        buttonRechercheAssociated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kanjiList.get(0).getCaractere() != null && !"".equals(kanjiList.get(0).getCaractere()) ){
                    vocabularyList = new ArrayList<>();
                    _adapterVocabulary.resetList();
                    taskVocabulary = new HNQueryTaskVocabulary(_adapterVocabulary);
                    taskVocabulary.execute(dbOpenHelper,"Associated",kanjiList.get(0).getCaractere(),vocabularyList);
                }
            }
        });

        buttonAddToString = (Button) findViewById(R.id.buttonAddToString);
        buttonAddToString.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringToTranslate = stringToTranslate + kanjiList.get(0).getCaractere();
                Intent intent = new Intent(FicheKanjiVisuActivity.this, TraductionActivity.class);
                intent.putExtra("categorie","漢字");
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
        if(taskVocabulary != null){
            taskVocabulary.cancel(true);
        }
    }



}
