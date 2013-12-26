package me.gotter.events;

public abstract class AbstractFilter implements Transport {

    private Transport delegate;

    /**
     * Default constructor
     *
     * @param delegate Final event receiver
     */
    public AbstractFilter(Transport delegate)
    {
        this.delegate = delegate;
    }

    /**
     * Sends event to delegate on successful check
     *
     * @param event Event to be sent
     */
    @Override
    public void send(Event event) {
        if (event != null && isEventValid(event)) {
            delegate.send(event);
        }
    }

    public abstract boolean isEventValid(Event eventInterface);
}
