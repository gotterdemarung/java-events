package me.gotter.events;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;

public class AbstractEventTest {
    @Test
    public void testToStringMap() throws Exception {
        AbstractEvent e = new TestEvent("text");
        HashMap<String, String> answer = new TestEvent("text").toStringMap();

        Assert.assertEquals(3, answer.size());
        Assert.assertTrue(answer.containsKey("Class"));
        Assert.assertTrue(answer.containsKey("Time"));
        Assert.assertTrue(answer.containsKey("Source"));
        Assert.assertEquals(e.getTime().toString(), answer.get("Time"));
        Assert.assertEquals("text", answer.get("Source"));
        Assert.assertEquals(e.getClass().getCanonicalName(), answer.get("Class"));

        // Without source
        answer = new TestEvent(null).toStringMap();

        Assert.assertEquals(2, answer.size());
        Assert.assertFalse(answer.containsKey("Source"));
    }

    @Test
    public void testGetTime() throws Exception {
        Date x = new Date();
        Event e = new TestEvent(null);

        Assert.assertTrue( 5000L > Math.abs(e.getTime().getTime() - x.getTime()) );

    }

    @Test
    public void testGetSource() throws Exception {
        Event e = new TestEvent("example");
        Assert.assertEquals("example", e.getSource());

        e = new TestEvent(this);
        Assert.assertSame(this, e.getSource());

        e = new TestEvent(null);
        Assert.assertNull(e.getSource());
    }

    @Test
    public void testHashCode() throws Exception {
        Event e = new TestEvent(null);
        Assert.assertEquals(e.getTime().hashCode(), e.hashCode());

        e = new TestEvent("some");
        Assert.assertEquals(e.getTime().hashCode() ^ "some".hashCode(), e.hashCode());
    }

    public static class TestEvent extends AbstractEvent
    {
        public TestEvent(Object where) {
            super(where);
        }
    }
}
