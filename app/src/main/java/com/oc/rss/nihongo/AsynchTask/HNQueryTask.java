package com.oc.rss.nihongo.AsynchTask;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.util.Log;

import com.oc.rss.nihongo.entities.Kanji;
import com.oc.rss.nihongo.helper.DatabaseHandler;
import com.oc.rss.nihongo.interfaces.HNQueryCallback;


import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class HNQueryTask extends AsyncTask<Object, Void, List<Kanji>> {
    private final HNQueryCallback _callback;
    // Database Helper
    DatabaseHandler dbOpenHelper;

    public HNQueryTask(HNQueryCallback callback) {
        this._callback = callback;
    }

    protected List<Kanji> doInBackground(Object... params) {
        dbOpenHelper = (DatabaseHandler) params[0];
        String modeRecherche = (String) params[1];
        String recherche = (String) params[2];
        List<Kanji> results = (List<Kanji>) params[3];
        HNQueryTask.HNArticleList list = new HNQueryTask.HNArticleList();

        if (modeRecherche.equals("All")){
            getAllKanji(list);
        }else if (modeRecherche.equals("Id")){
            Integer idKanji = Integer.parseInt(recherche);
            getKanjiById(list,idKanji);
        }else if ("Kanji".equals(modeRecherche)) {
            rechercheByKanji(list,recherche);
        } else if ("Kana".equals(modeRecherche)) {
            rechercheByKana(list,recherche);
        } else if ("Meaning".equals(modeRecherche)) {
            rechercheByMeaning(list,recherche);
        } else if ("Radicals".equals(modeRecherche)) {
            Log.d("Avant fonction recherc","Let' go");
            rechercheByRadicals(list,recherche);
        } else if ("Associated".equals(modeRecherche)) {
            rechercheAssociatedKanji(list,recherche);
        } else if ("RAZ".equals(modeRecherche)){
        }
        results.addAll(list.articles);
        return results;

    }


    protected void onPostExecute(List<Kanji> result) {
        if(result != null) {
            this._callback.onArticlesReceived(result);
        }

    }

    public static class HNArticleList {
        public final List<Kanji> articles = new ArrayList();
        public HNArticleList() {

        }
    }

    private void getAllKanji(HNQueryTask.HNArticleList list){
        Cursor cursor = null;
        try{
            cursor=dbOpenHelper.QueryData("Select * from kanji");
            if (cursor != null){
                if (cursor.moveToFirst()){
                    do {
                        Kanji kanji = new Kanji();
                        kanji.setId(Integer.parseInt(cursor.getString(0)));
                        kanji.setCaractere(cursor.getString(1));
                        kanji.setPronunciation(cursor.getString(3));
                        kanji.setMeaning(cursor.getString(2));
                        kanji.setStrokes(Integer.parseInt(cursor.getString(5)));
                        kanji.setRadicals(cursor.getString(4));
                        list.articles.add(kanji);
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

    private void getKanjiById(HNQueryTask.HNArticleList list, Integer idKanji){
        Cursor cursor = null;

        try{
            cursor = dbOpenHelper.QueryData("Select * from kanji where id ="+idKanji);
            if (cursor != null)
                cursor.moveToFirst();


            Kanji kanji = new Kanji(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getString(3),cursor.getString(2),Integer.parseInt(cursor.getString(5)),cursor.getString(4));
            list.articles.add(kanji);
            if (cursor != null && !cursor.isClosed()){
                cursor.close();
            }

        }catch (SQLiteException e){
            e.printStackTrace();
        }finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        }
    }

    private void rechercheByKanji(HNQueryTask.HNArticleList list,String character){
        Cursor cursor = null;
        try{
            cursor=dbOpenHelper.QueryData("Select * from kanji");
            if (cursor != null){
                if (cursor.moveToFirst()){
                    do {
                        Kanji kanji = new Kanji();
                        kanji.setId(Integer.parseInt(cursor.getString(0)));
                        kanji.setCaractere(cursor.getString(1));
                        kanji.setPronunciation(cursor.getString(3));
                        kanji.setMeaning(cursor.getString(2));
                        kanji.setStrokes(Integer.parseInt(cursor.getString(5)));
                        kanji.setRadicals(cursor.getString(4));
                        if (character.equals(kanji.getCaractere())){
                            list.articles.add(kanji);
                            break;
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

    private void rechercheByKana(HNQueryTask.HNArticleList list,String kana){
        Cursor cursor = null;
        try{
            cursor=dbOpenHelper.QueryData("Select * from kanji");
            if (cursor != null){
                if (cursor.moveToFirst()){
                    do {
                        Kanji kanji = new Kanji();
                        kanji.setId(Integer.parseInt(cursor.getString(0)));
                        kanji.setCaractere(cursor.getString(1));
                        kanji.setPronunciation(cursor.getString(3));
                        kanji.setMeaning(cursor.getString(2));
                        kanji.setStrokes(Integer.parseInt(cursor.getString(5)));
                        kanji.setRadicals(cursor.getString(4));
                        if (kanji.getPronunciation().contains(kana)){
                            list.articles.add(kanji);
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

    private void rechercheByMeaning(HNQueryTask.HNArticleList list,String meaning){
        Cursor cursor = null;
        try{
            cursor=dbOpenHelper.QueryData("Select * from kanji");
            if (cursor != null){
                if (cursor.moveToFirst()){
                    do {
                        Kanji kanji = new Kanji();
                        kanji.setId(Integer.parseInt(cursor.getString(0)));
                        kanji.setCaractere(cursor.getString(1));
                        kanji.setPronunciation(cursor.getString(3));
                        kanji.setMeaning(cursor.getString(2));
                        kanji.setStrokes(Integer.parseInt(cursor.getString(5)));
                        kanji.setRadicals(cursor.getString(4));
                        if (kanji.getMeaning().contains(meaning)){
                            list.articles.add(kanji);
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

    private void rechercheByRadicals(HNQueryTask.HNArticleList list,String radicals){
        Log.d("we are in the fct","yeah");
        Cursor cursor = null;
        try{
            cursor=dbOpenHelper.QueryData("Select * from kanji");
            if (cursor != null){
                if (cursor.moveToFirst()){
                    do {
                        Kanji kanji = new Kanji();
                        kanji.setId(Integer.parseInt(cursor.getString(0)));
                        kanji.setCaractere(cursor.getString(1));
                        kanji.setPronunciation(cursor.getString(3));
                        kanji.setMeaning(cursor.getString(2));
                        kanji.setStrokes(Integer.parseInt(cursor.getString(5)));
                        kanji.setRadicals(cursor.getString(4));

                        boolean add = true;
                        for(int i =0;i < radicals.length();i++){
                            if (!kanji.getRadicals().contains(radicals.substring(i,i+1))){
                                add = false;
                                break;
                            }
                        }

                        if (add){
                            list.articles.add(kanji);
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

    private void rechercheAssociatedKanji(HNQueryTask.HNArticleList list,String character){
        Cursor cursor = null;
        Integer count = 0;
        try{
            cursor=dbOpenHelper.QueryData("Select * from kanji");
            if (cursor != null){
                if (cursor.moveToFirst()){
                    do {
                        Kanji kanji = new Kanji();
                        kanji.setId(Integer.parseInt(cursor.getString(0)));
                        kanji.setCaractere(cursor.getString(1));
                        kanji.setPronunciation(cursor.getString(3));
                        kanji.setMeaning(cursor.getString(2));
                        kanji.setStrokes(Integer.parseInt(cursor.getString(5)));
                        kanji.setRadicals(cursor.getString(4));
                        if (character.contains(kanji.getCaractere())){
                            list.articles.add(kanji);
                            count++;
                        }
                    }while (cursor.moveToNext() && count <= character.length());
                }
            }
        }catch (SQLiteException e){
            e.printStackTrace();
        }finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        }
    }
}