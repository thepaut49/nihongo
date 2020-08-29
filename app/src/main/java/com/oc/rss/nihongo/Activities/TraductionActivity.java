package com.oc.rss.nihongo.Activities;


import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.oc.rss.nihongo.Adapter.MyCustomAdapter;
import com.oc.rss.nihongo.Adapter.ToolsAdapter;
import com.oc.rss.nihongo.Adapter.TraductionAdapter;
import com.oc.rss.nihongo.Adapter.VocabularyListAdapter;
import com.oc.rss.nihongo.AsynchTask.HNQueryTaskTraduction;
import com.oc.rss.nihongo.AsynchTask.HNQueryTaskVocabulary;
import com.oc.rss.nihongo.AsynchTask.JapaneseKeyboardTask;
import com.oc.rss.nihongo.R;
import com.oc.rss.nihongo.View.ExpandableHeightGridView;
import com.oc.rss.nihongo.entities.PartOfString;
import com.oc.rss.nihongo.entities.Vocabulary;
import com.oc.rss.nihongo.helper.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

public class TraductionActivity extends AppCompatActivity {

    private JapaneseKeyboardTask japaneseKeyboardTask = null;

    private HNQueryTaskTraduction searchVocabularyTask = null;

    private List<PartOfString> vocabularyList = new ArrayList<>();

    RecyclerView listResultatVocabulary = null;

    private MyCustomAdapter _adapter;

    DatabaseHandler dbOpenHelper;

    private TextView stringPronunciation = null;

    private Button boutonAddVocabulary = null;

    private Button boutonAddKanji = null;

    private String stringATranslater = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traduction);
        setTitle("Traduction");

        stringATranslater = getIntent().getStringExtra("stringToTranslate");

        stringPronunciation = (TextView) findViewById(R.id.stringPronunciation);
        stringPronunciation.setText("");

        final EditText stringToTranslate = (EditText) findViewById(R.id.stringToTranslate);
        stringToTranslate.setBackgroundResource(R.drawable.edit_text_background);

        if (stringATranslater != null && stringATranslater != ""){
            stringToTranslate.setText(stringATranslater);
        }

        ExpandableHeightGridView gridview = (ExpandableHeightGridView) findViewById(R.id.gridviewTools);
        gridview.setAdapter(new ToolsAdapter(this));
        gridview.setExpanded(true);

        listResultatVocabulary = (RecyclerView) findViewById(R.id.decoupageString);
        listResultatVocabulary.setLayoutManager(new LinearLayoutManager(this));
        _adapter = new MyCustomAdapter(this, (ArrayList<PartOfString>) vocabularyList);
        listResultatVocabulary.setAdapter(_adapter);

        boutonAddKanji = (Button) findViewById(R.id.buttonAddKanji);
        boutonAddVocabulary = (Button) findViewById(R.id.buttonAddVocabulaire);

        boutonAddKanji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TraductionActivity.this, SecondActivity.class);
                intent.putExtra("categorie","漢字");
                intent.putExtra("stringToTranslate",stringToTranslate.getText().toString());
                startActivity(intent);
            }
        });

        boutonAddVocabulary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TraductionActivity.this, SecondActivity.class);
                intent.putExtra("categorie","語彙");
                intent.putExtra("stringToTranslate",stringToTranslate.getText().toString());
                startActivity(intent);
            }
        });


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                stringToTranslate.setText(stringToTranslate.getText().toString()+((TextView) v).getText());
            }
        });

        ImageButton buttonclear = (ImageButton) findViewById(R.id.boutonClear);
        buttonclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _adapter.resetList();
                listResultatVocabulary.removeAllViews();
                stringPronunciation.setText("");
            }
        });

        ImageButton buttonTrad = (ImageButton) findViewById(R.id.boutonTranslate);
        buttonTrad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = stringToTranslate.getText().toString();
                japaneseKeyboardTask = new JapaneseKeyboardTask(stringToTranslate);
                japaneseKeyboardTask.execute(value);
            }
        });

        ImageButton buttonPartieManquante = (ImageButton) findViewById(R.id.boutonPartieManquante);
        buttonPartieManquante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              _adapter.ajoutPartieManquante(stringToTranslate.getText().toString(),stringPronunciation);
            }
        });

        ImageButton buttonDecoupage = (ImageButton) findViewById(R.id.boutonDecoupage);
        buttonDecoupage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vocabularyList = new ArrayList<>();
                _adapter.resetList();
                searchVocabularyTask = new HNQueryTaskTraduction(_adapter);
                searchVocabularyTask.execute(dbOpenHelper,"DecoupagePhrase",stringToTranslate.getText().toString(),vocabularyList);
            }
        });

        dbOpenHelper = new DatabaseHandler(this);
        try {
            dbOpenHelper.checkAndCopyDatabase();
            dbOpenHelper.openDatabase();
        }catch (SQLiteException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(japaneseKeyboardTask != null){
            japaneseKeyboardTask.cancel(true);
        }
        if (searchVocabularyTask != null){
            searchVocabularyTask.cancel(true);
        }
    }

}
