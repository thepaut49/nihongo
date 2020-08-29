package com.oc.rss.nihongo.interfaces;

import com.oc.rss.nihongo.entities.Kanji;

import java.util.List;

public interface HNQueryCallback {
    void onArticlesReceived(List<Kanji> var1);
}