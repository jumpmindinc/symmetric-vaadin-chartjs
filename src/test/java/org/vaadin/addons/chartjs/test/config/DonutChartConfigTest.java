package org.vaadin.addons.chartjs.test.config;

import elemental.json.JsonArray;
import elemental.json.JsonObject;
import org.junit.Test;
import org.vaadin.addons.chartjs.config.DonutChartConfig;
import org.vaadin.addons.chartjs.data.PieDataset;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class DonutChartConfigTest {

    @Test
    public void testConfig() {
        DonutChartConfig config = new DonutChartConfig();
        config.data()
            .labels("rate")
            .addDataset(generate())
            .and().options().legend().display(false)
            .and().rotation(Math.PI).circumference(Math.PI).tooltips().enabled(false)
            .and().title().display(false).text("Test")
            .and().animation().animateScale(false).animateRotate(false).duration(0)
            .and().cutoutPercentage(15).maintainAspectRatio(false).responsive(true).done();

        JsonObject actual = config.buildJson();

        assertNotNull(actual);
        assertEquals("doughnut", actual.getString("type"));

        JsonObject data = actual.getObject("data");
        assertNotNull(data);

        JsonArray labels = data.getArray("labels");
        assertEquals(1, labels.length());
        assertEquals("rate", labels.getString(0));

        JsonArray datasets = data.getArray("datasets");
        assertEquals(1, datasets.length());

        JsonObject dataset = datasets.get(0);
        assertEquals(generate().buildJson().toJson(), dataset.toJson());

        JsonObject options = actual.getObject("options");
        assertTrue(options.getBoolean("responsive"));
        assertFalse(options.getBoolean("maintainAspectRatio"));

        JsonObject title = options.getObject("title");
        assertFalse(title.getBoolean("display"));
        assertEquals("Test", title.getString("text"));

        JsonObject tooltips = options.getObject("tooltips");
        assertFalse(tooltips.getBoolean("enabled"));

        JsonObject legend = options.getObject("legend");
        assertFalse(legend.getBoolean("display"));

        JsonObject animation = options.getObject("animation");
        assertEquals(0, animation.getNumber("duration"), 0);
        assertFalse(animation.getBoolean("animateRotate"));
        assertFalse(animation.getBoolean("animateScale"));

        assertEquals(15, options.getNumber("cutoutPercentage"), 0);
        assertEquals(Math.PI, options.getNumber("rotation"), 0);
        assertEquals(Math.PI, options.getNumber("circumference"), 0);
    }

    private PieDataset generate() {
        PieDataset dataset = new PieDataset();
        dataset.label("Sales");
        dataset.dataAsList(List.of(65.2, 59.31, 80.93, 96.2));
        dataset.backgroundColor("rgba(75,192,192,0.2)");
        dataset.borderColor("rgba(75,192,192,1)");
        dataset.borderWidth(1);
        return dataset;
    }
}
