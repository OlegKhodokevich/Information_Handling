package by.khodokevich.composite.interpreter.expression;

public class NumberExpression implements MathExpression{
    private final int value;

    public NumberExpression(int value) {
        this.value = value;
    }

    @Override
    public int interpret() {
        return value;
    }
}
