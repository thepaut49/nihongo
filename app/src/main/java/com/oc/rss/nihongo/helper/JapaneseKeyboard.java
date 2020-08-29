package com.oc.rss.nihongo.helper;

import android.util.Log;

/**
 * Created by Antoine on 18/03/2017.
 */

public class JapaneseKeyboard {

    public static void ready() {

    }


    public static String translateString(String element) {
        String stringToTranslate = element;
        String newString = "";
        for (int i = 0; i < stringToTranslate.length(); i++) {
            switch (stringToTranslate.charAt(i)) {
                case 'a':
                    newString += "あ";
                    break;
                case 'i':
                    newString += "い";
                    break;
                case 'u':
                    newString += "う";
                    break;
                case 'e':
                    newString += "え";
                    break;
                case 'o':
                    newString += "お";
                    break;
                case 'A':
                    newString += "ア";
                    break;
                case 'I':
                    newString += "イ";
                    break;
                case 'U':
                    newString += "ウ";
                    break;
                case 'E':
                    newString += "エ";
                    break;
                case 'O':
                    newString += "オ";
                    break;
                default:
                    newString += stringToTranslate.substring(i, i + 1);
            }
        }

        Log.d("chaine de 1 char", "10%");

        if (newString.length() > 1){
            String newString2Letter = newString.substring(0,1);
            for (int i = 0; i < newString.length() - 1; i++) {
                String string = newString.substring(i,i+2);
                if (string.equals( "kあ") ){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "か";
                }else if (string.equals( "kい")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "き";
                }
                else if (string.equals( "kう")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "く";
                }
                else if (string.equals( "kえ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "け";
                }
                else if (string.equals( "kお")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "こ";
                }
                else if (string.equals( "Kア")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "カ";
                }
                else if (string.equals( "Kイ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "キ";
                }
                else if (string.equals( "Kウ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ク";
                }
                else if (string.equals( "Kエ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ケ";
                }
                else if (string.equals( "Kオ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "コ";
                }

                // Ta te to
                else if (string.equals( "tあ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "た";
                }
                else if (string.equals( "tえ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "て";
                }
                else if (string.equals( "tお")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "と";
                }
                else if (string.equals( "Tア")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "タ";
                }
                else if (string.equals( "Tエ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "テ";
                }
                else if (string.equals( "Tオ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ト";
                }

                // sa su se so
                else if (string.equals( "sあ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "さ";
                }
                else if (string.equals( "sう")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "す";
                }
                else if (string.equals( "sえ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "せ";
                }
                else if (string.equals( "sお")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "そ";
                }
                else if (string.equals( "Sア")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "サ";
                }
                else if (string.equals( "Sウ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ス";
                }
                else if (string.equals( "Sエ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "セ";
                }
                else if (string.equals( "Sオ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ソ";
                }

                // !!!!!!!!!!! na ni nu ne no!!!!!!!!!!!
                else if (string.equals( "nあ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "な";
                }
                else if (string.equals( "nい")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "に";
                }
                else if (string.equals( "nう")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ぬ";
                }
                else if (string.equals( "nえ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ね";
                }
                else if (string.equals( "nお")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "の";
                }
                else if (string.equals( "Nア")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ナ";
                }
                else if (string.equals( "Nイ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ニ";
                }
                else if (string.equals( "Nウ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ヌ";
                }
                else if (string.equals( "Nエ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ネ";
                }
                else if (string.equals( "Nオ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ノ";
                }

                // !!!!!!!!!!! ha hi fu he ho!!!!!!!!!!!
                else if (string.equals( "hあ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "は";
                }
                else if (string.equals( "hい")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ひ";
                }
                else if (string.equals( "fう")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ふ";
                }
                else if (string.equals( "hえ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "へ";
                }
                else if (string.equals( "hお")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ほ";
                }
                else if (string.equals( "Hア")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ハ";
                }
                else if (string.equals( "Hイ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ヒ";
                }
                else if (string.equals( "Fウ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "フ";
                }
                else if (string.equals( "Hエ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ヘ";
                }
                else if (string.equals( "Hオ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ホ";
                }

                // !!!!!!!!!!! ma mi mu me mo!!!!!!!!!!!
                else if (string.equals( "mあ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ま";
                }
                else if (string.equals( "mい")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "み";
                }
                else if (string.equals( "mう")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "む";
                }
                else if (string.equals( "mえ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "め";
                }
                else if (string.equals( "mお")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "も";
                }
                else if (string.equals( "Mア")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "マ";
                }
                else if (string.equals( "Mイ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ミ";
                }
                else if (string.equals( "Mウ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ム";
                }
                else if (string.equals( "Mエ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "メ";
                }
                else if (string.equals( "Mオ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "モ";
                }

                // !!!!!!!!!!! ya yu yo!!!!!!!!!!!
                else if (string.equals( "yあ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "や";
                }

                else if (string.equals( "yう")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ゆ";
                }
                else if (string.equals( "yお")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "よ";
                }
                else if (string.equals( "Yア")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ヤ";
                }
                else if (string.equals( "Yウ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ユ";
                }
                else if (string.equals( "Yオ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ヨ";
                }

                // !!!!!!!!!!! ra ri ru re ro!!!!!!!!!!!
                else if (string.equals( "rあ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ら";
                }
                else if (string.equals( "rい")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "り";
                }
                else if (string.equals( "rう")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "る";
                }
                else if (string.equals( "rえ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "れ";
                }
                else if (string.equals( "rお")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ろ";
                }
                else if (string.equals( "Rア")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ラ";
                }
                else if (string.equals( "Rイ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "リ";
                }
                else if (string.equals( "Rウ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ル";
                }
                else if (string.equals( "Rエ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "レ";
                }
                else if (string.equals( "Rオ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ロ";
                }

                // !!!!!!!!!!! wa wo!!!!!!!!!!!
                else if (string.equals( "wあ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "わ";
                }
                else if (string.equals( "wお")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "を";
                }
                else if (string.equals( "Wア")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ワ";
                }
                else if (string.equals( "Wオ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ヲ";
                }

                // !!!!!!!!!!! ga gi gu ge go!!!!!!!!!!!
                else if (string.equals( "gあ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "が";
                }
                else if (string.equals( "gい")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ぎ";
                }
                else if (string.equals( "gう")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ぐ";
                }
                else if (string.equals( "gえ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "げ";
                }
                else if (string.equals( "gお")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ご";
                }
                else if (string.equals( "Gア")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ガ";
                }
                else if (string.equals( "Gイ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ギ";
                }
                else if (string.equals( "Gウ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "グ";
                }
                else if (string.equals( "Gエ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ゲ";
                }
                else if (string.equals( "Gオ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ゴ";
                }

                // !!!!!!!!!!! za ji zu ze zo!!!!!!!!!!!
                else if (string.equals( "zあ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ざ";
                }
                else if (string.equals( "jい")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "じ";
                }
                else if (string.equals( "zう")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ず";
                }
                else if (string.equals( "zえ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ぜ";
                }
                else if (string.equals( "zお")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ぞ";
                }
                else if (string.equals( "Zア")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ザ";
                }
                else if (string.equals( "Jイ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ジ";
                }
                else if (string.equals( "Zウ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ズ";
                }
                else if (string.equals( "Zエ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ゼ";
                }
                else if (string.equals( "Zオ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ゾ";
                }

                // !!!!!!!!!!! da ji zu de do!!!!!!!!!!!
                else if (string.equals( "dあ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "だ";
                }
                else if (string.equals( "じ=")) {
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ぢ";
                } else if (string.equals( "ず=")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "づ";
                }
                else if (string.equals( "dえ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "で";
                }
                else if (string.equals( "dお")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ど";
                }
                else if (string.equals( "Dア")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ダ";
                }
                else if (string.equals( "ジ=")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ヂ";
                }
                else if (string.equals( "ズ=")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ヅ";
                }
                else if (string.equals( "Dエ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "デ";
                }
                else if (string.equals( "Dオ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ド";
                }

                // !!!!!!!!!!! ba bi bu be bo!!!!!!!!!!!
                else if (string.equals( "bあ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ば";
                }
                else if (string.equals( "bい")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "び";
                }
                else if (string.equals( "bう")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ぶ";
                }
                else if (string.equals( "bえ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "べ";
                }
                else if (string.equals( "bお")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ぼ";
                }
                else if (string.equals( "Bア")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "バ";
                }
                else if (string.equals( "Bイ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ビ";
                }
                else if (string.equals( "Bウ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ブ";
                }
                else if (string.equals( "Bエ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ベ";
                }
                else if (string.equals( "Bオ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ボ";
                }

                // !!!!!!!!!!! pa pi pu pe po!!!!!!!!!!!
                else if (string.equals( "pあ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ぱ";
                }
                else if (string.equals( "pい")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ぴ";
                }
                else if (string.equals( "pう")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ぷ";
                }
                else if (string.equals( "pえ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ぺ";
                }
                else if (string.equals( "pお")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ぽ";
                }
                else if (string.equals( "Pア")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "パ";
                }
                else if (string.equals( "Pイ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ピ";
                }
                else if (string.equals( "Pウ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "プ";
                }
                else if (string.equals( "Pエ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ペ";
                }
                else if (string.equals( "Pオ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ポ";
                }

                // !!!!!!!!! ja ju jo!!!!!!!!
                else if (string.equals( "jあ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "じゃ";
                }
                else if (string.equals( "jう")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "じゅ";
                }
                else if (string.equals( "jお")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "じょ";
                }
                else if (string.equals( "Jア")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ジャ";
                }
                else if (string.equals( "Jウ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ジュ";
                }
                else if (string.equals( "Jオ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ジョ";
                }

                // !!!!!!!!! va vi vu ve vo !!!!!!!!
                else if (string.equals( "Vア")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ヴァ";
                }
                else if (string.equals( "Vイ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ヴィ";
                }
                else if (string.equals( "Vウ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ヴ";
                }
                else if (string.equals( "Vエ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ヴェ";
                }
                else if (string.equals( "Vオ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ヴォ";
                }

                // !!!!!!!!!! ti du !!!!!
                else if (string.equals( "Tイ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ティ";
                }
                else if (string.equals( "Dウ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ドゥ";
                }

                // !!!!!!!!! fa fi fe fo !!!!!!!!
                else if (string.equals( "Fア")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ファ";
                }
                else if (string.equals( "Fイ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "フィ";
                }
                else if (string.equals( "Fエ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "フェ";
                }
                else if (string.equals( "Fオ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "フォ";
                }

                // !!!!!! she je !!!!!!!

                else if (string.equals( "Jエ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ジェ";
                }
                else{
                    newString2Letter += string.substring(1,2);
                }
            } // fin for

            Log.d("chaine de 2 char", "50%");
            newString = newString2Letter;

            newString2Letter = newString.substring(0,1);
            for (int i = 0; i < newString.length() - 1; i++) {

                String string = newString.substring(i,i+2);
                // !!!!!!!!! hya hyu hyo!!!!!!!!
                if (string.equals( "hや")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ひゃ";
                }
                else if (string.equals( "hゆ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ひゅ";
                }
                else if (string.equals( "hよ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ひょ";
                }
                else if (string.equals( "Hヤ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ヒャ";
                }
                else if (string.equals( "Hユ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ヒュ";
                }
                else if (string.equals( "Hヨ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ヒョ";
                }

                // !!!!!!!!! kya kyu kyo!!!!!!!!
                else if (string.equals( "kや")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "きゃ";
                }
                else if (string.equals( "kゆ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "きゅ";
                }
                else if (string.equals( "kよ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "きょ";
                }
                else if (string.equals( "Kヤ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "キャ";
                }
                else if (string.equals( "Kユ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "キュ";
                }
                else if (string.equals( "Kヨ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "キョ";
                }

                // !!!!!!!!! gya gyu gyo!!!!!!!!
                else if (string.equals( "gや")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ぎゃ";
                }
                else if (string.equals( "gゆ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ぎゅ";
                }
                else if (string.equals( "gよ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ぎょ";
                }
                else if (string.equals( "Gヤ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ギャ";
                }
                else if (string.equals( "Gユ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ギュ";
                }
                else if (string.equals( "Gヨ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ギョ";
                }

                // !!!!!!!!! nya nyu nyo!!!!!!!!
                else if (string.equals( "nや")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "にゃ";
                }
                else if (string.equals( "nゆ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "にゅ";
                }
                else if (string.equals( "nよ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "にょ";
                }
                else if (string.equals( "Nヤ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ニャ";
                }
                else if (string.equals( "Nユ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ニュ";
                }
                else if (string.equals( "Nヨ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ニョ";
                }

                // !!!!!!!!! bya byu byo!!!!!!!!
                else if (string.equals( "bや")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "びゃ";
                }
                else if (string.equals( "bゆ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                        newString2Letter += "びゅ";
                    }
                else if (string.equals( "bよ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "びょ";
                }
                else if (string.equals( "Bヤ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ビャ";
                }
                else if (string.equals( "Bユ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ビュ";
                }
                else if (string.equals( "Bヨ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ビョ";
                }

                // !!!!!!!!! pya pyu pyo!!!!!!!!
                else if (string.equals( "pや")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ぴゃ";
                }
                else if (string.equals( "pゆ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ぴゅ";
                }
                else if (string.equals( "pよ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ぴょ";
                }
                else if (string.equals( "Pヤ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ピャ";
                }
                else if (string.equals( "Pユ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ピュ";
                }
                else if (string.equals( "Pヨ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ピョ";
                }

                // !!!!!!!!! mya myu myo!!!!!!!!
                else if (string.equals( "mや")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "みゃ";
                }
                else if (string.equals( "mゆ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "みゅ";
                }
                else if (string.equals( "mよ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "みょ";
                }
                else if (string.equals( "Mヤ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ミャ";
                }
                else if (string.equals( "Mユ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ミュ";
                }
                else if (string.equals( "Mヨ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ミョ";
                }

                // !!!!!!!!! rya ryu ryo!!!!!!!!
                else if (string.equals( "rや")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "りゃ";
                }
                else if (string.equals( "rゆ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "りゅ";
                }
                else if (string.equals( "rよ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "りょ";
                }
                else if (string.equals( "Rヤ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "リャ";
                }
                else if (string.equals( "Rユ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "リュ";
                }
                else if (string.equals( "Rヨ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "リョ";
                }

                // petit tsu
                else if (string.equals( "つ=")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "っ";
                }

                else if (string.equals( "ツ=")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ッ";
                }

                // !!!!!!!!! sha shu sho!!!!!!!!
                else if (string.equals( "sは")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "しゃ";
                }

                else if (string.equals( "sひ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "し";
                }
                else if (string.equals( "sほ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "しょ";
                }
                else if (string.equals( "Sハ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "シャ";
                }

                else if (string.equals( "Sヒ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "シ";
                }

                else if (string.equals( "Sヘ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "シェ";
                }
                else if (string.equals( "Sホ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ショ";
                }

                // !!!!!!!!! cha chu cho!!!!!!!!
                else if (string.equals( "cは")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ちゃ";
                }
                else if (string.equals( "cひ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ち";
                }
                else if (string.equals( "cほ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ちょ";
                }
                else if (string.equals( "Cハ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "チャ";
                }
                else if (string.equals( "Cヒ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "チ";
                }

                else if (string.equals( "Cホ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "チョ";
                }

                else if (string.equals( "Cヘ")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "チエ";
                }

                // !!!!!!!!!! tsu
                else if (string.equals( "tす")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "つ";
                }

                else if (string.equals( "Tス")){
                    int indexCaractereAEnlever = newString2Letter.length() - 1;
                    newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                    newString2Letter += "ツ";
                }

                else{
                    newString2Letter += string.substring(1,2);
                }

            }

            Log.d("chaine de 3 char", "90%");

            newString = newString2Letter;
            if (newString.length() > 2) {
                newString2Letter = newString.substring(0, 2);
                for (int i = 0; i < newString.length() - 2; i++) {
                    String string = newString.substring(i, i + 3);
                    if (string.equals("CHウ")) {
                        int indexCaractereAEnlever = newString2Letter.length() - 2;
                        newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                        newString2Letter += "チュ";
                    } else if (string.equals("chう")) {
                        int indexCaractereAEnlever = newString2Letter.length() - 2;
                        newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                        newString2Letter += "ちゅ";
                    } else if (string.equals("SHウ")) {
                        int indexCaractereAEnlever = newString2Letter.length() - 2;
                        newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                        newString2Letter += "シュ";
                    } else if (string.equals("shう")) {
                        int indexCaractereAEnlever = newString2Letter.length() - 2;
                        newString2Letter = newString2Letter.substring(0, indexCaractereAEnlever);
                        newString2Letter += "しゅ";
                    } else {
                        newString2Letter += string.substring(2, 3);
                    }
                }
                newString = newString2Letter;
            }
        } //fin if
        Log.d("fin de translation", "100%");
        return newString;
    }
}



