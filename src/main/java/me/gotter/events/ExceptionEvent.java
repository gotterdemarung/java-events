package me.gotter.events;

import java.util.HashMap;

/**
 * Event for exceptions
 */
public class ExceptionEvent extends AbstractEvent {

    private Throwable exception;

    public ExceptionEvent(Throwable exception, Object where) {
        super(where);
        this.exception = exception;
    }

    /**
     * @return exception
     */
    public Throwable getException() {
        return exception;
    }

    @Override
    public HashMap<String, String> toStringMap() {
        HashMap<String, String> answer = super.toStringMap();
        answer.put("Exception class", exception.getClass().getCanonicalName());
        answer.put("Exception message", exception.getMessage());
        answer.put("Exception", exception.toString());

        return answer;
    }

    @Override
    public int hashCode() {
        return super.hashCode() ^ this.exception.hashCode();
    }
}
