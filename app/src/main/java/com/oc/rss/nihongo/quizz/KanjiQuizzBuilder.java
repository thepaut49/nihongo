package com.oc.rss.nihongo.quizz;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;



import com.oc.rss.nihongo.entities.Kanji;
import com.oc.rss.nihongo.entities.Vocabulary;
import com.oc.rss.nihongo.helper.DatabaseHandler;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Antoine on 15/12/2017.
 */

public class KanjiQuizzBuilder extends AsyncTask<Object, Void, QuestionLibrary> {

    // Database Helper
    DatabaseHandler dbOpenHelper;
    QuestionLibrary questionLibrary;


    public KanjiQuizzBuilder() {

    }

    protected QuestionLibrary doInBackground(Object... params) {
        dbOpenHelper = (DatabaseHandler) params[0];
        String typeQuizz = (String) params[1];
        QuestionLibrary results = new QuestionLibrary();

        if ("quizzKanjiEngToJap".equals(typeQuizz)){
            results = quizzKanjiEngToJap();
        }else if ("quizzKanjiJapToEng".equals(typeQuizz)){
            results =quizzKanjiJapToEng();
        }else if ("quizzKanjiPronunciation".equals(typeQuizz)) {
            results =quizzKanjiPronunciation();
        } else if ("quizzVocEngToJap".equals(typeQuizz)) {
            results =quizzVocEngToJap();
        } else if ("quizzVocJapToEng".equals(typeQuizz)) {
            results =quizzVocJapToEng();
        } else if ("quizzVocPronunciation".equals(typeQuizz)) {
            results =quizzVocPronunciation();
        }

        return results;

    }


    protected void onPostExecute(QuestionLibrary result) {
            this.questionLibrary = result;
    }


