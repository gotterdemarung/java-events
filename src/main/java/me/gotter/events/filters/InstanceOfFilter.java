package me.gotter.events.filters;

import me.gotter.events.AbstractFilter;
import me.gotter.events.Event;
import me.gotter.events.Transport;

/**
 * Passes only certain types of event
 * Check bases on instanceof
 */
public class InstanceOfFilter extends AbstractFilter {

    private Class<Event> eventClass;

    public InstanceOfFilter(Transport transport, Class<Event> eventClass)
    {
        super(transport);
        this.eventClass = eventClass;
    }

    @Override
    public boolean isEventValid(Event eventInterface) {
        return eventInterface.getClass().isAssignableFrom(eventClass);
    }
}
