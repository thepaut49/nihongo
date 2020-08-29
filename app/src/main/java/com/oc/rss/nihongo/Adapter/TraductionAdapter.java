package com.oc.rss.nihongo.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.oc.rss.nihongo.Activities.FicheVocabularyVisuActivity;
import com.oc.rss.nihongo.R;
import com.oc.rss.nihongo.entities.Vocabulary;
import com.oc.rss.nihongo.interfaces.HNQueryCallbackVocabulary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TraductionAdapter extends RecyclerView.Adapter<TraductionAdapter.TraductionListViewHolder> {

    private static final int VIEW_TYPE_KANJI = 0;
    private static final int VIEW_TYPE_PROGRESS = 1;

    private List<Vocabulary> vocabularyList = new ArrayList<>();

    static Context _context;


    public TraductionAdapter(List<Vocabulary> listOfWords, Context context){
        vocabularyList = listOfWords;
        _context = context;
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
    public TraductionListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_mot_string, parent, false);
        return new TraductionListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TraductionListViewHolder holder, final int position) {
        holder.id.setText(vocabularyList.get(position).getId()+"");
        holder.word.setText(vocabularyList.get(position).getWord());
        holder.pronunciation.setText(vocabularyList.get(position).getPronunciation());
        holder.meaning1.setText(vocabularyList.get(position).getMeaning1());
        holder.meaning2.setText(vocabularyList.get(position).getMeaning2());
        holder.meaning3.setText(vocabularyList.get(position).getMeaning3());

        final String currentWord = holder.word.getText().toString();
        holder.plusUn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plusUn(position);
            }
        });

        holder.moinsUn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moinsUn(position);
            }
        });

        holder.deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItem(position);
            }
        });

    }


    public static class TraductionListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView id;
        public TextView word;
        public TextView pronunciation;
        public TextView meaning1;
        public TextView meaning2;
        public TextView meaning3;

        public ImageButton plusUn;
        public ImageButton moinsUn;
        public ImageButton deleteItem;




        public TraductionListViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            this.id = (TextView) itemView.findViewById(R.id.wordId);
            this.word = (TextView) itemView.findViewById(R.id.wordCharacter);
            this.pronunciation = (TextView) itemView.findViewById(R.id.wordPronunciation);
            this.meaning1 = (TextView) itemView.findViewById(R.id.wordMeaning1);
            this.meaning2 = (TextView) itemView.findViewById(R.id.wordMeaning2);
            this.meaning3 = (TextView) itemView.findViewById(R.id.wordMeaning3);
            this.plusUn = (ImageButton) itemView.findViewById(R.id.plusUn);
            this.moinsUn = (ImageButton) itemView.findViewById(R.id.moinsUn);
            this.deleteItem = (ImageButton) itemView.findViewById(R.id.deleteItem);




            itemLayoutView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (Integer.parseInt(id.getText().toString())!= 0){
                Intent intent = new Intent(_context, FicheVocabularyVisuActivity.class);
                Bundle extras = new Bundle();
                extras.putInt("position",Integer.parseInt(id.getText().toString()));
                intent.putExtras(extras);
                _context.startActivity(intent);
                Toast.makeText(VocabularyListAdapter._context, "you have clicked Row " + id.getText().toString(), Toast.LENGTH_LONG).show();
            }
        }
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressViewHolder(View itemView) { super(itemView); }
    }

    public void plusUn(int position){
        if (position != vocabularyList.size()-1){
            Collections.swap(vocabularyList,position,position+1);
            notifyItemMoved(position,position+1);
        }
    }

    public void moinsUn(int position){
        if (position != 0){
            Collections.swap(vocabularyList,position,position-1);
            notifyItemMoved(position,position-1);
        }
    }

    public void deleteItem(int position){
        vocabularyList.remove(position);
        notifyItemRemoved(position);
    }

    public void ajoutPartieManquante(String stringToTranslate){
        Integer deb=null,i = null,indMotSuiv =null,n=vocabularyList.size();
        Integer wordSize = null;
        String partieManquante = "";
        List<Vocabulary> listVoc = new ArrayList<Vocabulary>();
        if (vocabularyList == null || vocabularyList.size() == 0){
            Vocabulary voc = new Vocabulary();
            voc.setId(0);
            voc.setWord(stringToTranslate);
            voc.setPronunciation(stringToTranslate);
            listVoc.add(voc);

        } else {
            if (stringToTranslate.contains(vocabularyList.get(0).getWord())){
                deb = stringToTranslate.indexOf(vocabularyList.get(0).getWord());
            }else {
                deb = stringToTranslate.indexOf(vocabularyList.get(0).getPronunciation());
            }

            if (deb > 0){
                partieManquante = stringToTranslate.substring(0,deb);
                Vocabulary voc1 = new Vocabulary();
                voc1.setId(0);
                voc1.setWord(partieManquante);
                voc1.setPronunciation(partieManquante);
                listVoc.add(voc1);
            }

            for(i=0;i<n-1;i++){
                listVoc.add(vocabularyList.get(i));
                if (stringToTranslate.contains(vocabularyList.get(i+1).getWord())){
                    indMotSuiv = stringToTranslate.indexOf(vocabularyList.get(i+1).getWord());
                }else {
                    indMotSuiv = stringToTranslate.indexOf(vocabularyList.get(i+1).getPronunciation());
                }
                if (stringToTranslate.contains(vocabularyList.get(i).getWord())){
                    wordSize = vocabularyList.get(i).getWord().length();
                    deb = stringToTranslate.indexOf(vocabularyList.get(i).getWord());
                }else {
                    wordSize = vocabularyList.get(i).getPronunciation().length();
                    deb = stringToTranslate.indexOf(vocabularyList.get(i).getPronunciation());
                }
                if (deb+wordSize < indMotSuiv){
                    partieManquante = stringToTranslate.substring(deb+wordSize,indMotSuiv);
                    Vocabulary voc = new Vocabulary();
                    voc.setId(0);
                    voc.setWord(partieManquante);
                    voc.setPronunciation(partieManquante);
                    listVoc.add(voc);
                }
            }
            listVoc.add(vocabularyList.get(n-1));
            if (stringToTranslate.contains(vocabularyList.get(n-1).getWord())){
                deb = stringToTranslate.indexOf(vocabularyList.get(n-1).getWord());
                wordSize = vocabularyList.get(n-1).getWord().length();
            }else {
                deb = stringToTranslate.indexOf(vocabularyList.get(n-1).getPronunciation());
                wordSize = vocabularyList.get(n-1).getPronunciation().length();
            }
            if (deb+wordSize != stringToTranslate.length()-1){
                partieManquante = stringToTranslate.substring(deb+wordSize);
                Vocabulary voc = new Vocabulary();
                voc.setId(0);
                voc.setWord(partieManquante);
                voc.setPronunciation(partieManquante);
                listVoc.add(voc);
            }
        }

        vocabularyList.clear();
        vocabularyList.addAll(listVoc);
        notifyDataSetChanged();
    }
}