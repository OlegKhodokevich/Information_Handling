package by.khodokevich.composite.exception;

public class ProjectCompositeException extends Exception {
    public ProjectCompositeException() {
    }

    public ProjectCompositeException(String message) {
        super(message);
    }

    public ProjectCompositeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProjectCompositeException(Throwable cause) {
        super(cause);
    }
}
