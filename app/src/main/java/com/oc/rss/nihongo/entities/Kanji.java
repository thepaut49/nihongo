package com.oc.rss.nihongo.entities;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by Antoine on 22/02/2017.
 */

public class Kanji implements Parcelable {

    /*************************** Attributs ***************************/
    private Integer id;
    private String caractere;
    private String pronunciation;
    private String meaning;
    private Integer strokes;
    private String radicals;

    /*************************** Getter/setter ***************************/
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCaractere() {
        return caractere;
    }

    public void setCaractere(String caractere) {
        this.caractere = caractere;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public Integer getStrokes() {
        return strokes;
    }

    public void setStrokes(Integer strokes) {
        this.strokes = strokes;
    }

    public String getRadicals() {
        return radicals;
    }

    public void setRadicals(String radicals) {
        this.radicals = radicals;
    }

    /*************************** Constructeurs ***************************/

    public Kanji() {
        super();
    }

    public Kanji(Integer id, String caractere, String pronunciation, String meaning, Integer strokes, String radicals) {
        super();
        this.id = id;
        this.caractere = caractere;
        this.pronunciation = pronunciation;
        this.meaning = meaning;
        this.strokes = strokes;
        this.radicals = radicals;
    }

    public Kanji(String caractere, String pronunciation, String meaning, Integer strokes) {
        super();
        this.caractere = caractere;
        this.pronunciation = pronunciation;
        this.meaning = meaning;
        this.strokes = strokes;
    }


    public Kanji(String caractere, String pronunciation, String meaning,
                 Integer strokes, String radicals) {
        super();
        this.caractere = caractere;
        this.pronunciation = pronunciation;
        this.meaning = meaning;
        this.strokes = strokes;
        this.radicals = radicals;
    }


    /****************************** Methodes *****************************/

    @Override
    public boolean equals(Object obj) {
        Kanji other = (Kanji) obj;
        if (caractere == other.caractere){
            return true;
        }else {
            return false;
        }
    }

    protected Kanji(Parcel in) {
        id = in.readByte() == 0x00 ? null : in.readInt();
        caractere = in.readString();
        pronunciation = in.readString();
        meaning = in.readString();
        strokes = in.readByte() == 0x00 ? null : in.readInt();
        radicals = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(id);
        }
        dest.writeString(caractere);
        dest.writeString(pronunciation);
        dest.writeString(meaning);
        if (strokes == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(strokes);
        }
        dest.writeString(radicals);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Kanji> CREATOR = new Parcelable.Creator<Kanji>() {
        @Override
        public Kanji createFromParcel(Parcel in) {
            return new Kanji(in);
        }

        @Override
        public Kanji[] newArray(int size) {
            return new Kanji[size];
        }
    };
}