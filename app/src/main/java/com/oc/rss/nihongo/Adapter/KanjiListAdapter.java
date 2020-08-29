package com.oc.rss.nihongo.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.oc.rss.nihongo.Activities.FicheKanjiVisuActivity;
import com.oc.rss.nihongo.R;
import com.oc.rss.nihongo.entities.Kanji;
import com.oc.rss.nihongo.entities.PartOfString;
import com.oc.rss.nihongo.interfaces.HNQueryCallback;

import java.util.ArrayList;
import java.util.List;

public class KanjiListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements HNQueryCallback{

    private static final int VIEW_TYPE_KANJI = 0;
    private static final int VIEW_TYPE_PROGRESS = 1;

    private List<Kanji> kanjiList = new ArrayList<>();
    static String stringToTranslate;
    static Activity _activity;
    static Context _context;

    public String getStringToTranslate() {
        return stringToTranslate;
    }

    public void setStringToTranslate(String stringToTranslate) {
        this.stringToTranslate = stringToTranslate;
    }

    public KanjiListAdapter(Activity activity, Context context){
        _activity = activity;
        _context = context;
    }

    public KanjiListAdapter(Activity activity, Context context,String stringToTranslateArg){
        _activity = activity;
        _context = context;
        stringToTranslate = stringToTranslateArg;
    }

    @Override
    public void onArticlesReceived(List<Kanji> kanjis) {
        kanjiList.addAll(kanjis);
        notifyDataSetChanged();
    }

    public void resetList() {
        kanjiList = new ArrayList<>();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return kanjiList.size();
    }


    @Override
    public int getItemViewType(int position) {
        if (position < kanjiList.size())
            return VIEW_TYPE_KANJI;
        else
            return VIEW_TYPE_PROGRESS;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_KANJI: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cell, parent, false);
                return new KanjiListViewHolder(view);
            }
            case VIEW_TYPE_PROGRESS: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_progress, parent, false);
                return new ProgressViewHolder(view);
            }
            default:
                throw new IllegalStateException("Unknown type" + viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof KanjiListViewHolder){
            ((KanjiListViewHolder) holder).id.setText(String.valueOf(kanjiList.get(position).getId()));
            ((KanjiListViewHolder) holder).character.setText(kanjiList.get(position).getCaractere());
            ((KanjiListViewHolder) holder).pronunciation.setText(kanjiList.get(position).getPronunciation());
            ((KanjiListViewHolder) holder).meaning.setText(kanjiList.get(position).getMeaning());
            ((KanjiListViewHolder) holder).strokes.setText(String.valueOf(kanjiList.get(position).getStrokes()));
            ((KanjiListViewHolder) holder).radicals.setText(kanjiList.get(position).getRadicals());
        }
    }


    public static class KanjiListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView id;
        public TextView character;
        public TextView pronunciation;
        public TextView meaning;
        public TextView strokes;
        public TextView radicals;

        public KanjiListViewHolder(View itemLayoutView) {
            super(itemLayoutView);

            this.id = (TextView) itemView.findViewById(R.id.idKanjiAfficher);
            this.character = (TextView) itemView.findViewById(R.id.characterKanjiAfficher);
            this.pronunciation = (TextView) itemView.findViewById(R.id.pronunciationKanjiAfficher);
            this.meaning = (TextView) itemView.findViewById(R.id.meaningKanjiAfficher);
            this.strokes = (TextView) itemView.findViewById(R.id.strokesKanjiAfficher);
            this.radicals = (TextView) itemView.findViewById(R.id.radicalsKanjiAfficher);

            itemLayoutView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(_context, FicheKanjiVisuActivity.class);
            Bundle extras = new Bundle();
            extras.putInt("position",Integer.parseInt(id.getText().toString()));
            intent.putExtra("stringToTranslate",stringToTranslate);
            intent.putExtras(extras);
            _context.startActivity(intent);
            Toast.makeText(KanjiListAdapter._context, "you have clicked Row " + id.getText().toString(), Toast.LENGTH_LONG).show();

        }
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressViewHolder(View itemView) { super(itemView); }
    }
}