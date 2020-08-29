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


import com.oc.rss.nihongo.Activities.FicheVocabularyVisuActivity;
import com.oc.rss.nihongo.R;
import com.oc.rss.nihongo.entities.PartOfString;
import com.oc.rss.nihongo.entities.Vocabulary;
import com.oc.rss.nihongo.interfaces.HNQueryCallbackVocabulary;

import java.util.ArrayList;
import java.util.List;

public class VocabularyListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements HNQueryCallbackVocabulary {

    private static final int VIEW_TYPE_KANJI = 0;
    private static final int VIEW_TYPE_PROGRESS = 1;

    private List<Vocabulary> vocabularyList = new ArrayList<>();

    static Activity _activity;
    static Context _context;
    static String stringToTranslate;


    public static String getStringToTranslate() {
        return stringToTranslate;
    }

    public static void setStringToTranslate(String stringToTranslate) {
        VocabularyListAdapter.stringToTranslate = stringToTranslate;
    }

    public VocabularyListAdapter(Activity activity, Context context){
        _activity = activity;
        _context = context;
    }

    public VocabularyListAdapter(Activity activity, Context context,String stringToTranslateArg ){
        _activity = activity;
        _context = context;
        stringToTranslate = stringToTranslateArg;
    }

    @Override
    public void onVocabularyReceived(List<Vocabulary> vocabularies) {
        vocabularyList.addAll(vocabularies);
        notifyDataSetChanged();
    }

    public void resetList() {
        vocabularyList = new ArrayList<>();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return vocabularyList.size();
    }


    @Override
    public int getItemViewType(int position) {
        if (position < vocabularyList.size())
            return VIEW_TYPE_KANJI;
        else
            return VIEW_TYPE_PROGRESS;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_KANJI: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cell_vocabulary, parent, false);
                return new VocabularyListViewHolder(view);
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
        if (holder instanceof VocabularyListViewHolder){
            ((VocabularyListViewHolder) holder).id.setText(String.valueOf(vocabularyList.get(position).getId()));
            ((VocabularyListViewHolder) holder).word.setText(vocabularyList.get(position).getWord());
            ((VocabularyListViewHolder) holder).pronunciation.setText(vocabularyList.get(position).getPronunciation());
            ((VocabularyListViewHolder) holder).meaning1.setText(vocabularyList.get(position).getMeaning1());
            ((VocabularyListViewHolder) holder).meaning2.setText(vocabularyList.get(position).getMeaning2());
            ((VocabularyListViewHolder) holder).meaning3.setText(vocabularyList.get(position).getMeaning3());
        }
    }


    public static class VocabularyListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView id;
        public TextView word;
        public TextView pronunciation;
        public TextView meaning1;
        public TextView meaning2;
        public TextView meaning3;


        public VocabularyListViewHolder(View itemLayoutView) {
            super(itemLayoutView);

            this.id = (TextView) itemView.findViewById(R.id.idVocabularyCell);
            this.word = (TextView) itemView.findViewById(R.id.characterVocabularyCell);
            this.pronunciation = (TextView) itemView.findViewById(R.id.pronunciationVocabularyCell);
            this.meaning1 = (TextView) itemView.findViewById(R.id.meaning1VocabularyCell);
            this.meaning2 = (TextView) itemView.findViewById(R.id.meaning2VocabularyCell);
            this.meaning3 = (TextView) itemView.findViewById(R.id.meaning3VocabularyCell);


            itemLayoutView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(_context, FicheVocabularyVisuActivity.class);
            Bundle extras = new Bundle();
            extras.putInt("position",Integer.parseInt(id.getText().toString()));
            intent.putExtra("stringToTranslate",stringToTranslate);
            intent.putExtras(extras);
            _context.startActivity(intent);
            Toast.makeText(VocabularyListAdapter._context, "you have clicked Row " + id.getText().toString(), Toast.LENGTH_LONG).show();

        }
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressViewHolder(View itemView) { super(itemView); }
    }
}