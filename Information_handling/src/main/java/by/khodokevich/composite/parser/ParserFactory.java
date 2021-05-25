package by.khodokevich.composite.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParserFactory {
    private static final Logger LOGGER = LogManager.getLogger();

    private ParserFactory() {
    }

    public static AbstractTextParser createChainOfParser() {
        LOGGER.info("Start createChainOfParser().");
        AbstractTextParser paragraphParser = new ParagraphParser();
        AbstractTextParser sentenceParser = new SentenceParser();
        AbstractTextParser lexemeParser = new LexemeParser();
        AbstractTextParser symbolParser = new SymbolParser();

        paragraphParser.setNextParser(sentenceParser);
        sentenceParser.setNextParser(lexemeParser);
        lexemeParser.setNextParser(symbolParser);

        LOGGER.info("End createChainOfParser().");
        return paragraphParser;
    }


}
