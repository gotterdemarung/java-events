package me.gotter.events;

import org.junit.Assert;
import org.junit.Test;

public class AbstractFilterTest {
    @Test
    public void testSend() throws Exception {
        final TestContainer cont = new TestContainer();
        AbstractFilter f = new TestFilter(new Transport() {
            @Override
            public void send(Event event) {
                cont.i++;
            }
        });

        Assert.assertEquals(0, cont.i);
        f.send(null);
        Assert.assertEquals(0, cont.i);
        f.send(new AbstractEvent("foo") {});
        Assert.assertEquals(0, cont.i);
        f.send(new AbstractEvent("foo-bar") {});
        Assert.assertEquals(1, cont.i);
        f.send(new AbstractEvent("foo-bar") {});
        Assert.assertEquals(2, cont.i);
    }

    @Test
    public void testIsEventValid() throws Exception {
        AbstractFilter f = new TestFilter(null);

        Assert.assertTrue(f.isEventValid(new AbstractEvent("foo-bar") {}));
        Assert.assertFalse(f.isEventValid(new AbstractEvent("foo") {}));
    }

    public static class TestFilter extends AbstractFilter
    {
        public TestFilter(Transport delegate) {
            super(delegate);
        }

        @Override
        public boolean isEventValid(Event eventInterface) {
            return eventInterface.getSource().equals("foo-bar");
        }
    }

    public static class TestContainer
    {
        public int i;
    }
}
