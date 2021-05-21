package by.khodokevich.composite.parser;

import by.khodokevich.composite.entity.Component;
import by.khodokevich.composite.entity.ComponentType;
import by.khodokevich.composite.entity.LetterLeaf;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SymbolParser extends AbstractTextParser{
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void parse(String text, Component component) {
        LOGGER.info("Start parse(String text, Component component). Component class  = " + component.getClass() + ", text = " + text);
        for (int i = 0; i < text.length(); i++) {
            String symbol = String.valueOf(text.charAt(i));
            Component symbolComponent = new LetterLeaf(symbol, ComponentType.LETTER);
            component.add(symbolComponent);
        }
        LOGGER.info("Start parse(String text, Component component). Component = " + component);
    }

}
