package org.vaadin.addons.chartjs.test.options.scale;

import elemental.json.JsonObject;
import org.junit.Test;
import org.vaadin.addons.chartjs.options.Position;
import org.vaadin.addons.chartjs.options.scale.LinearScale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class LinearScaleTest {

    @Test
    public void testLinearScale() {
        JsonObject actual = new LinearScale()
            .display(true)
            .position(Position.LEFT)
            .ticks()
            .minRotation(10)
            .maxRotation(100)
            .beginAtZero(true)
            .and().buildJson();

        assertNotNull(actual);
        assertEquals("linear", actual.getString("type"));
        assertTrue(actual.getBoolean("display"));
        assertEquals("left", actual.getString("position"));

        JsonObject ticks = actual.getObject("ticks");
        assertTrue(ticks.getBoolean("beginAtZero"));
        assertEquals(10, ticks.getNumber("minRotation"), 0);
        assertEquals(100, ticks.getNumber("maxRotation"), 0);
    }
}
