package org.vaadin.addons.chartjs.test;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

import org.vaadin.addons.chartjs.ChartJs;
import org.vaadin.addons.chartjs.config.BarChartConfig;
import org.vaadin.addons.chartjs.config.ChartConfig;
import org.vaadin.addons.chartjs.data.BarDataset;
import org.vaadin.addons.chartjs.data.Dataset;
import org.vaadin.addons.chartjs.data.LineDataset;
import org.vaadin.addons.chartjs.options.Position;

@Route("demo")
public class DemoView extends VerticalLayout {

    public DemoView() {
        add(createBarChart());
    }

    private ChartJs createBarChart() {
        ChartJs barChart = new ChartJs(generateBarChartConfig());
        barChart.setHeight("800px");
        barChart.setWidth("1000px");
        return barChart;
    }

    private ChartConfig generateBarChartConfig() {
        BarChartConfig config = new BarChartConfig();
        config
            .data()
            .labels("January", "February", "March", "April", "May", "June", "July")
            .addDataset(
                new BarDataset()
                    .type()
                    .label("Dataset 1")
                    .backgroundColor("rgba(151,187,205,0.5)")
                    .borderColor("white")
                    .borderWidth(2))
            .addDataset(
                new LineDataset()
                    .type()
                    .label("Dataset 2")
                    .backgroundColor("rgba(222, 56, 117, 0.4)")
                    .borderColor("white")
                    .borderWidth(2))
            .addDataset(
                new BarDataset().type().label("Dataset 3").backgroundColor("rgba(220,220,220,0.5)"))
            .and()
            .options()
            .responsive(true)
            .title()
            .display(true)
            .position(Position.LEFT)
            .text("Chart.js Combo Bar Line Chart")
            .and()
            .tooltips()
            .enabled(true)
            .callbacks()
            .label("tooltipItem.yLabel  + ' %'")
            .and()
            .and()
            .done();

        generateBarChartData(config);
        return config;
    }

    private void generateBarChartData(BarChartConfig config) {
        List<String> labels = config.data().getLabels();
        for (Dataset<?, ?> ds : config.data().getDatasets()) {
            List<Double> data = new ArrayList<>();
            for (int i = 0; i < labels.size(); i++) {
                data.add((Math.random() > 0.5 ? 1.0 : -1.0) * Math.round(Math.random() * 100));
            }

            if (ds instanceof BarDataset bds) {
                bds.dataAsList(data);
            }

            if (ds instanceof LineDataset lds) {
                lds.dataAsList(data);
            }
        }
    }
}
