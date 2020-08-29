package com.oc.rss.nihongo.interfaces;

import com.oc.rss.nihongo.entities.Vocabulary;

import java.util.List;

public interface HNQueryCallbackVocabulary {
    void onVocabularyReceived(List<Vocabulary> var1);

}