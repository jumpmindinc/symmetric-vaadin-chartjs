package org.vaadin.addons.chartjs.test.options;

import org.junit.Test;
import org.vaadin.addons.chartjs.options.Position;

import static org.junit.Assert.assertEquals;

public class InteractionModeTest {

    @Test
    public void testEnumValues() {
        assertEquals("LEFT", Position.LEFT.name());
        assertEquals("RIGHT", Position.RIGHT.name());
        assertEquals("TOP", Position.TOP.name());
        assertEquals("BOTTOM", Position.BOTTOM.name());
    }

    @Test
    public void testEnumCount() {
        assertEquals(4, Position.values().length);
    }
}
