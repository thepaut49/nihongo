package com.oc.rss.nihongo.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Antoine on 25/11/2017.
 */

public class PartOfString implements Parcelable {

    //attributes
    private Integer positionInTheString;
    private Vocabulary wordAssociated;
    private Boolean writtenInKanji;
    private Integer longueur;
    private Boolean partieInconnu;


    //getter and setter
    public Vocabulary getWordAssociated() {
        return wordAssociated;
    }

    public void setWordAssociated(Vocabulary wordAssociated) {
        this.wordAssociated = wordAssociated;
    }

    public Integer getPositionInTheString() {
        return positionInTheString;
    }

    public void setPositionInTheString(Integer positionInTheString) {
        this.positionInTheString = positionInTheString;
    }

    public Boolean getWrittenInKanji() {
        return writtenInKanji;
    }

    public void setWrittenInKanji(Boolean writtenInKanji) {
        this.writtenInKanji = writtenInKanji;
    }

    public Integer getLongueur() {
        return longueur;
    }

    public void setLongueur(Integer longueur) {
        this.longueur = longueur;
    }

    public Boolean getPartieInconnu() {
        return partieInconnu;
    }

    public void setPartieInconnu(Boolean partieInconnu) {
        this.partieInconnu = partieInconnu;
    }

    //Constructor
    public PartOfString() {
    }

    public PartOfString(Integer positionInTheString, Vocabulary wordAssociated) {
        this.positionInTheString = positionInTheString;
        this.wordAssociated = wordAssociated;
    }

    public PartOfString(Integer positionInTheString, Vocabulary wordAssociated, Boolean writtenInKanji, Integer longueur) {
        this.positionInTheString = positionInTheString;
        this.wordAssociated = wordAssociated;
        this.writtenInKanji = writtenInKanji;
        this.longueur = longueur;
    }

    public PartOfString(Integer positionInTheString, Vocabulary wordAssociated, Boolean writtenInKanji, Integer longueur, Boolean partieInconnu) {
        this.positionInTheString = positionInTheString;
        this.wordAssociated = wordAssociated;
        this.writtenInKanji = writtenInKanji;
        this.longueur = longueur;
        this.partieInconnu = partieInconnu;
    }

    //other methods

    @Override
    public boolean equals(Object obj) {
        PartOfString otherPart = (PartOfString) obj;
        if (this.positionInTheString.equals(otherPart.getPositionInTheString()) && this.wordAssociated.equals(otherPart.getWordAssociated())){
            return true;
        }else {
            return false;
        }
    }

    protected PartOfString(Parcel in) {
        positionInTheString = in.readByte() == 0x00 ? null : in.readInt();
        wordAssociated = (Vocabulary) in.readValue(Vocabulary.class.getClassLoader());
        byte writtenInKanjiVal = in.readByte();
        writtenInKanji = writtenInKanjiVal == 0x02 ? null : writtenInKanjiVal != 0x00;
        longueur = in.readByte() == 0x00 ? null : in.readInt();
        byte partieInconnuVal = in.readByte();
        partieInconnu = partieInconnuVal == 0x02 ? null : partieInconnuVal != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (positionInTheString == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(positionInTheString);
        }
        dest.writeValue(wordAssociated);
        if (writtenInKanji == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (writtenInKanji ? 0x01 : 0x00));
        }
        if (longueur == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(longueur);
        }
        if (partieInconnu == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (partieInconnu ? 0x01 : 0x00));
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<PartOfString> CREATOR = new Parcelable.Creator<PartOfString>() {
        @Override
        public PartOfString createFromParcel(Parcel in) {
            return new PartOfString(in);
        }

        @Override
        public PartOfString[] newArray(int size) {
            return new PartOfString[size];
        }
    };
}