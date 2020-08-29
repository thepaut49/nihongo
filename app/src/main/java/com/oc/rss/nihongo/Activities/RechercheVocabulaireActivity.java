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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.oc.rss.nihongo.Adapter.VocabularyListAdapter;
import com.oc.rss.nihongo.AsynchTask.HNQueryTaskVocabulary;
import com.oc.rss.nihongo.AsynchTask.JapaneseKeyboardTask;
import com.oc.rss.nihongo.R;
import com.oc.rss.nihongo.entities.PartOfString;
import com.oc.rss.nihongo.entities.Vocabulary;
import com.oc.rss.nihongo.helper.DatabaseHandler;
import com.oc.rss.nihongo.interfaces.KanjiResultListner;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Antoine on 25/09/2016.
 */
public class RechercheVocabulaireActivity extends AppCompatActivity implements KanjiResultListner {

    private String categorie;

    // La chaîne de caractères par défaut
    private final String defaut = "You have to click on « Search » to begin the search.";

    // Database Helper
    DatabaseHandler dbOpenHelper;
    private HNQueryTaskVocabulary searchVocabularyTask = null;
    private JapaneseKeyboardTask task = null;
    private VocabularyListAdapter _adapter;
    private List<Vocabulary> vocabularyList = new ArrayList<>();
    private String modeRecherche ="kanji";

    ImageButton rechercherVocabulary = null;
    Button raz = null;
    ImageButton translate = null;
    Button nHiragana = null;
    Button nKatakana = null;

    EditText rechercheVocabulary = null;

    RadioGroup group = null;

    TextView resultVocabulary = null;

    RecyclerView listResultatVocabulary = null;
    private String stringToTranslate ="";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche_vocabulaire);

        categorie = getIntent().getStringExtra("categorie");
        setTitle("Search vocabulary");

        stringToTranslate = getIntent().getStringExtra("stringToTranslate");
        if (stringToTranslate ==null){
            stringToTranslate = "";
        }


        // On récupère toutes les vues dont on a besoin
        translate = (ImageButton) findViewById(R.id.traductionRechercheVocabulary);

        rechercherVocabulary = (ImageButton)findViewById(R.id.rechercherVocabulary);

        raz = (Button)findViewById(R.id.razVocabulary);

        nHiragana = (Button)findViewById(R.id.nHiraganaVoc);
        nKatakana = (Button)findViewById(R.id.nKatakanaVoc);

        rechercheVocabulary = (EditText)findViewById(R.id.rechercheVocabulary);

        group = (RadioGroup)findViewById(R.id.groupVocabulary);
        group.getChildAt(0).setSelected(true);

        resultVocabulary = (TextView)findViewById(R.id.resultVocabulary);

        // On attribue un listener adapté aux vues qui en ont besoin
        translate.setOnClickListener(translateListener);
        rechercherVocabulary.setOnClickListener(rechercherListener);
        raz.setOnClickListener(razListener);
        rechercheVocabulary.addTextChangedListener(textWatcher);
        group.setOnCheckedChangeListener(radioListener);

        listResultatVocabulary = (RecyclerView) findViewById(R.id.listResultatVocabulary);
        listResultatVocabulary.setLayoutManager(new LinearLayoutManager(this));
        _adapter = new VocabularyListAdapter(this,this,stringToTranslate);
        listResultatVocabulary.setAdapter(_adapter);

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
                rechercheVocabulary.setText(rechercheVocabulary.getText().toString() + "ん" );
            }
        });

        nKatakana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rechercheVocabulary.setText(rechercheVocabulary.getText().toString() + "ン" );
            }
        });
    }


    private View.OnClickListener rechercherListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onClickOnrecherche();
        }
    };

    private View.OnClickListener translateListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String value = rechercheVocabulary.getText().toString();
            task = new JapaneseKeyboardTask(rechercheVocabulary);
            task.execute(value);
        }
    };


    private void rechercheVocabulary(){
        vocabularyList = new ArrayList<>();
        _adapter.resetList();
        searchVocabularyTask = new HNQueryTaskVocabulary(_adapter);
        searchVocabularyTask.execute(dbOpenHelper,modeRecherche,rechercheVocabulary.getText().toString(),vocabularyList);
    }

    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            resultVocabulary.setText(defaut);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };



    // Listener du bouton de remise à zéro
    private View.OnClickListener razListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            rechercheVocabulary.getText().clear();
            resultVocabulary.setText(defaut);
            vocabularyList = new ArrayList<>();
            _adapter.resetList();
        }
    };

    // Listener du bouton de la megafonction.
    private RadioGroup.OnCheckedChangeListener radioListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            if (i == R.id.radioKanjiVoca) {
                modeRecherche = "Kanji";
                translate.setVisibility(View.VISIBLE);
                nHiragana.setVisibility(View.VISIBLE);
                nKatakana.setVisibility(View.VISIBLE);
            }
            else if (i == R.id.radioKanaVoca) {
                modeRecherche = "Kana";
                translate.setVisibility(View.VISIBLE);
                nHiragana.setVisibility(View.VISIBLE);
                nKatakana.setVisibility(View.VISIBLE);
            } else {
                modeRecherche = "Meaning";
                translate.setVisibility(View.GONE);
                nHiragana.setVisibility(View.GONE);
                nKatakana.setVisibility(View.GONE);
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onClickOnrecherche() {
        // On récupère la chaine de caractère du champ recherche
        vocabularyList = new ArrayList<>();
        rechercheVocabulary();
        _adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                resultVocabulary.setText("We found "+vocabularyList.size()+" vocabulary words.");
            }
        });
    }
}
