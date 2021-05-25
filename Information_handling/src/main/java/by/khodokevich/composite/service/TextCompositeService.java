package by.khodokevich.composite.service;

import by.khodokevich.composite.exception.ProjectCompositeException;

import java.util.List;
import java.util.Map;

public interface TextCompositeService {
    String sortParagraphByNumberSentence(String text) throws ProjectCompositeException;

    List<String> findSentenceWithBiggestWord(String text) throws ProjectCompositeException;

    String removeSentenceWithNumberWordLessSpecified(String text, int specifiedWordsNumber) throws ProjectCompositeException;

    Map<String, Integer> findSimilarWord(String text) throws ProjectCompositeException;

    Map<String, Map<String, Integer>> calcFrequencyVowelsAndConsonants(String text) throws ProjectCompositeException;
}
