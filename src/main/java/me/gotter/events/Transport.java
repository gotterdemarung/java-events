package me.gotter.events;

/**
 * Interface for event transports
 */
public interface Transport {
    void send(Event event);
}
