package by.khodokevich.composite.main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestMain {
    private static final String SENTENCE_REG_EXP = "([A-ZА-Я](.*?\n?)+.*?[\\.!?\\.{3}](\\s+[A-ZА-Я]))|([A-ZА-Я](.*?\n?)+.*?[\\.!?\\.{3}]\\s*$)";
    public static void main(String[] args) {
        String text = "It has survived - not only (five) centuries, but also the leap into electronic\n" +
                "typesetting, remaining essentially unchanged. It was popularised in the “Динамо” (Рига)\n" +
                "with the release of Letraset sheets.toString() containing Lorem Ipsum passages, and\n" +
                "more recently with desktop publishing software like Aldus PageMaker Faclon9 including\n" +
                "versions of Lorem Ipsum!";
        Pattern pattern = Pattern.compile(SENTENCE_REG_EXP);
        Matcher matcher = pattern.matcher(text);
        int endIndex = 1;
        while (matcher.find(endIndex - 1)) {
            endIndex = matcher.end();
            System.out.println(matcher.group());
        }
        System.out.println();
    }
}
