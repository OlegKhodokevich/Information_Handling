package by.khodokevich.composite.service.impl;

import by.khodokevich.composite.comparator.NumberSentenceForParagraphComparator;
import by.khodokevich.composite.entity.Component;
import by.khodokevich.composite.entity.ComponentType;
import by.khodokevich.composite.entity.TextComposite;
import by.khodokevich.composite.exception.ProjectCompositeException;
import by.khodokevich.composite.parser.AbstractTextParser;
import by.khodokevich.composite.parser.ParserFactory;
import by.khodokevich.composite.service.TextCompositeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class TextCompositeServiceImpl implements TextCompositeService {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String VOWELS_REG_EXP = "[aeiouyаеёиоуыэюя]";
    private static final String CONSONANTS_REG_EXP = "[a-zа-я&&[^aeiouyаеёиоуыэюя]]";


    @Override
    public String sortParagraphByNumberSentence(String text) throws ProjectCompositeException {
        LOGGER.info("Start sortParagraphByNumberSentence(String text). Text = " + text);
        Component textComponent = new TextComposite(ComponentType.TEXT);
        AbstractTextParser parser = ParserFactory.createChainOfParser();
        parser.parse(text, textComponent);

        List<Component> paragraphComponents = textComponent.getChildComponentAsList();
        List<Component> sortedParagraphComponents = paragraphComponents.stream().sorted(new NumberSentenceForParagraphComparator()).toList();

        Component newTextComponent = new TextComposite(ComponentType.TEXT);
        newTextComponent.addAll(sortedParagraphComponents);
        String sortedText = newTextComponent.toString();
        LOGGER.info("End sortParagraphByNumberSentence(String text). Sorted text = " + sortedText);
        return sortedText;
    }

    @Override
    public List<String> findSentenceWithBiggestWord(String text) throws ProjectCompositeException {
        LOGGER.info("Start findSentenceWithBiggestWord(String text). Text = " + text);
        List<String> sentences = new ArrayList<>();
        Component textComponent = new TextComposite(ComponentType.TEXT);
        AbstractTextParser parser = ParserFactory.createChainOfParser();
        parser.parse(text, textComponent);

        List<String> wordsList = new ArrayList<>();
        getAllWordsFromTextComposite(textComponent, wordsList);
        if (!wordsList.isEmpty()) {
            wordsList.sort(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.length() - o2.length();
                }
            }.reversed());

            int maxSize = wordsList.get(0).length();
            LOGGER.info("The biggest word = " + wordsList.get(0) + ", max word's length in text = " + maxSize);
            boolean hasWordGivenLength = false;
            getAllSentenceWithWordsGivenLength(textComponent, sentences, maxSize, hasWordGivenLength);
        }
        LOGGER.info("End findSentenceWithBiggestWord(String text). Sentences = " + sentences);
        return sentences;
    }

    private void getAllWordsFromTextComposite(Component component, List<String> wordsList) {
        List<Component> textComponents = component.getChildComponentAsList();
        for (int i = 0; i < textComponents.size(); i++) {
            Component element = textComponents.get(i);

            if (element.getType() == ComponentType.WORD) {
                wordsList.add(element.toString());
            } else if (element.getType() != ComponentType.LETTER && element.getType() != ComponentType.PUNCTUATION && element.getType() != ComponentType.NUMBER) {
                getAllWordsFromTextComposite(element, wordsList);
            }
        }
    }

    private boolean getAllSentenceWithWordsGivenLength(Component component, List<String> sentenceList, int wordsLength, boolean hasWordGivenLength) {
        List<Component> textComponents = component.getChildComponentAsList();
        int i = 0;
        while (!hasWordGivenLength && i < textComponents.size()) {

            Component element = textComponents.get(i);
            if (element.getType() == ComponentType.WORD && element.getChildComponentAsList().size() == wordsLength) {
                hasWordGivenLength = true;

            } else if (element.getType() != ComponentType.LETTER && element.getType() != ComponentType.PUNCTUATION && element.getType() != ComponentType.NUMBER) {
                hasWordGivenLength = getAllSentenceWithWordsGivenLength(element, sentenceList, wordsLength, hasWordGivenLength);
            }
            i++;
        }
        if (component.getType() == ComponentType.SENTENCE && hasWordGivenLength) {
            sentenceList.add(component.toString());
            hasWordGivenLength = false;
        }
        return hasWordGivenLength;
    }

    @Override
    public String removeSentenceWithNumberWordLessSpecified(String text, int specifiedWordsNumber) throws ProjectCompositeException {
        LOGGER.info("Start removeSentenceWithNumberWordLessSpecified(String text, int specifiedWordsNumber). Text = " + text + ", number of min number of word = " + specifiedWordsNumber);
        AbstractTextParser parser = ParserFactory.createChainOfParser();
        Component textComponent = new TextComposite(ComponentType.TEXT);
        parser.parse(text, textComponent);

        int wordsNumber = 0;
        removeSentenceWithNumberWordLessSpecifiedFromComponent(textComponent, specifiedWordsNumber, wordsNumber);

        LOGGER.info("End removeSentenceWithNumberWordLessSpecified(String text, int specifiedWordsNumber). Text = " + textComponent);
        return textComponent.toString();
    }

    private int removeSentenceWithNumberWordLessSpecifiedFromComponent(Component component, int specifiedWordsNumber, int wordsNumber) {
        List<Component> textComponents = component.getChildComponentAsList();

        for (int i = 0; i < textComponents.size(); i++) {
            Component element = textComponents.get(i);

            if (element.getType() == ComponentType.WORD) {
                wordsNumber++;

            } else if (element.getType() != ComponentType.LETTER && element.getType() != ComponentType.PUNCTUATION && element.getType() != ComponentType.NUMBER) {
                wordsNumber = removeSentenceWithNumberWordLessSpecifiedFromComponent(element, specifiedWordsNumber, wordsNumber);
            }

            if (element.getType() == ComponentType.SENTENCE) {
                if (wordsNumber < specifiedWordsNumber) {
                    LOGGER.info("Remove sentence = " + element + ", number of words = " + wordsNumber);
                    component.remove(element);
                }
                wordsNumber = 0;
            }
        }
        return wordsNumber;
    }

    @Override
    public Map<String, Integer> findSimilarWord(String text) throws ProjectCompositeException {
        LOGGER.info("Start findSimilarWord(String text). Text = " + text);
        AbstractTextParser parser = ParserFactory.createChainOfParser();
        Component textComponent = new TextComposite(ComponentType.TEXT);
        parser.parse(text, textComponent);

        Map<String, Integer> wordsMap = new HashMap<>();

        fillMapByWords(textComponent, wordsMap);
        Iterator<Map.Entry<String, Integer>> iterator = wordsMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> couple = iterator.next();
            if (couple.getValue() == 1) {
                iterator.remove();
            }
        }
        LOGGER.info("End findSimilarWord(String text). Word's map = " + wordsMap);
        return wordsMap;
    }

    private void fillMapByWords(Component component, Map<String, Integer> wordsMap) {
        List<Component> textComponents = component.getChildComponentAsList();

        for (int i = 0; i < textComponents.size(); i++) {
            Component element = textComponents.get(i);

            if (element.getType() == ComponentType.WORD) {
                String word = element.toString().toLowerCase();
                int number = 0;
                if (wordsMap.containsKey(word)) {
                    number = wordsMap.get(word);
                }
                wordsMap.put(word, ++number);

            } else if (element.getType() != ComponentType.LETTER && element.getType() != ComponentType.PUNCTUATION && element.getType() != ComponentType.NUMBER) {
                fillMapByWords(element, wordsMap);
            }
        }
    }

    @Override
    public Map<String, Map<String, Integer>> calcFrequencyVowelsAndConsonants(String text) throws ProjectCompositeException {
        LOGGER.info("Start calcFrequencyVowelsAndConsonants(String text). Text = " + text);
        AbstractTextParser parser = ParserFactory.createChainOfParser();
        Component textComponent = new TextComposite(ComponentType.TEXT);
        parser.parse(text, textComponent);

        Map<String, Map<String, Integer>> sentences = new HashMap<>();
        fillSentenceMapWithConsonantsAndVowels(textComponent, sentences, new HashMap<>());
        LOGGER.info("End calcFrequencyVowelsAndConsonants(String text). Result = " + sentences);
        return sentences;
    }

    private void fillSentenceMapWithConsonantsAndVowels(Component component, Map<String, Map<String, Integer>> sentences, Map<String, Integer> consonantsAndVowelsMap) {
        List<Component> textComponents = component.getChildComponentAsList();

        for (int i = 0; i < textComponents.size(); i++) {
            Component element = textComponents.get(i);
            if (element.getType() == ComponentType.SENTENCE) {
                consonantsAndVowelsMap = new HashMap<>();
                consonantsAndVowelsMap.put("vowel", 0);
                consonantsAndVowelsMap.put("consonant", 0);
            }
            if (element.getType() == ComponentType.LETTER) {
                String letter = element.toString().toLowerCase();
                if (letter.matches(VOWELS_REG_EXP)) {
                    int numberVowels = consonantsAndVowelsMap.get("vowel");
                    consonantsAndVowelsMap.put("vowel", ++numberVowels);
                }
                if (letter.matches(CONSONANTS_REG_EXP)) {
                    int numberConsonants = consonantsAndVowelsMap.get("consonant");
                    consonantsAndVowelsMap.put("consonant", ++numberConsonants);
                }
            } else if (element.getType() != ComponentType.LETTER && element.getType() != ComponentType.PUNCTUATION && element.getType() != ComponentType.NUMBER) {
                fillSentenceMapWithConsonantsAndVowels(element, sentences, consonantsAndVowelsMap);
            }

            if (element.getType() == ComponentType.SENTENCE) {
                LOGGER.info("Add sentence = " + element + ", sentence has next letter = " + consonantsAndVowelsMap);
                sentences.put(element.toString(), consonantsAndVowelsMap);
            }
        }
    }
}
