package me.gotter.events;

import java.util.Date;

/**
 * Interface for all events
 */
public interface Event {
    /**
     * @return time of event
     */
    Date getTime();

    /**
     *
     * @return source of event
     */
    Object getSource();
}
