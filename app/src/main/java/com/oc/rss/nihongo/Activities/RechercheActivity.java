package com.oc.rss.nihongo.Activities;

import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.oc.rss.nihongo.Adapter.KanjiListAdapter;
import com.oc.rss.nihongo.AsynchTask.HNQueryTask;
import com.oc.rss.nihongo.AsynchTask.JapaneseKeyboardTask;
import com.oc.rss.nihongo.View.ExpandableHeightGridView;
import com.oc.rss.nihongo.R;
import com.oc.rss.nihongo.Adapter.RadicalAdapter;
import com.oc.rss.nihongo.entities.Kanji;
import com.oc.rss.nihongo.entities.PartOfString;
import com.oc.rss.nihongo.entities.Radical;
import com.oc.rss.nihongo.helper.DatabaseHandler;
import com.oc.rss.nihongo.interfaces.KanjiResultListner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Antoine on 25/09/2016.
 */
public class RechercheActivity extends AppCompatActivity implements KanjiResultListner {

    private String categorie;

    // La chaîne de caractères par défaut
    private final String defaut = "You have to click on « Search » to begin the search.";

    private HNQueryTask searchKanjiTask = null;
    private JapaneseKeyboardTask task = null;

    // Database Helper
    DatabaseHandler dbOpenHelper;
    private KanjiListAdapter adapter;
    private RecyclerView.LayoutManager _layoutManager;
    RecyclerView listResultatKanji = null;

    private List<Kanji> kanjiList = new ArrayList<>();
    private String modeRecherche ="kanji";

    ImageButton rechercher = null;
    Button raz = null;
    ImageButton rechercherRadicalCorrection = null;
    ImageButton translate = null;
    Button nHiragana = null;
    Button nKatakana = null;

    TextView rechercheRadical = null;
    EditText recherche = null;

    RadioGroup group = null;

    TextView result = null;

    ExpandableHeightGridView listRadical = null;

