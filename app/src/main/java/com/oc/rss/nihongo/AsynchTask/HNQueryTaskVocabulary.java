package com.oc.rss.nihongo.AsynchTask;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;

import com.oc.rss.nihongo.entities.Vocabulary;
import com.oc.rss.nihongo.helper.DatabaseHandler;
import com.oc.rss.nihongo.interfaces.HNQueryCallbackVocabulary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class HNQueryTaskVocabulary extends AsyncTask<Object, Void, List<Vocabulary>> {
    private final HNQueryCallbackVocabulary _callback;
    // Database Helper
    DatabaseHandler dbOpenHelper;

    public HNQueryTaskVocabulary(HNQueryCallbackVocabulary callback) {
        this._callback = callback;
    }

    protected List<Vocabulary> doInBackground(Object... params) {
        dbOpenHelper = (DatabaseHandler) params[0];
        String modeRecherche = (String) params[1];
        String recherche = (String) params[2];
        List<Vocabulary> vocabularies = (List<Vocabulary>) params[3];
        HNQueryTaskVocabulary.HNVocabularyList list = new HNQueryTaskVocabulary.HNVocabularyList();

        if (modeRecherche.equals("All")){
            getAllNames(list);
        }else if (modeRecherche.equals("Id")){
            Integer idKanji = Integer.parseInt(recherche);
            getNamesById(list,idKanji);
        }else if ("Kanji".equals(modeRecherche)) {
            rechercheByKanji(list,recherche);
        } else if ("Kana".equals(modeRecherche)) {
            rechercheByKana(list,recherche);
        } else if ("Meaning".equals(modeRecherche)){
            rechercheByMeaning(list,recherche);
        } else if ("Associated".equals(modeRecherche)) {
            rechercheAssociatedVocabulary(list,recherche);
        } else if ("DecoupagePhrase".equals(modeRecherche)){
            searchWordsInSentence(list,recherche);
        } else if ("PartieManquante".equals(modeRecherche)){
            ajoutPartieManquante(recherche,list);
        }
        vocabularies.addAll(list.vocabularyList);
        return vocabularies;
    }


    protected void onPostExecute(List<Vocabulary> result) {
        if(result != null) {
            this._callback.onVocabularyReceived(result);
        }

    }

    public static class HNVocabularyList {
        public final List<Vocabulary> vocabularyList = new ArrayList();
        public HNVocabularyList() {}
    }

    private void getAllNames(HNQueryTaskVocabulary.HNVocabularyList list){
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
                        list.vocabularyList.add(vocabulary);
                    }while (cursor.moveToNext());
                }
            }
        }catch (SQLiteException e){
            e.printStackTrace();
        }finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        }
    }

    private void getNamesById(HNQueryTaskVocabulary.HNVocabularyList list, Integer id){
        Cursor cursor = null;

        try{
            cursor = dbOpenHelper.QueryData("Select * from vocabulary where id ="+id);
            if (cursor != null)
                cursor.moveToFirst();
            Vocabulary vocabulary = new Vocabulary(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(5), cursor.getString(4),cursor.getString(1),cursor.getString(2),cursor.getString(3));
            list.vocabularyList.add(vocabulary);
        }catch (SQLiteException e){
            e.printStackTrace();
        }finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        }
    }

    private void rechercheByKanji(HNQueryTaskVocabulary.HNVocabularyList list, String character){
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
                        if (vocabulary.getWord().contains(character)){
                            list.vocabularyList.add(vocabulary);
                        }
                    }while (cursor.moveToNext());
                }
            }
        }catch (SQLiteException e){
            e.printStackTrace();
        }finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        }
    }

    private void rechercheByKana(HNQueryTaskVocabulary.HNVocabularyList list, String kana){
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
                        if (vocabulary.getPronunciation().contains(kana)){
                            list.vocabularyList.add(vocabulary);
                        }
                    }while (cursor.moveToNext());
                }
            }
        }catch (SQLiteException e){
            e.printStackTrace();
        }finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        }
    }

    private void rechercheByMeaning(HNQueryTaskVocabulary.HNVocabularyList list, String meaning){
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
                        if (vocabulary.getMeaning1().contains(meaning) || vocabulary.getMeaning2().contains(meaning) || vocabulary.getMeaning3().contains(meaning) ){
                            list.vocabularyList.add(vocabulary);
                        }
                    }while (cursor.moveToNext());
                }
            }
        }catch (SQLiteException e){
            e.printStackTrace();
        }finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        }
    }


    private void rechercheAssociatedVocabulary(HNQueryTaskVocabulary.HNVocabularyList list, String character){
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
                        if (vocabulary.getWord().contains(character)){
                            list.vocabularyList.add(vocabulary);
                        }
                    }while (cursor.moveToNext());
                }
            }
        }catch (SQLiteException e){
            e.printStackTrace();
        }finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        }
    }

    private void searchWordsInSentence(HNQueryTaskVocabulary.HNVocabularyList list, final String stringToSplit){
        List<Vocabulary> allVocabulary = new ArrayList<>();
        List<Vocabulary> results = new ArrayList<>();
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

        for(int j = 6;j>0;j--){
            for (int i = 0;i<stringToSplit.length()-6;i++){
                for(Vocabulary voc : allVocabulary){
                    if (stringToSplit.substring(i, i+j).equals(voc.getWord())){
                        results.add(voc);
                    } else if (stringToSplit.substring(i, i+j).equals(voc.getPronunciation())){
                        results.add(voc);
                    }
                }
            }
        }


        for(int i = 0;i<results.size();i++){
            Boolean add = true;
            String wordI = "";
            if (stringToSplit.contains(results.get(i).getWord())){
                wordI = results.get(i).getWord();
            }else if (stringToSplit.contains(results.get(i).getPronunciation())){
                wordI = results.get(i).getPronunciation();
            }
            results.get(i).getWord();
            for(int j = 0;j<results.size();j++){
                String wordJ = "";
                if (stringToSplit.contains(results.get(j).getWord())){
                    wordJ = results.get(j).getWord();
                }else if (stringToSplit.contains(results.get(j).getPronunciation())){
                    wordJ = results.get(j).getPronunciation();
                }
                if (i!=j && wordJ.contains(wordI)){
                    if(compteOccurenceString(wordI,stringToSplit) > 1){
                        System.out.println("test");
                    }
                    else if (stringToSplit.indexOf(wordJ) == stringToSplit.indexOf(wordI)){
                        add = false;
                        break;
                    }
                    else {
                        int debWordI = stringToSplit.indexOf(wordI);
                        int finWordI = stringToSplit.indexOf(wordI)+ wordI.length()-1;
                        int debWordJ = stringToSplit.indexOf(wordJ);
                        int finWordJ = stringToSplit.indexOf(wordJ) + wordJ.length()-1;
                        if(debWordI >= debWordJ && finWordI <= finWordJ){
                            add = false;
                            break;
                        }
                    }
                }
            }
            if (add && !list.vocabularyList.contains(results.get(i))){
                list.vocabularyList.add(results.get(i));
            }
        }

        if (list.vocabularyList != null && list.vocabularyList.size() > 1){
            Collections.sort(list.vocabularyList, new Comparator<Vocabulary>() {
                @Override
                public int compare(Vocabulary voc1, Vocabulary voc2) {
                    String mot1 = "";
                    String mot2 = "";
                    if (stringToSplit.contains(voc1.getWord())){
                        mot1 = voc1.getWord();
                    } else {
                        mot1 = voc1.getPronunciation();
                    }

                    if (stringToSplit.contains(voc2.getWord())){
                        mot2 = voc2.getWord();
                    } else {
                        mot2 = voc2.getPronunciation();
                    }

                    int testPosition = stringToSplit.indexOf(mot1)  - stringToSplit.indexOf(mot2);
                    if (testPosition < 0){
                        return -1;
                    }else if (testPosition > 0){
                        return 1;
                    }else {
                        return 0;
                    }
                }
            });
        }
    }

    private void ajoutPartieManquante(String stringToTranslate,HNQueryTaskVocabulary.HNVocabularyList results){
        Integer deb=null,i = null,indMotSuiv =null,n=results.vocabularyList.size();
        Integer wordSize = null;
        String partieManquante = "";
        List<Vocabulary> listVoc = new ArrayList<Vocabulary>();
        if (results == null || results.vocabularyList.size() == 0){
            Vocabulary voc = new Vocabulary();
            voc.setWord(stringToTranslate);
            voc.setPronunciation(stringToTranslate);
            listVoc.add(voc);

        } else {
            if (stringToTranslate.contains(results.vocabularyList.get(0).getWord())){
                deb = stringToTranslate.indexOf(results.vocabularyList.get(0).getWord());
            }else {
                deb = stringToTranslate.indexOf(results.vocabularyList.get(0).getPronunciation());
            }

            if (deb > 0){
                partieManquante = stringToTranslate.substring(0,deb);
                Vocabulary voc1 = new Vocabulary();
                voc1.setWord(partieManquante);
                voc1.setPronunciation(partieManquante);
                listVoc.add(voc1);
            }

            for(i=0;i<n-1;i++){
                listVoc.add(results.vocabularyList.get(i));
                if (stringToTranslate.contains(results.vocabularyList.get(i+1).getWord())){
                    indMotSuiv = stringToTranslate.indexOf(results.vocabularyList.get(i+1).getWord());
                }else {
                    indMotSuiv = stringToTranslate.indexOf(results.vocabularyList.get(i+1).getPronunciation());
                }
                if (stringToTranslate.contains(results.vocabularyList.get(i).getWord())){
                    wordSize = results.vocabularyList.get(i).getWord().length();
                    deb = stringToTranslate.indexOf(results.vocabularyList.get(i).getWord());
                }else {
                    wordSize = results.vocabularyList.get(i).getPronunciation().length();
                    deb = stringToTranslate.indexOf(results.vocabularyList.get(i).getPronunciation());
                }
                if (deb+wordSize < indMotSuiv){
                    partieManquante = stringToTranslate.substring(deb+wordSize,indMotSuiv);
                    Vocabulary voc = new Vocabulary();
                    voc.setWord(partieManquante);
                    voc.setPronunciation(partieManquante);
                    listVoc.add(voc);
                }
            }
            listVoc.add(results.vocabularyList.get(n-1));
            if (stringToTranslate.contains(results.vocabularyList.get(n-1).getWord())){
                deb = stringToTranslate.indexOf(results.vocabularyList.get(n-1).getWord());
                wordSize = results.vocabularyList.get(n-1).getWord().length();
            }else {
                deb = stringToTranslate.indexOf(results.vocabularyList.get(n-1).getPronunciation());
                wordSize = results.vocabularyList.get(n-1).getPronunciation().length();
            }
            if (deb+wordSize != stringToTranslate.length()-1){
                partieManquante = stringToTranslate.substring(deb+wordSize);
                Vocabulary voc = new Vocabulary();
                voc.setWord(partieManquante);
                voc.setPronunciation(partieManquante);
                listVoc.add(voc);
            }
        }

        results.vocabularyList.clear();
        results.vocabularyList.addAll(listVoc);
    }

    public int compteOccurenceString(String word,String string){
        if (string.indexOf(word) == -1){
            return 0;
        } else {
            String[] stringTemp = string.split(word);
            return stringTemp.length-1;
        }
    }
}