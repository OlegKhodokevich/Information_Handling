package by.khodokevich.composite.parser;

import by.khodokevich.composite.entity.Component;
import by.khodokevich.composite.entity.ComponentType;
import by.khodokevich.composite.entity.TextComposite;
import by.khodokevich.composite.exception.ProjectCompositeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends AbstractTextParser {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String SENTENCE_REG_EXP = "([A-ZА-Я](.*?\\s?)+.*?[\\.!?\\.{3}](\\s+[A-ZА-Я]))|([A-ZА-Я](.*?\\s?)+.*?[\\.!?\\.{3}]\\s*$)";
    private static final String FIRST_LETTER_OF_SENTENCE = "[A-ZА-Я]";

    @Override
    public void parse(String text, Component component) throws ProjectCompositeException {
        LOGGER.info("Start parse(String text, Component component). Component class  = " + component.getClass() + ", text = " + text);
        Pattern pattern = Pattern.compile(SENTENCE_REG_EXP);
        Matcher matcher = pattern.matcher(text);
        int endIndex = 1;
        while (matcher.find(endIndex - 1)) {
            String sentence = matcher.group();
            endIndex = matcher.end();
            int length = sentence.length();
            if (String.valueOf(sentence.charAt(length - 1)).matches(FIRST_LETTER_OF_SENTENCE)) {
                sentence = sentence.substring(0, length - 1).trim();
            }
            Component textComposite = new TextComposite(ComponentType.SENTENCE);
            component.add(textComposite);
            nextParser.parse(sentence, textComposite);
            LOGGER.info("After parse(String text, Component component). Component = " + component);
        }
        LOGGER.info("End parse(String text, Component component).");
    }
}
