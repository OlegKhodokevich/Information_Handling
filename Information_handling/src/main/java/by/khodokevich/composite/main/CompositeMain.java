package by.khodokevich.composite.main;

import by.khodokevich.composite.entity.Component;
import by.khodokevich.composite.entity.ComponentType;
import by.khodokevich.composite.entity.TextComposite;
import by.khodokevich.composite.exception.ProjectCompositeException;
import by.khodokevich.composite.parser.AbstractTextParser;
import by.khodokevich.composite.parser.ParserFactory;
import by.khodokevich.composite.reader.DataTextReader;
import by.khodokevich.composite.service.TextCompositeService;
import by.khodokevich.composite.service.impl.TextCompositeServiceImpl;

import java.util.Map;
import java.util.Set;

public class CompositeMain {
    private static final String FILE_NAME_SOURCE = "data/information.txt";

    public static void main(String[] args) throws ProjectCompositeException {
        DataTextReader dataTextReader = new DataTextReader();
        String text = dataTextReader.readAllText(FILE_NAME_SOURCE);
        System.out.println(text);

        AbstractTextParser factoryParser = ParserFactory.createChainOfParser();
        Component textComposite = new TextComposite(ComponentType.TEXT);
        factoryParser.parse(text, textComposite);
        System.out.println(textComposite);
        System.out.println();
        TextCompositeService service = new TextCompositeServiceImpl();
        String sortedText = service.sortParagraphByNumberSentence(text);
        System.out.println(sortedText);

        service.findSentenceWithBiggestWord(text);

        System.out.println("111111111111");   //TODO

        String newText = service.removeSentenceWithNumberWordLessSpecified(text, 5);
        System.out.println(newText);

        Map<String, Integer> wordsMap = service.findSimilarWord(text);
        Set<Map.Entry<String, Integer>> wordsEntrySet = wordsMap.entrySet();
        for (Map.Entry<String, Integer> words : wordsEntrySet) {
            System.out.println(words);
        }

        Map<String, Map<String, Integer>> sentences = service.calcFrequencyVowelsAndConsonants(text);

        Set<Map.Entry<String, Map<String, Integer>>> sentenceSet = sentences.entrySet();
        for (Map.Entry<String, Map<String, Integer>> element : sentenceSet) {
            System.out.println(element);

        }


        String ff = "After parse(String text, Component component). Text = It has survived - not only (five) centuries, but also the leap into electronic\n" +
                "typesetting, remaining essentially unchanged. It was popularised in the “Динамо” (Рига)\n" +
                "with the release of Letraset sheets.toString() containing Lorem Ipsum passages, and\n" +
                "more recently with desktop publishing software like Aldus PageMaker Faclon9 including\n" +
                "versions of Lorem Ipsum!";

    }
}