    String stringToTranslate ="";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche);

        categorie = getIntent().getStringExtra("categorie");
        stringToTranslate = getIntent().getStringExtra("stringToTranslate");
        if (stringToTranslate ==null){
            stringToTranslate = "";
        }

        setTitle("Search Kanji");

        // On récupère toutes les vues dont on a besoin
        rechercheRadical = (TextView) findViewById(R.id.rechercheRadical);

        rechercherRadicalCorrection = (ImageButton)findViewById(R.id.rechercherRadicalCorrection);

        translate = (ImageButton) findViewById(R.id.traductionRechercheKanji);

        rechercher = (ImageButton)findViewById(R.id.rechercher);

        nHiragana = (Button)findViewById(R.id.nHiraganaKanji);
        nKatakana = (Button)findViewById(R.id.nKatakanaKanji);



        raz = (Button)findViewById(R.id.raz);

        recherche = (EditText)findViewById(R.id.recherche);
        recherche.setVisibility(View.GONE);
        translate.setVisibility(View.GONE);

        group = (RadioGroup)findViewById(R.id.group);

        result = (TextView)findViewById(R.id.result);

        // On attribue un listener adapté aux vues qui en ont besoin
        rechercherRadicalCorrection.setOnClickListener(rechercherRadicalCorrectionListener);
        rechercher.setOnClickListener(rechercherListener);
        translate.setOnClickListener(translateListener);
        raz.setOnClickListener(razListener);
        recherche.addTextChangedListener(textWatcher);
        group.setOnCheckedChangeListener(radioListener);

        final List<String> list = Radical.all;
        listRadical = (ExpandableHeightGridView) findViewById(R.id.listRadical);
        listRadical.setAdapter( new RadicalAdapter(this));
        listRadical.setExpanded(true);

        listResultatKanji = (RecyclerView) findViewById(R.id.listResultatKanji);
        _layoutManager = new LinearLayoutManager(this);
        listResultatKanji.setLayoutManager(_layoutManager);

        adapter = new KanjiListAdapter(this,this,stringToTranslate);
        listResultatKanji.setAdapter(adapter);

        listRadical.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                rechercheRadical.setText(rechercheRadical.getText().toString()+list.get(position));
            }
        });

        dbOpenHelper = new DatabaseHandler(this);
        try {
            dbOpenHelper.checkAndCopyDatabase();
            dbOpenHelper.openDatabase();
        }catch (SQLiteException e){
            e.printStackTrace();
        }

        nHiragana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recherche.setText(recherche.getText().toString() + "ん" );
            }
        });

        nKatakana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recherche.setText(recherche.getText().toString() + "ン" );
            }
        });

        nHiragana.setVisibility(View.GONE);
        nKatakana.setVisibility(View.GONE);
    }


    private View.OnClickListener rechercherListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onClickOnrecherche();
        }
    };

    private View.OnClickListener rechercherRadicalCorrectionListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String recherche = rechercheRadical.getText().toString();
            rechercheRadical.setText(recherche.substring(0,recherche.length()-1));
        }
    };

    private View.OnClickListener translateListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String value = recherche.getText().toString();
            task = new JapaneseKeyboardTask(recherche);
            task.execute(value);
        }
    };

    private void rechercheKanji(){
        kanjiList = new ArrayList<>();
        adapter.resetList();
        searchKanjiTask = new HNQueryTask(adapter);
        if (modeRecherche.equals("Radicals")){
            searchKanjiTask.execute(dbOpenHelper,"Radicals",rechercheRadical.getText().toString(),kanjiList);
        } else {
            searchKanjiTask.execute(dbOpenHelper,modeRecherche,recherche.getText().toString(),kanjiList);
        }


        final ProgressBar progress = (ProgressBar) findViewById(R.id.progress);
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                progress.setVisibility(View.GONE);
                result.setText("We found "+kanjiList.size()+" Kanji.");
            }
        });



    }


    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            result.setText(defaut);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String value = recherche.getText().toString();
        }
    };



    // Listener du bouton de remise à zéro
    private View.OnClickListener razListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            recherche.getText().clear();
            rechercheRadical.setText("");
            result.setText(defaut);
            kanjiList = new ArrayList<>();
            adapter.resetList();
        }
    };

    // Listener du bouton de la megafonction.
    private RadioGroup.OnCheckedChangeListener radioListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            if (i == R.id.radio1) {
                listRadical.setVisibility(View.VISIBLE);
                recherche.setVisibility(View.GONE);
                nHiragana.setVisibility(View.GONE);
                nKatakana.setVisibility(View.GONE);
                rechercheRadical.setVisibility(View.VISIBLE);
                rechercherRadicalCorrection.setVisibility(View.VISIBLE);
                modeRecherche = "Radicals";
                translate.setVisibility(View.GONE);
            }
            else if (i == R.id.radio2) {
                listRadical.setVisibility(View.GONE);
                rechercheRadical.setVisibility(View.GONE);
                recherche.setVisibility(View.VISIBLE);
                nHiragana.setVisibility(View.VISIBLE);
                nKatakana.setVisibility(View.VISIBLE);
                rechercherRadicalCorrection.setVisibility(View.GONE);
                modeRecherche = "Kana";
                translate.setVisibility(View.VISIBLE);

            } else {
                listRadical.setVisibility(View.GONE);
                rechercheRadical.setVisibility(View.GONE);
                recherche.setVisibility(View.VISIBLE);
                nHiragana.setVisibility(View.GONE);
                nKatakana.setVisibility(View.GONE);
                rechercherRadicalCorrection.setVisibility(View.GONE);
                modeRecherche = "Meaning";
                translate.setVisibility(View.GONE);
            }
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (searchKanjiTask != null){
            searchKanjiTask.cancel(true);
        }
        if(task != null){
            task.cancel(true);
        }
    }


    @Override
    public void onClickOnrecherche() {
        // On récupère la chaine de caractère du champ recherche
        kanjiList = new ArrayList<>();
        int radioButtonID = group.getCheckedRadioButtonId();
        View radioButton = group.findViewById(radioButtonID);
        int idx = group.indexOfChild(radioButton);
        RadioButton r = (RadioButton)  group.getChildAt(idx);
        modeRecherche  = r.getText().toString();
        rechercheKanji();
        result.setText(kanjiList.size()+" results where found !!!");
    }
}
