package by.khodokevich.composite.entity;

public enum ComponentType {
    TEXT("    ", "\n"),
    PARAGRAPH("", " "),
    SENTENCE("", " "),
    LEXEME("", ""),
    WORD("", ""),
    PUNCTUATION("", ""),
    NUMBER("", ""),
    LETTER("", "");

    private String beforeComponent;
    private String afterComponent;

    ComponentType(String beforeComponent, String afterComponent) {
        this.beforeComponent = beforeComponent;
        this.afterComponent = afterComponent;
    }

    public String getBeforeComponent() {
        return beforeComponent;
    }

    public String getAfterComponent() {
        return afterComponent;
    }
}
