package org.vaadin.addons.chartjs.test.options;

import org.junit.Test;
import org.vaadin.addons.chartjs.options.InteractionMode;

import static org.junit.Assert.assertEquals;

public class PositionTest {

    @Test
    public void testEnumValues() {
        assertEquals("POINT", InteractionMode.POINT.name());
        assertEquals("NEAREST", InteractionMode.NEAREST.name());
        assertEquals("INDEX", InteractionMode.INDEX.name());
        assertEquals("DATASET", InteractionMode.DATASET.name());
        assertEquals("X", InteractionMode.X.name());
        assertEquals("Y", InteractionMode.Y.name());
    }

    @Test
    public void testEnumCount() {
        assertEquals(6, InteractionMode.values().length);
    }

}
