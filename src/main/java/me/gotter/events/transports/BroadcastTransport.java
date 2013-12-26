package me.gotter.events.transports;

import me.gotter.events.Event;
import me.gotter.events.Transport;

/**
 * Broadcast transport
 * Thread-safe
 *
 */
public class BroadcastTransport implements Transport {

    private Transport[] transports = null;

    private Object modificationLock = new Object();

    /**
     * Adds provided transports to broadcast
     *
     * @param another Transport to add
     *
     * @throws IllegalArgumentException If one of provided transports is this
     */
    public void add(Transport... another) {
        if (another == null || another.length == 0) {
            return;
        }
        for (Transport ti : another) {
            if (ti != null) {
                add(ti);
            }
        }
    }

    /**
     * Adds provided transport to broadcast
     *
     * @param another Transport to add
     *
     * @throws NullPointerException     If null provided
     * @throws IllegalArgumentException If provided this
     */
    public void add(Transport another)
    {
        if (another == null) {
            throw new NullPointerException();
        }
        if (another == this) {
            throw new IllegalArgumentException("First-level recursion check failed");
        }
        synchronized (modificationLock) {
            if (transports == null) {
                transports = new Transport[]{another};
            } else {
                Transport[] newT = new Transport[transports.length + 1];
                for (int i = 0; i < transports.length; i++) {
                    newT[i] = transports[i];
                }
                newT[newT.length - 1] = another;
                transports = newT;
            }
        }
    }

    /**
     * Removes transport from broadcast
     *
     * @param another Transport to remove
     */
    public void remove(Transport another)
    {
        if (another == null) {
            throw new NullPointerException();
        }
        synchronized (modificationLock){
            if (transports == null) {
                // Nothing to remove
            }
            if (transports.length == 1) {
                if (transports[0] == another || transports[0].equals(another)) {
                    transports = null;
                }
            } else {
                int occurrences = 0;
                for (int i = 0; i < transports.length; i++) {
                    if (transports[i] == another || transports[i].equals(another)) {
                        occurrences++;
                    }
                }
                if (occurrences == transports.length) {
                    transports = null;
                } else if (occurrences == 0) {
                    // No matches
                } else {
                    Transport[] newT = new Transport[transports.length - occurrences];
                    int j=0;
                    for (int i = 0; i < transports.length; i++) {
                        if (transports[i] == another || transports[i].equals(another)) {
                            continue;
                        }
                        newT[j++] = transports[i];
                    }
                    transports = newT;
                }
            }
        }

    }

    /**
     * @return Amount of transports in broadcast
     */
    public int size()
    {
        Transport[] clone = transports;
        if (clone == null) {
            return 0;
        } else {
            return clone.length;
        }
    }

    @Override
    public void send(Event event) {
        Transport[] clone = transports;
        if (clone == null) {
            // Nowhere to send
            return;
        }
        for (Transport ti : clone) {
            ti.send(event);
        }
    }
}
