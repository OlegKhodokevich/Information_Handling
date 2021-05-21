package by.khodokevich.composite.main;

import by.khodokevich.composite.entity.Component;
import by.khodokevich.composite.entity.ComponentType;
import by.khodokevich.composite.entity.TextComposite;
import by.khodokevich.composite.exception.ProjectCompositeException;
import by.khodokevich.composite.parser.AbstractTextParser;
import by.khodokevich.composite.parser.FactoryParser;
import by.khodokevich.composite.reader.DataTextReader;

public class CompositeMain {
    private static final String FILE_NAME_SOURCE = "data/information.txt";

    public static void main(String[] args) throws ProjectCompositeException {
        DataTextReader dataTextReader = new DataTextReader();
        String text = dataTextReader.readAllText(FILE_NAME_SOURCE);
        System.out.println(text);

        AbstractTextParser factoryParser = FactoryParser.createChainOfParser();
        Component textComposite = new TextComposite(ComponentType.TEXT);
        factoryParser.parse(text, textComposite);
        System.out.println("    " + textComposite.toString());

        String ff = "After parse(String text, Component component). Text = It has survived - not only (five) centuries, but also the leap into electronic\n" +
                "typesetting, remaining essentially unchanged. It was popularised in the “Динамо” (Рига)\n" +
                "with the release of Letraset sheets.toString() containing Lorem Ipsum passages, and\n" +
                "more recently with desktop publishing software like Aldus PageMaker Faclon9 including\n" +
                "versions of Lorem Ipsum!";

    }
}
