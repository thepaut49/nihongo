package com.oc.rss.nihongo.Adapter;


import android.content.Context;
import android.content.Intent;
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
import com.oc.rss.nihongo.Utils.AnimationUtil;
import com.oc.rss.nihongo.entities.PartOfString;
import com.oc.rss.nihongo.entities.Vocabulary;
import com.oc.rss.nihongo.interfaces.HNQueryCallbackTraduction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class MyCustomAdapter extends RecyclerView.Adapter<MyCustomAdapter.MyViewHolder> implements HNQueryCallbackTraduction {

    private Context context;

    private ArrayList<PartOfString> data = new ArrayList<>();

    private LayoutInflater inflater;

    private int previousPosition = 0;

    public MyCustomAdapter(Context context, ArrayList<PartOfString> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int position) {

        View view = inflater.inflate(R.layout.list_mot_string, parent, false);

        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {

        myViewHolder.id.setText(data.get(position).getWordAssociated().getId()+"");
        myViewHolder.word.setText(data.get(position).getWordAssociated().getWord());
        myViewHolder.pronunciation.setText(data.get(position).getWordAssociated().getPronunciation());
        myViewHolder.meaning1.setText(data.get(position).getWordAssociated().getMeaning1());
        myViewHolder.meaning2.setText(data.get(position).getWordAssociated().getMeaning2());
        myViewHolder.meaning3.setText(data.get(position).getWordAssociated().getMeaning3());

        if(position > previousPosition){ // We are scrolling DOWN

            AnimationUtil.animate(myViewHolder, true);

        }else{ // We are scrolling UP

            AnimationUtil.animate(myViewHolder, false);


        }

        previousPosition = position;


        final int currentPosition = myViewHolder.getAdapterPosition();
        final PartOfString infoData = data.get(position);

        myViewHolder.deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItem(currentPosition);
            }
        });

        myViewHolder.plusUn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plusUn(currentPosition);
            }
        });

        myViewHolder.moinsUn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moinsUn(currentPosition);
            }
        });

        myViewHolder.word.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (infoData.getPartieInconnu() != null && !infoData.getPartieInconnu()){
                    Intent intent = new Intent(context, FicheVocabularyVisuActivity.class);
                    Bundle extras = new Bundle();
                    extras.putInt("position",infoData.getWordAssociated().getId());
                    intent.putExtras(extras);
                    context.startActivity(intent);
                    Toast.makeText(context, "you have clicked Row " + infoData.getWordAssociated().getId().toString(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        if (data != null){
            return data.size();
        }
        return 0;
    }

    @Override
    public void onVocabularyReceived(List<PartOfString> var1) {
        data.addAll(var1);
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView id;
        public TextView word;
        public TextView pronunciation;
        public TextView meaning1;
        public TextView meaning2;
        public TextView meaning3;
        public TextView un;
        public TextView deux;
        public TextView trois;

        public ImageButton plusUn;
        public ImageButton moinsUn;
        public ImageButton deleteItem;

        public MyViewHolder(View itemView) {
            super(itemView);

            this.id = (TextView) itemView.findViewById(R.id.wordId);
            this.word = (TextView) itemView.findViewById(R.id.wordCharacter);
            this.pronunciation = (TextView) itemView.findViewById(R.id.wordPronunciation);
            this.meaning1 = (TextView) itemView.findViewById(R.id.wordMeaning1);
            this.meaning2 = (TextView) itemView.findViewById(R.id.wordMeaning2);
            this.meaning3 = (TextView) itemView.findViewById(R.id.wordMeaning3);
            this.plusUn = (ImageButton) itemView.findViewById(R.id.plusUn);
            this.moinsUn = (ImageButton) itemView.findViewById(R.id.moinsUn);
            this.deleteItem = (ImageButton) itemView.findViewById(R.id.deleteItem);
            this.un = (TextView) itemView.findViewById(R.id.un);
            this.deux = (TextView) itemView.findViewById(R.id.deux);
            this.trois = (TextView) itemView.findViewById(R.id.trois);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (Integer.parseInt(id.getText().toString())!= 0){
                Intent intent = new Intent(context, FicheVocabularyVisuActivity.class);
                Bundle extras = new Bundle();
                extras.putInt("position",Integer.parseInt(id.getText().toString()));
                intent.putExtras(extras);
                context.startActivity(intent);
                Toast.makeText(context, "you have clicked Row " + id.getText().toString(), Toast.LENGTH_LONG).show();
            }
        }
    }

    // This method adds(duplicates) a Object (item ) to our Data set as well as Recycler View.
    private void addItem(int position, PartOfString infoData) {
        data.add(position, infoData);
        notifyItemInserted(position);
        notifyItemRangeChanged(position, data.size());
    }

    public void plusUn(int position){
        if (position != data.size()-1){
            Collections.swap(data,position,position+1);
            notifyItemMoved(position,position+1);
            notifyItemRangeChanged(position, data.size());
        }
    }

    public void moinsUn(int position){
        if (position != 0){
            Collections.swap(data,position,position-1);
            notifyItemMoved(position,position-1);
            notifyItemRangeChanged(position-1, data.size());
        }
    }

    public void deleteItem(int position){
        data.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, data.size());
    }

    public void resetList() {
        int size = this.data.size();
        this.data.clear();
        notifyItemRangeRemoved(0, size);
    }

    public void ajoutPartieManquante(String stringToTranslate,TextView stringPronunciation){
        Integer indMotSuiv =null;
        String partieManquante = "";
        if (data == null || data.size() == 0 ){
            Vocabulary voc = new Vocabulary();
            voc.setId(0);
            voc.setWord(stringToTranslate);
            voc.setPronunciation(stringToTranslate);
            PartOfString partOfString = new PartOfString();
            partOfString.setPositionInTheString(0);
            partOfString.setWordAssociated(voc);
            partOfString.setWrittenInKanji(true);
            partOfString.setLongueur(voc.getWord().length());
            partOfString.setPartieInconnu(true);
            data.add(partOfString);
        }else {
            if (data.get(0).getPositionInTheString() >0 ){
                Vocabulary voc = new Vocabulary();
                voc.setId(0);
                voc.setWord(stringToTranslate.substring(0,data.get(0).getPositionInTheString()));
                voc.setPronunciation(stringToTranslate.substring(0,data.get(0).getPositionInTheString()));
                PartOfString partOfString = new PartOfString();
                partOfString.setPositionInTheString(0);
                partOfString.setWordAssociated(voc);
                partOfString.setWrittenInKanji(true);
                partOfString.setLongueur(voc.getWord().length());
                partOfString.setPartieInconnu(true);
                data.add(0,partOfString);
            }

            if (data.size() > 0 ){
                Integer indiceFinDernierMot = data.get(data.size()-1).getPositionInTheString()+ data.get(data.size()-1).getLongueur();
                if (indiceFinDernierMot < stringToTranslate.length()){
                    Vocabulary voc = new Vocabulary();
                    voc.setId(0);
                    voc.setWord(stringToTranslate.substring(indiceFinDernierMot));
                    voc.setPronunciation(stringToTranslate.substring(indiceFinDernierMot));
                    PartOfString partOfString = new PartOfString();
                    partOfString.setPositionInTheString(indiceFinDernierMot);
                    partOfString.setWordAssociated(voc);
                    partOfString.setWrittenInKanji(true);
                    partOfString.setLongueur(voc.getWord().length());
                    partOfString.setPartieInconnu(true);
                    data.add(data.size(),partOfString);
                }
            }

            List<PartOfString> wordToInsert = new ArrayList<>();
            for(int i=0; i<data.size()-2;i++){
                if ((data.get(i).getPositionInTheString() + data.get(i).getLongueur()) < data.get(i+1).getPositionInTheString()){
                    indMotSuiv =  data.get(i).getPositionInTheString() + data.get(i).getLongueur();
                    partieManquante = stringToTranslate.substring(indMotSuiv,data.get(i+1).getPositionInTheString());
                    Vocabulary voc = new Vocabulary();
                    voc.setId(0);
                    voc.setWord(partieManquante);
                    voc.setPronunciation(partieManquante);
                    PartOfString partOfString = new PartOfString();
                    partOfString.setPositionInTheString(indMotSuiv);
                    partOfString.setWordAssociated(voc);
                    partOfString.setWrittenInKanji(true);
                    partOfString.setLongueur(voc.getWord().length());
                    partOfString.setPartieInconnu(true);
                    wordToInsert.add(partOfString);
                }
            }

            data.addAll(wordToInsert);
            Collections.sort(data, new Comparator<PartOfString>() {
                @Override
                public int compare(PartOfString word1, PartOfString word2) {
                    int testPosition = word1.getPositionInTheString()  - word2.getPositionInTheString();
                    if (testPosition < 0){
                        return -1;
                    }else if (testPosition > 0){
                        return 1;
                    }else {
                        return 0;
                    }
                }
            });

            for(PartOfString word : data){
                Integer indicePremierChar = 0;
                Integer indiceDernierChar = 0;
                for(int i = 0;i<word.getWordAssociated().getPronunciation().length();i++){
                    if(!" ".equals(word.getWordAssociated().getPronunciation().substring(i,i+1))){
                        indicePremierChar = i;
                        break;
                    }
                }
                for(int i = indicePremierChar+1;i<word.getWordAssociated().getPronunciation().length();i++){
                    if(" ".equals(word.getWordAssociated().getPronunciation().substring(i,i+1))){
                        indiceDernierChar = i;
                        break;
                    }
                    if (i == word.getWordAssociated().getPronunciation().length()-1 ){
                        indiceDernierChar = word.getWordAssociated().getPronunciation().length();
                    }
                }
                if (indicePremierChar  == indiceDernierChar){
                    indiceDernierChar = indiceDernierChar +1;
                }
                String partToAdd = word.getWordAssociated().getPronunciation().substring(indicePremierChar,indiceDernierChar);
                stringPronunciation.setText(stringPronunciation.getText().toString()+partToAdd);
            }
        }
        notifyDataSetChanged();
    }

    public List<PartOfString> getData(){
        return data;
    }
}
