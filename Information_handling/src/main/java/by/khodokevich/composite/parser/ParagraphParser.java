package by.khodokevich.composite.parser;

import by.khodokevich.composite.entity.Component;
import by.khodokevich.composite.entity.ComponentType;
import by.khodokevich.composite.entity.TextComposite;
import by.khodokevich.composite.exception.ProjectCompositeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParagraphParser extends AbstractTextParser{
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String PARAGRAPH_SEPARATOR_REG_EXP = "\n    ";

    @Override
    public void parse(String text, Component component) throws ProjectCompositeException {
        LOGGER.info("Start parse(String text, Component component). Component class  = " + component.getClass() + ", text = " + text);
        String [] paragraphs = text.split(PARAGRAPH_SEPARATOR_REG_EXP);
        for (int i = 0; i < paragraphs.length; i++) {
            Component textComposite = new TextComposite(ComponentType.PARAGRAPH);
            component.add(textComposite);
            paragraphs[i] = paragraphs[i].strip();
            nextParser.parse(paragraphs[i], textComposite);
            LOGGER.info("After parse(String text, Component component). Component = " + component);
        }
        LOGGER.info("End parse(String text, Component component).");
    }

}
