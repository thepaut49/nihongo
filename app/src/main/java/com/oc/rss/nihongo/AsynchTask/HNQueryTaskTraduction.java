package com.oc.rss.nihongo.AsynchTask;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;

import com.oc.rss.nihongo.entities.PartOfString;
import com.oc.rss.nihongo.entities.Vocabulary;
import com.oc.rss.nihongo.helper.DatabaseHandler;
import com.oc.rss.nihongo.interfaces.HNQueryCallbackTraduction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class HNQueryTaskTraduction extends AsyncTask<Object, Void, List<PartOfString>> {
    private final HNQueryCallbackTraduction _callback;
    // Database Helper
    DatabaseHandler dbOpenHelper;

    public HNQueryTaskTraduction(HNQueryCallbackTraduction callback) {
        this._callback = callback;
    }

    protected List<PartOfString> doInBackground(Object... params) {
        dbOpenHelper = (DatabaseHandler) params[0];
        String modeRecherche = (String) params[1];
        String recherche = (String) params[2];
        List<PartOfString> vocabularies = (List<PartOfString>) params[3];
        HNQueryTaskTraduction.HNVocabularyList list = new HNQueryTaskTraduction.HNVocabularyList();

        if ("DecoupagePhrase".equals(modeRecherche)){
            searchWordsInSentence(list,recherche);
        }
        vocabularies.addAll(list.vocabularyList);
        return vocabularies;
    }


    protected void onPostExecute(List<PartOfString> result) {
        if(result != null) {
            this._callback.onVocabularyReceived(result);
        }

    }

    public static class HNVocabularyList {
        public final List<PartOfString> vocabularyList = new ArrayList();
        public HNVocabularyList() {}
    }


    private void searchWordsInSentence(HNQueryTaskTraduction.HNVocabularyList list, final String stringToSplit){
        List<Vocabulary> allVocabulary = new ArrayList<>();
        Cursor cursor = null;
        try{
            cursor=dbOpenHelper.QueryData("Select * from vocabulary");
            if (cursor != null){
                if (cursor.moveToFirst()){
                    do {
                        Vocabulary vocabulary = new Vocabulary();
                        vocabulary.setId(Integer.parseInt(cursor.getString(0)));
                        vocabulary.setWord(cursor.getString(5));
                        vocabulary.setPronunciation(cursor.getString(4));
                        vocabulary.setMeaning1(cursor.getString(1));
                        vocabulary.setMeaning2(cursor.getString(2));
                        vocabulary.setMeaning3(cursor.getString(3));
                        allVocabulary.add(vocabulary);
                    }while (cursor.moveToNext());
                }
            }
        }catch (SQLiteException e){
            e.printStackTrace();
        }finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        }
        Integer indiceCourant = 0;
        while(indiceCourant < stringToSplit.length()){
            for(int j = 6;j>0;j--){
                if (indiceCourant+j <= stringToSplit.length()){
                    for(Vocabulary voc : allVocabulary){
                        if (stringToSplit.substring(indiceCourant, indiceCourant+j).equals(voc.getWord())){
                            PartOfString partOfString = new PartOfString();
                            partOfString.setPositionInTheString(indiceCourant);
                            partOfString.setWordAssociated(voc);
                            partOfString.setWrittenInKanji(true);
                            partOfString.setLongueur(voc.getWord().length());
                            partOfString.setPartieInconnu(false);
                            list.vocabularyList.add(partOfString);
                        } else if (stringToSplit.substring(indiceCourant, indiceCourant+j).equals(voc.getPronunciation())){
                            PartOfString partOfString = new PartOfString();
                            partOfString.setPositionInTheString(indiceCourant);
                            partOfString.setWordAssociated(voc);
                            list.vocabularyList.add(partOfString);
                            partOfString.setWrittenInKanji(false);
                            partOfString.setLongueur(voc.getPronunciation().length());
                            partOfString.setPartieInconnu(false);
                        }
                    }
                }
            }
            indiceCourant = indiceCourant + 1;
        }

    }
}