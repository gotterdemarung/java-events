package me.gotter.events.transports;

import me.gotter.events.Event;
import me.gotter.events.Transport;

/**
 * Does nothing to received event
 */
public class BlackholeTransport implements Transport {

    /**
     * Instance of blackhole transport
     */
    public static final Transport Instance = new BlackholeTransport();

    @Override
    public void send(Event event) {
        // Do nothing
    }
}
