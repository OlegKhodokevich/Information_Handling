package by.khodokevich.composite.service;

import java.util.List;
import java.util.Map;

public interface TextCompositeService {
    String sortParagraphByNumberSentence(String text);

    List<String> findSentenceWithBiggestWord(String text);

    String removeSentenceWithNumberWordLessSpecified(String text);

    Map<String, Integer> findSimilarWord(String text);

    Map<String, Integer> calcFrequencyVowelsAndConsonants(String text);
}
