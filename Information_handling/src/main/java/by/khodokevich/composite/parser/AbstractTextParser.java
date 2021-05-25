package by.khodokevich.composite.parser;

import by.khodokevich.composite.entity.Component;
import by.khodokevich.composite.exception.ProjectCompositeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractTextParser {
    private static final Logger LOGGER = LogManager.getLogger();
    protected AbstractTextParser nextParser = DefaultTextParser.getDefaultTextParser();

    void setNextParser(AbstractTextParser nextParser) {
        this.nextParser = nextParser;
    }

    public abstract void parse(String text, Component component) throws ProjectCompositeException;

    private static class DefaultTextParser extends AbstractTextParser{
        private static AbstractTextParser defaultTextParser = new DefaultTextParser();

        public static AbstractTextParser getDefaultTextParser(){

            return defaultTextParser;
        }

        @Override
        public void parse(String text, Component component) {
            LOGGER.info("It is the last element which can't be parsed.");
        }
    }
}
