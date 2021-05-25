package by.khodokevich.composite.interpreter;

import by.khodokevich.composite.interpreter.expression.MathExpression;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayDeque;
import java.util.Deque;

public class TextInterpreterContext {
    private static final Logger LOGGER = LogManager.getLogger();

    public int calculateExpression(String textExpression) {
//        String[] numbers = textExpression.split();
        Deque<MathExpression> expressionDeque = new ArrayDeque<>();
        Deque<OperationType> operationDeque = new ArrayDeque<>();



        return 0;           //TODO
    }

    private boolean fillQueue(Deque<MathExpression> expressionDeque, String[] numbers) {
        for (String number : numbers) {


        }
        return true;  //TODO
    }


}
