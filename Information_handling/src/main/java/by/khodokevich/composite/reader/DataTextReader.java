package by.khodokevich.composite.reader;

import by.khodokevich.composite.exception.ProjectCompositeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DataTextReader {
    private static final Logger LOGGER = LogManager.getLogger();

    public String readAllText(String filename) throws ProjectCompositeException {
        LOGGER.info("Start readAllText(String filename). File = " + filename);
        String text;
        try {
            text = Files.readString(Path.of(filename));
        } catch (IOException e) {
            throw new ProjectCompositeException("File is incorrect.");
        }
        if (text.isBlank()) {
            throw new ProjectCompositeException("File is empty.");
        }
        LOGGER.info("End readAllText(String filename). Text = " + text);
        return text;


    }
}
