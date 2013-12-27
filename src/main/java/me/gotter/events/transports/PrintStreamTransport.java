package me.gotter.events.transports;

import me.gotter.events.AbstractEvent;
import me.gotter.events.Event;
import me.gotter.events.Transport;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * Prints event using toString method
 * Has special rendering rule for DebugEvent
 *
 */
public class PrintStreamTransport implements Transport {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("[yyyy-mm-dd HH:mm:ss.S] ");
    private static final String spacesPrefix = "                        ";
    private PrintStream printer;

    /**
     * Constructs printer to StdOut
     */
    public PrintStreamTransport()
    {
        this(System.out);
    }

    /**
     * Constructs printer to any PrintStream
     *
     * @param target Stream to print
     * @throws java.lang.NullPointerException If target is null
     */
    public PrintStreamTransport(PrintStream target)
    {
        if (target == null) {
            throw new NullPointerException();
        }
        printer = target;
    }

    @Override
    public void send(Event event) {
        if (event == null) {
            return;
        }

        String datix = dateFormat.format(event.getTime());

        if (event instanceof AbstractEvent) {
            AbstractEvent ae = (AbstractEvent) event;
            boolean first = true;
            for (Map.Entry<String, String> row : ae.toStringMap().entrySet()) {
                if (first) {
                    first = false;
                    printer.println(datix + row.getKey() + ": " + row.getValue());
                } else {
                    printer.println(spacesPrefix + row.getKey() + ": " + row.getValue());
                }
            }
        } else {
            printer.println(datix + event);
        }
    }
}
