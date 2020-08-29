package com.oc.rss.nihongo.Activities;

import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.oc.rss.nihongo.Adapter.VocabularyListAdapter;
import com.oc.rss.nihongo.AsynchTask.HNQueryTaskVocabulary;
import com.oc.rss.nihongo.R;
import com.oc.rss.nihongo.entities.PartOfString;
import com.oc.rss.nihongo.entities.Vocabulary;
import com.oc.rss.nihongo.helper.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Antoine on 25/09/2016.
 */
public class VocabularyListActivity extends AppCompatActivity {

    // Database Helper
    DatabaseHandler dbOpenHelper;

    private HNQueryTaskVocabulary taskTemp = null;

    //Composant de la page

    private RecyclerView recyclerView;
    private VocabularyListAdapter _adapter;
    private List<Vocabulary> vocabularyList = new ArrayList<>();
    private List<PartOfString> listOfVoc = null;
    private String stringToTranslate ="";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary_list);
        listOfVoc = getIntent().getParcelableArrayListExtra("listOfVoc");
        stringToTranslate = getIntent().getStringExtra("stringToTranslate");
        if (stringToTranslate ==null){
            stringToTranslate = "";
        }

        if (listOfVoc == null){
            listOfVoc = new ArrayList<>();
        }

        setTitle("List of vocabulary");

        dbOpenHelper = new DatabaseHandler(this);
        try {
            dbOpenHelper.checkAndCopyDatabase();
            dbOpenHelper.openDatabase();
        }catch (SQLiteException e){
            e.printStackTrace();
        }

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewVocabulary);
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

        _adapter.setStringToTranslate(stringToTranslate);

        taskTemp = new HNQueryTaskVocabulary(_adapter);
        taskTemp.execute(dbOpenHelper,"All",null,vocabularyList);
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
