package by.khodokevich.composite.parser;

import by.khodokevich.composite.entity.Component;
import by.khodokevich.composite.entity.ComponentType;
import by.khodokevich.composite.entity.TextComposite;
import by.khodokevich.composite.exception.ProjectCompositeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser extends AbstractTextParser {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String LEXEME_SEPARATOR = "\\s+";
    private static final String WORD_REX_EXP = "[A-Za-zА-Яа-я][a-zа-я]*";
    private static final String WORD_WITH_PUNCTUATION_REX_EXP = "[\\.,:;?!\\{\\[\\(\"\'“”\\)\\]\\}]*[A-Za-zА-Яа-я][a-zа-я]*[\\.,:;?!\\{\\[\\(\"\'“”\\)\\]\\}.{3}]*";
    private static final String PUNCTUATION_REX_EXP = "[\\.,:;?!\\{\\[\\(\"\'“”\\)\\]\\}\\.{3}]+";
//    private static final

    @Override
    public void parse(String text, Component component) throws ProjectCompositeException {
        LOGGER.info("Start parse(String text, Component component). Component class  = " + component.getClass() + ", text = " + text);
        String[] lexemes = text.split(LEXEME_SEPARATOR);
        Pattern patternPunctuation = Pattern.compile(PUNCTUATION_REX_EXP);
        Pattern patternWord = Pattern.compile(WORD_REX_EXP);


        for (int i = 0; i < lexemes.length; i++) {
            if (lexemes[i].matches(WORD_WITH_PUNCTUATION_REX_EXP)) {
                if (component.getType() == ComponentType.SENTENCE) {        //TODO
                    TextComposite textCompositeLexeme = new TextComposite(ComponentType.LEXEME);
                    component.add(textCompositeLexeme);
                    parse(lexemes[i], textCompositeLexeme);
                } else if (component.getType() == ComponentType.LEXEME) {
//                    System.out.println(lexemes[i]);  //TODO
                    Matcher matcherWord = patternWord.matcher(lexemes[i]);
                    matcherWord.find();
                    int startIndex = matcherWord.start();
                    if (startIndex > 0){
                        String punctuation = lexemes[i].substring(0,startIndex);
                        TextComposite textCompositePunctuation = new TextComposite(ComponentType.PUNCTUATION);
                        component.add(textCompositePunctuation);
                        nextParser.parse(punctuation, textCompositePunctuation);
                    }

                    String word = matcherWord.group();
                    Component textComposite = new TextComposite(ComponentType.WORD);
                    component.add(textComposite);
                    nextParser.parse(word, textComposite);


                    int endIndex = matcherWord.end();
                    int lengthLexeme = lexemes[i].length();
//                    System.out.println("endIndex = " + endIndex + "  lengthLexeme - 1 = " + (lengthLexeme - 1)); //TODO
                    if (endIndex < lengthLexeme){
//                        System.out.println("lexemes[i] = " + lexemes[i] + " endIndex = " + endIndex);       //TODO
                        String punctuation = lexemes[i].substring(endIndex, lengthLexeme);
//                        System.out.println(punctuation);
                        TextComposite textCompositePunctuation = new TextComposite(ComponentType.PUNCTUATION);
                        component.add(textCompositePunctuation);
                        nextParser.parse(punctuation, textCompositePunctuation);
                    }

                    LOGGER.info("After parse(String text, Component component). Component = " + textComposite);

                } else {
                    throw new ProjectCompositeException("Wrong composite type. Text = " + lexemes[i] + " Component" + component.getType());
                }

            } else {
                Component symbolComponent = new TextComposite(ComponentType.LEXEME);
                component.add(symbolComponent);
                nextParser.parse(lexemes[i], symbolComponent);
                LOGGER.info("After parse(String text, Component component). Component = " + symbolComponent);
            }
        }
        LOGGER.info("End parse(String text, Component component).");
    }
}

