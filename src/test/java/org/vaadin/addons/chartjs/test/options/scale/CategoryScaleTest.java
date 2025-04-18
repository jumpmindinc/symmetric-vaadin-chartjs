package org.vaadin.addons.chartjs.test.options.scale;

import elemental.json.JsonObject;
import org.junit.Test;
import org.vaadin.addons.chartjs.options.Position;
import org.vaadin.addons.chartjs.options.scale.CategoryScale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CategoryScaleTest {

    @Test
    public void testCategoryScale() {
        JsonObject actual = new CategoryScale()
            .display(true)
            .scaleLabel().display(true).labelString("Quarter")
            .and().ticks().fontSize(10)
            .and().position(Position.BOTTOM).buildJson();

        assertNotNull(actual);
        assertEquals("category", actual.getString("type"));
        assertTrue(actual.getBoolean("display"));
        assertEquals("bottom", actual.getString("position"));

        JsonObject scaleLabel = actual.getObject("scaleLabel");
        assertTrue(scaleLabel.getBoolean("display"));
        assertEquals("Quarter", scaleLabel.getString("labelString"));

        JsonObject ticks = actual.getObject("ticks");
        assertEquals(10, ticks.getNumber("fontSize"), 0);
    }
}
