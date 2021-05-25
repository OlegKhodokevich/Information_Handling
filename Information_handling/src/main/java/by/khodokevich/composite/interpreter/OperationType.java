package by.khodokevich.composite.interpreter;

import by.khodokevich.composite.interpreter.expression.MathExpression;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public enum OperationType implements BiFunction<MathExpression, MathExpression, Integer> {

    RIGHT_SHIFT(">>", 1),
    UNSIGNED_RIGHT_SHIFT(">>>", 1),
    LEFT_SHIFT("<<", 1),
    AND("&", 2),
    XOR("^", 3),
    OR("|", 4);

    private final String valueString;
    private final int priority;

    private static final Map<String, OperationType> operationMap;

    static {
        operationMap = new HashMap<>();

        for (OperationType element : values()) {
            operationMap.put(element.getValueString(), element);

        }
    }

    OperationType(String value, int priority) {
        this.valueString = value;
        this.priority = priority;
    }

    public String getValueString() {
        return valueString;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public Integer apply(MathExpression mathExpression, MathExpression mathExpression2) {
        return null;
    }

    public static OperationType findByValueString(String valueString) {
        return operationMap.get(valueString);
    }

//    public static boolean contain(String valueString) {
//
//    }
}
