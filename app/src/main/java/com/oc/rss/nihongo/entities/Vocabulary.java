package com.oc.rss.nihongo.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Antoine on 22/02/2017.
 */

public class Vocabulary implements Parcelable {

    /*************************** Attributs ***************************/
    private Integer id;
    private String word;
    private String pronunciation;
    private String meaning1;
    private String meaning2;
    private String meaning3;

    /*************************** Getter/setter ***************************/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public String getMeaning1() {
        return meaning1;
    }

    public void setMeaning1(String meaning1) {
        this.meaning1 = meaning1;
    }

    public String getMeaning2() {
        return meaning2;
    }

    public void setMeaning2(String meaning2) {
        this.meaning2 = meaning2;
    }

    public String getMeaning3() {
        return meaning3;
    }

    public void setMeaning3(String meaning3) {
        this.meaning3 = meaning3;
    }


    /*************************** Constructeurs ***************************/

    public Vocabulary() {
        super();
    }

    public Vocabulary(String word, String pronunciation, String meaning1, String meaning2, String meaning3) {
        super();
        this.word = word;
        this.pronunciation = pronunciation;
        this.meaning1 = meaning1;
        this.meaning2 = meaning2;
        this.meaning3 = meaning3;
    }

    public Vocabulary(Integer id, String word, String pronunciation, String meaning1, String meaning2, String meaning3) {
        super();
        this.id = id;
        this.word = word;
        this.pronunciation = pronunciation;
        this.meaning1 = meaning1;
        this.meaning2 = meaning2;
        this.meaning3 = meaning3;
    }

    /****************************** Methodes *****************************/

    @Override
    public boolean equals(Object obj) {
        Vocabulary other = (Vocabulary) obj;
        if (word == other.word){
            return true;
        }else {
            return false;
        }
    }

    protected Vocabulary(Parcel in) {
        id = in.readByte() == 0x00 ? null : in.readInt();
        word = in.readString();
        pronunciation = in.readString();
        meaning1 = in.readString();
        meaning2 = in.readString();
        meaning3 = in.readString();
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
        dest.writeString(word);
        dest.writeString(pronunciation);
        dest.writeString(meaning1);
        dest.writeString(meaning2);
        dest.writeString(meaning3);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Vocabulary> CREATOR = new Parcelable.Creator<Vocabulary>() {
        @Override
        public Vocabulary createFromParcel(Parcel in) {
            return new Vocabulary(in);
        }

        @Override
        public Vocabulary[] newArray(int size) {
            return new Vocabulary[size];
        }
    };
}