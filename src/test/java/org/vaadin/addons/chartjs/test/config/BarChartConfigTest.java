package org.vaadin.addons.chartjs.test.config;

import elemental.json.JsonArray;
import elemental.json.JsonObject;
import org.junit.Test;
import org.vaadin.addons.chartjs.config.BarChartConfig;
import org.vaadin.addons.chartjs.data.BarDataset;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;


public class BarChartConfigTest {

    @Test
    public void testConfig() {
        BarChartConfig config = new BarChartConfig();
        config.data().labels("FY25").addDataset(generate())
            .and().options().legend().display(false)
            .and().tooltips().callbacks()
            .and().and().maintainAspectRatio(false).done();

        JsonObject actual = config.buildJson();

        assertNotNull(actual);
        assertEquals("bar", actual.getString("type"));

        JsonObject data = actual.getObject("data");
        assertNotNull(data);

        JsonArray labels = data.getArray("labels");
        assertEquals(1, labels.length());
        assertEquals("FY25", labels.getString(0));

        JsonArray datasets = data.getArray("datasets");
        assertEquals(1, datasets.length());

        JsonObject dataset = datasets.get(0);
        assertEquals(generate().buildJson().toJson(), dataset.toJson());

        JsonObject options = actual.getObject("options");
        assertFalse(options.getBoolean("maintainAspectRatio"));

        JsonObject legend = options.getObject("legend");
        assertFalse(legend.getBoolean("display"));
    }

    private BarDataset generate() {
        BarDataset dataset = new BarDataset();
        dataset.label("Sales");
        dataset.dataAsList(List.of(65.2, 59.31, 80.93, 96.2));
        dataset.backgroundColor("rgba(75,192,192,0.2)");
        dataset.borderColor("rgba(75,192,192,1)");
        dataset.borderWidth(1);
        return dataset;
    }
}
