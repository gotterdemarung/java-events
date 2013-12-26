package me.gotter.events;

import me.gotter.events.transports.BlackholeTransport;

/**
 * Abstract hub that can attach event transports and send events to it
 * Does not return null if transport not set
 */
public class AbstractHub implements Hub {

    private Transport pipe;

    @Override
    public void setEventTransport(Transport transport) {
        this.pipe = transport;
    }

    @Override
    public Transport getEventTransport() {
        if (pipe == null) {
            return BlackholeTransport.Instance;
        } else {
            return pipe;
        }
    }

    @Override
    public void send(Event event) {
        getEventTransport().send(event);
    }
}
