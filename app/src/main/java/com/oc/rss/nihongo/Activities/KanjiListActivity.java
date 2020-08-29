package com.oc.rss.nihongo.Activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.oc.rss.nihongo.Adapter.KanjiListAdapter;
import com.oc.rss.nihongo.AsynchTask.HNQueryTask;
import com.oc.rss.nihongo.R;
import com.oc.rss.nihongo.entities.Kanji;
import com.oc.rss.nihongo.entities.PartOfString;
import com.oc.rss.nihongo.helper.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Antoine on 25/09/2016.
 */
public class KanjiListActivity extends AppCompatActivity {

    // Database Helper
    DatabaseHandler dbOpenHelper;

    private HNQueryTask taskTemp = null;

    private List<Kanji> kanjiList = new ArrayList<>();

    //Composant de la page

    private RecyclerView recyclerView;
    private TextView nbrAllKanji = null;
    private KanjiListAdapter _adapter;
    private List<PartOfString> listOfVoc = null;
    private String stringToTranslate ="";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kanji_list);
        listOfVoc = getIntent().getParcelableArrayListExtra("listOfVoc");
        stringToTranslate = getIntent().getStringExtra("stringToTranslate");
        if (stringToTranslate ==null){
            stringToTranslate = "";
        }

        if (listOfVoc == null){
            listOfVoc = new ArrayList<>();
        }

        setTitle("Kanji List");

        dbOpenHelper = new DatabaseHandler(this);
        try {
            dbOpenHelper.checkAndCopyDatabase();
            dbOpenHelper.openDatabase();
        }catch (SQLiteException e){
            e.printStackTrace();
        }

        // On récupère toutes les vues dont on a besoin


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        _adapter = new KanjiListAdapter(this,this);
        recyclerView.setAdapter(_adapter);


        final ProgressBar progress = (ProgressBar) findViewById(R.id.progress);
        _adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                progress.setVisibility(View.GONE);
            }
        });

        _adapter.setStringToTranslate(stringToTranslate);

        taskTemp = new HNQueryTask(_adapter);
        taskTemp.execute(dbOpenHelper,"All",null,kanjiList);

    }


    public void loadNext() {
        if (taskTemp != null && taskTemp.getStatus() != AsyncTask.Status.FINISHED)
            return ;

        taskTemp = new HNQueryTask(_adapter);
        taskTemp.execute(dbOpenHelper,"All",null,kanjiList);
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
    }



}
