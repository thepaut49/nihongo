package com.oc.rss.nihongo.helper;
 
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import com.oc.rss.nihongo.entities.Kanji;
import com.oc.rss.nihongo.entities.Vocabulary;

public class DatabaseHandler extends SQLiteOpenHelper {
 
    // All Static variables
    // Database Version
    public static final int DATABASE_VERSION = 1;

    // Database Path
    public static String DATABASE_PATH = "";
 
    // Database Name
    public static final String DATABASE_NAME = "toto4.db";
 
    // Kanji table name
    public static final String TABLE_KANJI = "kanji";
 
    // Kanji Table Columns names
    public static final String KEY = "id";
    public static final String CHARACTER = "caractere";
    public static final String PRONUNCIATION = "pronunciation";
    public static final String MEANING = "meaning";
    public static final String STROKES = "strokes";
    public static final String RADICALS = "radicals";

    private SQLiteDatabase myDatabase;
    private final Context myContext;

    //table kanji create statement
    private static final String CREATE_TABLE_KANJI = "CREATE TABLE " + TABLE_KANJI + " (" + KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CHARACTER + " TEXT, " + PRONUNCIATION + " TEXT, "
            + MEANING + " TEXT, "+STROKES+ " INTEGER, "+RADICALS+"  TEXT);";

    // Vocabulary table name
    public static final String TABLE_VOCABULARY = "vocabulary";

    // Vocabulary Table Columns names
    public static final String WORD = "WORD";
    public static final String MEANING1 = "meaning1";
    public static final String MEANING2 = "meaning2";
    public static final String MEANING3 = "meaning3";

