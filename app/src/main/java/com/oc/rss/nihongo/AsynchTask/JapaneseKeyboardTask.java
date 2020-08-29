package com.oc.rss.nihongo.AsynchTask;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.widget.TextView;

import com.oc.rss.nihongo.entities.Kanji;
import com.oc.rss.nihongo.helper.DatabaseHandler;
import com.oc.rss.nihongo.helper.JapaneseKeyboard;

import java.util.ArrayList;
import java.util.List;

public class JapaneseKeyboardTask extends AsyncTask<String, Void, String> {

    private final TextView textView;


    public JapaneseKeyboardTask(TextView textView) {
       this.textView = textView;
   }



   @Override
   protected String doInBackground(String... params) {
       String recherche = params[0];
       recherche = JapaneseKeyboard.translateString(recherche);
       return recherche;
   }

   protected void onPostExecute(String result) {
       textView.setText(result);
   }
}