    private QuestionLibrary quizzKanjiEngToJap(){
        List<Kanji> kanjiList = new ArrayList<>();
        QuestionLibrary result = new QuestionLibrary();
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
                        kanjiList.add(kanji);
                    }while (cursor.moveToNext());
                }
            }
        }catch (SQLiteException e){
            e.printStackTrace();
        }finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        }

        Collections.shuffle(kanjiList);
        int i = 0;
        int indQuestion = 0;
        while( i <= 57){
            //we set the question
            result.setQuestion(indQuestion,kanjiList.get(i).getMeaning());
            // we set the correct answer
            result.setCorrectAnswer(indQuestion,kanjiList.get(i).getCaractere());
            //we set the list of choice by shuffling the kanji with index i,i+1,i+2
            List<String> listOfChoice = new ArrayList<>();
            listOfChoice.add(kanjiList.get(i).getCaractere());
            listOfChoice.add(kanjiList.get(i+1).getCaractere());
            listOfChoice.add(kanjiList.get(i+2).getCaractere());
            Collections.shuffle(listOfChoice);
            result.setChoice1(indQuestion,listOfChoice.get(0));
            result.setChoice2(indQuestion,listOfChoice.get(1));
            result.setChoice3(indQuestion,listOfChoice.get(2));
            indQuestion = indQuestion + 1;
            i = i + 3;
        }


        return result;

    }

    private QuestionLibrary quizzKanjiJapToEng(){
        List<Kanji> kanjiList = new ArrayList<>();
        QuestionLibrary result = new QuestionLibrary();
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
                        kanjiList.add(kanji);
                    }while (cursor.moveToNext());
                }
            }
        }catch (SQLiteException e){
            e.printStackTrace();
        }finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        }

        Collections.shuffle(kanjiList);
        int i = 0;
        int indQuestion = 0;
        while( i <= 57){
            //we set the question
            result.setQuestion(indQuestion,kanjiList.get(i).getCaractere());
            // we set the correct answer
            result.setCorrectAnswer(indQuestion,kanjiList.get(i).getMeaning());
            //we set the list of choice by shuffling the kanji with index i,i+1,i+2
            List<String> listOfChoice = new ArrayList<>();
            listOfChoice.add(kanjiList.get(i).getMeaning());
            listOfChoice.add(kanjiList.get(i+1).getMeaning());
            listOfChoice.add(kanjiList.get(i+2).getMeaning());
            Collections.shuffle(listOfChoice);
            result.setChoice1(indQuestion,listOfChoice.get(0));
            result.setChoice2(indQuestion,listOfChoice.get(1));
            result.setChoice3(indQuestion,listOfChoice.get(2));
            indQuestion = indQuestion + 1;
            i = i + 3;
        }


        return result;

    }

    private QuestionLibrary quizzKanjiPronunciation(){
        List<Kanji> kanjiList = new ArrayList<>();
        QuestionLibrary result = new QuestionLibrary();
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
                        kanjiList.add(kanji);
                    }while (cursor.moveToNext());
                }
            }
        }catch (SQLiteException e){
            e.printStackTrace();
        }finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        }

        Collections.shuffle(kanjiList);
        int i = 0;
        int indQuestion = 0;
        while( i <= 57){
            //we set the question
            result.setQuestion(indQuestion,kanjiList.get(i).getCaractere());
            // we set the correct answer
            result.setCorrectAnswer(indQuestion,kanjiList.get(i).getPronunciation());
            //we set the list of choice by shuffling the kanji with index i,i+1,i+2
            List<String> listOfChoice = new ArrayList<>();
            listOfChoice.add(kanjiList.get(i).getPronunciation());
            listOfChoice.add(kanjiList.get(i+1).getPronunciation());
            listOfChoice.add(kanjiList.get(i+2).getPronunciation());
            Collections.shuffle(listOfChoice);
            result.setChoice1(indQuestion,listOfChoice.get(0));
            result.setChoice2(indQuestion,listOfChoice.get(1));
            result.setChoice3(indQuestion,listOfChoice.get(2));
            indQuestion = indQuestion + 1;
            i = i + 3;
        }

        return result;
    }

    private QuestionLibrary quizzVocEngToJap(){
        List<Vocabulary> vocList = new ArrayList<>();
        QuestionLibrary result = new QuestionLibrary();
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
                        vocList.add(vocabulary);
                    }while (cursor.moveToNext());
                }
            }
        }catch (SQLiteException e){
            e.printStackTrace();
        }finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        }

        Collections.shuffle(vocList);
        int i = 0;
        int indQuestion = 0;
        while( i <= 57){
            //we set the question
            result.setQuestion(indQuestion,vocList.get(i).getMeaning1() +";"+ vocList.get(i).getMeaning2() +";"+ vocList.get(i).getMeaning3());
            // we set the correct answer
            result.setCorrectAnswer(indQuestion,vocList.get(i).getWord());
            //we set the list of choice by shuffling the kanji with index i,i+1,i+2
            List<String> listOfChoice = new ArrayList<>();
            listOfChoice.add(vocList.get(i).getWord());
            listOfChoice.add(vocList.get(i+1).getWord());
            listOfChoice.add(vocList.get(i+2).getWord());
            Collections.shuffle(listOfChoice);
            result.setChoice1(indQuestion,listOfChoice.get(0));
            result.setChoice2(indQuestion,listOfChoice.get(1));
            result.setChoice3(indQuestion,listOfChoice.get(2));
            indQuestion = indQuestion + 1;
            i = i + 3;
        }

        return result;

    }

    private QuestionLibrary quizzVocJapToEng(){
        List<Vocabulary> vocList = new ArrayList<>();
        QuestionLibrary result = new QuestionLibrary();
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
                        vocList.add(vocabulary);
                    }while (cursor.moveToNext());
                }
            }
        }catch (SQLiteException e){
            e.printStackTrace();
        }finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        }

        Collections.shuffle(vocList);
        int i = 0;
        int indQuestion = 0;
        while( i <= 57){
            //we set the question
            result.setQuestion(indQuestion,vocList.get(i).getWord());
            // we set the correct answer
            result.setCorrectAnswer(indQuestion,vocList.get(i).getMeaning1() +";"+ vocList.get(i).getMeaning2() +";"+ vocList.get(i).getMeaning3());
            //we set the list of choice by shuffling the kanji with index i,i+1,i+2
            List<String> listOfChoice = new ArrayList<>();
            listOfChoice.add(vocList.get(i).getMeaning1() +";"+ vocList.get(i).getMeaning2() +";"+ vocList.get(i).getMeaning3());
            listOfChoice.add(vocList.get(i+1).getMeaning1() +";"+ vocList.get(i+1).getMeaning2() +";"+ vocList.get(i+1).getMeaning3());
            listOfChoice.add(vocList.get(i+2).getMeaning1() +";"+ vocList.get(i+2).getMeaning2() +";"+ vocList.get(i+2).getMeaning3());
            Collections.shuffle(listOfChoice);
            result.setChoice1(indQuestion,listOfChoice.get(0));
            result.setChoice2(indQuestion,listOfChoice.get(1));
            result.setChoice3(indQuestion,listOfChoice.get(2));
            indQuestion = indQuestion +1;
            i = i + 3;
        }

        return result;

    }

    private QuestionLibrary quizzVocPronunciation(){
        List<Vocabulary> vocList = new ArrayList<>();
        QuestionLibrary result = new QuestionLibrary();
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
                        vocList.add(vocabulary);
                    }while (cursor.moveToNext());
                }
            }
        }catch (SQLiteException e){
            e.printStackTrace();
        }finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        }

        Collections.shuffle(vocList);
        int i = 0;
        int indQuestion = 0;
        while( i <= 57){
            //we set the question
            result.setQuestion(indQuestion,vocList.get(i).getWord());
            // we set the correct answer
            result.setCorrectAnswer(indQuestion,vocList.get(i).getPronunciation());
            //we set the list of choice by shuffling the kanji with index i,i+1,i+2
            List<String> listOfChoice = new ArrayList<>();
            listOfChoice.add(vocList.get(i).getPronunciation());
            listOfChoice.add(vocList.get(i+1).getPronunciation());
            listOfChoice.add(vocList.get(i+2).getPronunciation());
            Collections.shuffle(listOfChoice);
            result.setChoice1(indQuestion,listOfChoice.get(0));
            result.setChoice2(indQuestion,listOfChoice.get(1));
            result.setChoice3(indQuestion,listOfChoice.get(2));
            indQuestion = indQuestion + 1;
            i = i + 3;
        }

        return result;

    }
}
