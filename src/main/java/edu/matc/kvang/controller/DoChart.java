package edu.matc.kvang.controller;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by kvang on 2/20/17.
 */
@WebServlet (
        name = "DoChart",
        urlPatterns = {"/DoChart"}
)
public class DoChart extends HttpServlet {

    /**
     *  Output chart object with size.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("image/png");

        OutputStream outputStream = response.getOutputStream();

        JFreeChart chart = getChart();
        int width = 500;
        int height = 350;

        ChartUtilities.writeChartAsPNG(outputStream, chart, width, height);
    }

    /**
     * Create chart with data then assign to chart object.
     *
     * @return chart
     */
    public JFreeChart getChart() {

        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Automobile", new Double(500.68));
        dataset.setValue("Childcare", new Double(340.22));
        dataset.setValue("Household", new Double(50.99));
        dataset.setValue("Credit card", new Double(200.67));
        dataset.setValue("Others", new Double(100.50));

        JFreeChart chart = ChartFactory.createPieChart(
                "Expenses",     // Title
                dataset             // data
                //true,         // legend
                //true,        // tooltips
                //false           // URL
        );

        PiePlot piePlot = new PiePlot();
        piePlot.setSimpleLabels(true);

        //chart.setBorderVisible(false);

        return chart;
    }

}
