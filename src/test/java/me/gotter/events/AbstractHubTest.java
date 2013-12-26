package me.gotter.events;

import me.gotter.events.transports.BlackholeTransport;
import org.junit.Assert;
import org.junit.Test;

public class AbstractHubTest {
    @Test
    public void testSetEventTransport() throws Exception {
        Hub h = new AbstractHub() {};

        h.setEventTransport(null); // No exceptions
        h.setEventTransport(new TestTransport()); // All ok
        h.setEventTransport(h); // Recursive
    }

    @Test
    public void testGetEventTransport() throws Exception {
        Hub h = new AbstractHub() {};

        // Returns blackhole on null
        Assert.assertNotNull(h.getEventTransport());
        Assert.assertTrue(h.getEventTransport() instanceof BlackholeTransport);

        // Returns other if set
        Transport t = new TestTransport();
        h.setEventTransport(t);
        Assert.assertSame(t, h.getEventTransport());
    }

    @Test
    public void testSend() throws Exception {
        final TestContainer cont = new TestContainer();

        Assert.assertSame(0, cont.i);
        Hub h = new AbstractHub() {};
        h.send(new AbstractEvent(null) {});
        Assert.assertSame(0, cont.i);

        h.setEventTransport(new Transport() {
            @Override
            public void send(Event event) {
                cont.i++;
            }
        });

        h.send(null);
        Assert.assertSame(0, cont.i);

        h.send(new AbstractEvent(null) {});
        Assert.assertSame(1, cont.i);

        h.send(new AbstractEvent(null) {});
        Assert.assertSame(2, cont.i);
    }

    public static class TestTransport implements Transport
    {
        @Override
        public void send(Event event) {
            // do nothing
        }
    }

    public static class TestContainer
    {
        public int i;
    }
}