    //table Vocabulaire create statement
    private static final String CREATE_TABLE_VOCABULARY = "CREATE TABLE " + TABLE_VOCABULARY + " (" + KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " + WORD + " TEXT, " + PRONUNCIATION + " TEXT, "
            + MEANING1 + " TEXT, "+MEANING2+ " TEXT, "+MEANING3+"  TEXT);";



    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        if (Build.VERSION.SDK_INT >= 15){
            DATABASE_PATH = context.getApplicationInfo().dataDir+"/databases/";
        }else {
            DATABASE_PATH = Environment.getDataDirectory() +"/data/" + context.getPackageName()+"/databases/";
        }
        this.myContext = context;
    }


 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
       // db.execSQL(CREATE_TABLE_KANJI);
       // db.execSQL(CREATE_TABLE_VOCABULARY);
       // db.execSQL(CREATE_TABLE_NAME);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_KANJI);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VOCABULARY);

        // Create tables again
        onCreate(db);
    }
 
    /**
     * All CRUD(Create, Read, Update, Delete) Operations for kanji table
     */

    // Adding new kanji
    public void addKanji(Kanji kanji) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CHARACTER, kanji.getCaractere()); // Kanji character
        values.put(PRONUNCIATION, kanji.getPronunciation()); // kanji pronunciation
        values.put(MEANING, kanji.getMeaning()); // kanji meaning
        values.put(STROKES, kanji.getStrokes()); // Ckanji number of strokes
        values.put(RADICALS, kanji.getRadicals()); // kanji radicals

        // Inserting Row
        db.insert(TABLE_KANJI, null, values);
        db.close(); // Closing database connection
    }

    // Getting single kanji
    public Kanji getKanji(String character) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_KANJI, new String[] { KEY,
                        CHARACTER, PRONUNCIATION, MEANING, STROKES, RADICALS }, CHARACTER + "=?",
                new String[] { character }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Kanji kanji = new Kanji(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),cursor.getString(3),Integer.parseInt(cursor.getString(4)),cursor.getString(5));
        return kanji;
    }


    // Getting  Kanjis with the same pronunciation
    public List<Kanji> getKanjisWithPronunciation(String pronunciation) {
        List<Kanji> kanjiList = new ArrayList<Kanji>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_KANJI;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Kanji kanji = new Kanji();
                kanji.setId(Integer.parseInt(cursor.getString(0)));
                kanji.setCaractere(cursor.getString(1));
                kanji.setPronunciation(cursor.getString(2));
                kanji.setMeaning(cursor.getString(3));
                kanji.setStrokes(Integer.parseInt(cursor.getString(4)));
                kanji.setRadicals(cursor.getString(5));
                if (kanji.getPronunciation().contains(pronunciation)){
                    kanjiList.add(kanji);
                }
            } while (cursor.moveToNext());
        }

        // return kanji list
        return kanjiList;
    }

    // Getting  Kanjis with the same meaning
    public List<Kanji> getKanjisWithMeaning(String meaning) {
        List<Kanji> kanjiList = new ArrayList<Kanji>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_KANJI;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Kanji kanji = new Kanji();
                kanji.setId(Integer.parseInt(cursor.getString(0)));
                kanji.setCaractere(cursor.getString(1));
                kanji.setPronunciation(cursor.getString(2));
                kanji.setMeaning(cursor.getString(3));
                kanji.setStrokes(Integer.parseInt(cursor.getString(4)));
                kanji.setRadicals(cursor.getString(5));
                if (kanji.getMeaning().contains(meaning)){
                    kanjiList.add(kanji);
                }
            } while (cursor.moveToNext());
        }

        // return kanji list
        return kanjiList;
    }



    /**
     * All CRUD(Create, Read, Update, Delete) Operations for vocabulary table
     */

    // Adding new vocabulary word
    public void addVocabulary(Vocabulary vocabulary) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(WORD, vocabulary.getWord()); // vocabulary word
        values.put(PRONUNCIATION, vocabulary.getPronunciation()); // vocabulary pronunciation
        values.put(MEANING1, vocabulary.getMeaning1()); // vocabulary meaning1
        values.put(MEANING2, vocabulary.getMeaning2()); // vocabulary meaning2
        values.put(MEANING3, vocabulary.getMeaning3()); // vocabulary meaning3

        // Inserting Row
        db.insert(TABLE_VOCABULARY, null, values);
        db.close(); // Closing database connection
    }

    // Getting single vocabulary word
    public Vocabulary getVocabulary(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_VOCABULARY, new String[] { KEY,
                        WORD, PRONUNCIATION, MEANING1, MEANING2, MEANING3 }, KEY + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Vocabulary vocabulary = new Vocabulary(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
        return vocabulary;
    }

    // Getting All Vocabulary words
    public List<Vocabulary> getAllVocabulary() {
        List<Vocabulary> vocabularyList = new ArrayList<Vocabulary>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_VOCABULARY;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Vocabulary vocabulary = new Vocabulary();
                vocabulary.setId(Integer.parseInt(cursor.getString(0)));
                vocabulary.setWord(cursor.getString(1));
                vocabulary.setPronunciation(cursor.getString(2));
                vocabulary.setMeaning1(cursor.getString(3));
                vocabulary.setMeaning2(cursor.getString(4));
                vocabulary.setMeaning3(cursor.getString(5));
                // Adding vocabulary to list
                vocabularyList.add(vocabulary);
            } while (cursor.moveToNext());
        }

        // return vocabulary list
        return vocabularyList;
    }

    // Getting Vocabulary Count
    public int getVocabularyCount() {
        String countQuery = "SELECT  * FROM " + TABLE_VOCABULARY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }


    // Getting  vocabulary with the word
    public List<Vocabulary> getVocabularyWithWord(String word) {
        List<Vocabulary> vocabularyList = new ArrayList<Vocabulary>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_VOCABULARY;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Vocabulary vocabulary = new Vocabulary();
                vocabulary.setId(Integer.parseInt(cursor.getString(0)));
                vocabulary.setWord(cursor.getString(1));
                vocabulary.setPronunciation(cursor.getString(2));
                vocabulary.setMeaning1(cursor.getString(3));
                vocabulary.setMeaning2(cursor.getString(4));
                vocabulary.setMeaning3(cursor.getString(5));
                if (vocabulary.getWord().contains(word)){
                    vocabularyList.add(vocabulary);
                }
            } while (cursor.moveToNext());
        }

        // return vocabulary list
        return vocabularyList;
    }


    // Getting  vocabulary with the same pronunciation
    public List<Vocabulary> getVocabularyWithPronunciation(String pronunciation) {
        List<Vocabulary> vocabularyList = new ArrayList<Vocabulary>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_VOCABULARY;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Vocabulary vocabulary = new Vocabulary();
                vocabulary.setId(Integer.parseInt(cursor.getString(0)));
                vocabulary.setWord(cursor.getString(1));
                vocabulary.setPronunciation(cursor.getString(2));
                vocabulary.setMeaning1(cursor.getString(3));
                vocabulary.setMeaning2(cursor.getString(4));
                vocabulary.setMeaning3(cursor.getString(5));
                if (vocabulary.getPronunciation().contains(pronunciation)){
                    vocabularyList.add(vocabulary);
                }
            } while (cursor.moveToNext());
        }

        // return vocabulary list
        return vocabularyList;
    }

    // Getting  Vocabulary with the same meaning
    public List<Vocabulary> getVocabularyWithMeaning(String meaning) {
        List<Vocabulary> vocabularyList = new ArrayList<Vocabulary>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_VOCABULARY;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Vocabulary vocabulary = new Vocabulary();
                vocabulary.setId(Integer.parseInt(cursor.getString(0)));
                vocabulary.setWord(cursor.getString(1));
                vocabulary.setPronunciation(cursor.getString(2));
                vocabulary.setMeaning1(cursor.getString(3));
                vocabulary.setMeaning2(cursor.getString(4));
                vocabulary.setMeaning3(cursor.getString(5));
                if (vocabulary.getMeaning1().contains(meaning) || vocabulary.getMeaning2().contains(meaning)  || vocabulary.getMeaning3().contains(meaning) ){
                    vocabularyList.add(vocabulary);
                }
            } while (cursor.moveToNext());
        }

        // return vocabulary list
        return vocabularyList;
    }


    public void checkAndCopyDatabase(){
        boolean dbExist = checkDatabase();
        if (dbExist){
            Log.d("Tag","database already exist");
        } else {
            this.getReadableDatabase();
        }
        try {
            copyDatabase();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("TAG","Error copy database");
        }
    }

    public boolean checkDatabase(){
        SQLiteDatabase checkDB =null;

        try {
            String myPath = DATABASE_PATH+DATABASE_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READWRITE);
        } catch (SQLiteException el ){

        }

        if (checkDB != null){
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    public void copyDatabase() throws IOException {
        InputStream myInput = myContext.getAssets().open(DATABASE_NAME);
        String outFileName = DATABASE_PATH+DATABASE_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while((length = myInput.read(buffer)) > 0){
            myOutput.write(buffer,0,length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDatabase(){
        String  myPath = DATABASE_PATH+DATABASE_NAME;
        myDatabase = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READWRITE);

    }

    // closing database
    public synchronized void closeDB() {

        if (myDatabase != null){
            myDatabase.close();
        }
        super.close();
    }

    public Cursor QueryData(String query){
        return myDatabase.rawQuery(query,null);
    }
 
}