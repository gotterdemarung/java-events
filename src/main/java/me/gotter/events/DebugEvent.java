package me.gotter.events;

import java.util.HashMap;

/**
 * Event for debugging
 * Converts to string all varargs, received in constructor
 */
public class DebugEvent extends AbstractEvent {

    /**
     * List of debug messages
     */
    private String[] messages;

    /**
     * Default constructor
     *
     * @param where         Object, generated an event
     * @param debugMessages List of messages. They must be castable to string
     */
    public DebugEvent(Object where, Object... debugMessages) {
        super(where);
        if (debugMessages == null) {
            throw new NullPointerException();
        }
        messages = new String[debugMessages.length];
        for (int i=0; i < debugMessages.length; i++) {
            if (debugMessages[i] == null) {
                messages[i] = null;
            } else {
                messages[i] = debugMessages[i].toString();
            }
        }
    }

    /**
     * @return Array of messages inside debug event
     */
    public String[] getMessages()
    {
        return messages;
    }

    @Override
    public HashMap<String, String> toStringMap() {
        HashMap<String, String> answer = super.toStringMap();
        String[] msgs = getMessages();

        for (int i=0; i < msgs.length; i++) {
            answer.put("Debug #" + i, msgs[i]);
        }

        return answer;
    }
}
