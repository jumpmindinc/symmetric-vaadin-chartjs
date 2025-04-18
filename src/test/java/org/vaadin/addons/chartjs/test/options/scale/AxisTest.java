package org.vaadin.addons.chartjs.test.options.scale;

import org.junit.Test;
import org.vaadin.addons.chartjs.options.scale.Axis;

import static org.junit.Assert.assertEquals;

public class AxisTest {

    @Test
    public void testAxisEnumValues() {
        assertEquals("X", Axis.X.name());
        assertEquals("Y", Axis.Y.name());
    }

    @Test
    public void testEnumCount() {
        Axis[] values = Axis.values();
        assertEquals(2, values.length);
    }
}
