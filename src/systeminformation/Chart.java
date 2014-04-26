/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systeminformation;

import java.awt.Color;
import java.text.DecimalFormat;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 *
 * @author furkanb
 */
public class Chart {

    public PieDataset createPieDataSet(Hdd h) {
        DefaultPieDataset pieDataset = new DefaultPieDataset();

        pieDataset.setValue("Free", h.getFree());
        pieDataset.setValue("Used", h.getUsed());

        return pieDataset;
    }

    public JFreeChart create3DPieChart(PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart3D("", dataset, true, true, true);
        PiePlot3D p = (PiePlot3D) chart.getPlot();

        PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
                "{0}: {1} ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
        p.setLabelGenerator(gen);
//        p.setSimpleLabels(true);
        p.setForegroundAlpha(0.5f);
        p.setBackgroundAlpha(0.2f);
        chart.setBackgroundPaint(Color.white);
        chart.setAntiAlias(true);
        chart.setBorderVisible(true);

        return chart;
    }
}
