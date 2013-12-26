package me.gotter.events;

import java.util.Date;
import java.util.HashMap;

/**
 * Base class for all events
 */
public abstract class AbstractEvent implements Event {
    private Date when;
    private Object where;

    private int hashCode;

    /**
     * Default constructor
     *
     * @param where object, emitted an event
     */
    public AbstractEvent(Object where) {
        // Current date
        this.when = new Date();

        // Emitter
        this.where = where;

        // Calculating hash
        hashCode = this.when.hashCode();
        if (this.where != null) {
            hashCode = hashCode ^ this.where.hashCode();
        }
    }

    /**
     * Converts event's data into hashmap
     *
     * @return Resulting hashmap
     */
    public HashMap<String, String> toStringMap()
    {
        HashMap<String, String> answer = new HashMap<String, String>();
        answer.put("Time", when.toString());
        answer.put("Class", this.getClass().getCanonicalName());
        if (where != null) {
            answer.put("Source", where.toString());
        }

        return answer;
    }

    @Override
    public final Date getTime() {
        return when;
    }

    @Override
    public final Object getSource() {
        return where;
    }

    @Override
    public int hashCode() {
        return hashCode;
    }
}
