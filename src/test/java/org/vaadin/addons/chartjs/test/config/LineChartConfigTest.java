package org.vaadin.addons.chartjs.test.config;

import elemental.json.JsonArray;
import elemental.json.JsonObject;
import org.junit.Test;
import org.vaadin.addons.chartjs.config.LineChartConfig;
import org.vaadin.addons.chartjs.data.LineDataset;
import org.vaadin.addons.chartjs.options.InteractionMode;
import org.vaadin.addons.chartjs.options.Position;
import org.vaadin.addons.chartjs.options.scale.Axis;
import org.vaadin.addons.chartjs.options.scale.CategoryScale;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class LineChartConfigTest {

    @Test
    public void testConfig() {
        int fontSize = 9;

        LineChartConfig config = new LineChartConfig();
        config.data().addDataset(generate())
            .and().options().animation().duration(0)
            .and().responsive(true).maintainAspectRatio(false).legend().labels().boxWidth(20).fontSize(fontSize)
            .and().and().tooltips().mode(InteractionMode.INDEX).intersect(false)
            .and().hover().mode(InteractionMode.NEAREST).intersect(true)
            .and().scales()
            .add(Axis.X, new CategoryScale().display(true).scaleLabel().display(true).labelString("Quarter")
                .and().ticks().fontSize(fontSize).and().position(Position.BOTTOM))
            .and().done();
        JsonObject actual = config.buildJson();

        assertNotNull(actual);
        assertEquals("line", actual.getString("type"));

        JsonObject data = actual.getObject("data");
        assertNotNull(data);

        JsonArray datasets = data.getArray("datasets");
        assertEquals(1, datasets.length());

        JsonObject dataset = datasets.get(0);
        assertEquals(generate().buildJson().toJson(), dataset.toJson());

        JsonObject options = actual.getObject("options");
        assertTrue(options.getBoolean("responsive"));
        assertFalse(options.getBoolean("maintainAspectRatio"));

        JsonObject hoverOptions = options.getObject("hover");
        assertEquals("nearest", hoverOptions.getString("mode"));
        assertTrue(hoverOptions.getBoolean("intersect"));

        JsonObject tooltips = options.getObject("tooltips");
        assertEquals("index", tooltips.getString("mode"));
        assertFalse(tooltips.getBoolean("intersect"));

        JsonObject animation = options.getObject("animation");
        assertEquals(0, animation.getNumber("duration"), 0);

        JsonObject legend = options.getObject("legend");
        assertNotNull(legend);

        JsonObject labels = legend.getObject("labels");
        assertEquals(20, labels.getNumber("boxWidth"), 0);
        assertEquals(fontSize, labels.getNumber("fontSize"), 0);

        JsonObject scales = options.getObject("scales");
        assertNotNull(scales);

        JsonArray xAxes = scales.getArray("xAxes");
        assertNotNull(xAxes);

        JsonObject xAxis = xAxes.getObject(0);
        assertEquals("category", xAxis.getString("type"));
        assertTrue(xAxis.getBoolean("display"));
        assertEquals("bottom", xAxis.getString("position"));

        JsonObject scaleLabel = xAxis.getObject("scaleLabel");
        assertTrue(scaleLabel.getBoolean("display"));
        assertEquals("Quarter", scaleLabel.getString("labelString"));

        JsonObject ticks = xAxis.getObject("ticks");
        assertEquals(fontSize, ticks.getNumber("fontSize"), 0);
    }

    private LineDataset generate() {
        LineDataset dataset = new LineDataset();
        dataset.label("Sales");
        dataset.dataAsList(List.of(65.2, 59.31, 80.93));
        dataset.fill(false);
        dataset.borderColor("rgba(75,192,192,1)");
        dataset.lineTension(0.1);
        dataset.pointRadius(0);
        return dataset;
    }
}
