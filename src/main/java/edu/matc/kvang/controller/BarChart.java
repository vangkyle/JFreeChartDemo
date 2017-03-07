package edu.matc.kvang.controller;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by kvang on 3/6/17.
 */
@WebServlet(
        name="barchart",
        urlPatterns = "/barchart"
)
public class BarChart extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("image/png");

        OutputStream outputStream = response.getOutputStream();

        JFreeChart chart = makeBarChart();
        int width = 500;
        int height = 350;

        ChartUtilities.writeChartAsPNG(outputStream, chart, width, height);

    }

    public JFreeChart makeBarChart() {

        // local variables for expenses
        final String expense = "Expense";
        final String income = "Income";
        final String childcare = "Childcare";
        final String household = "Household";
        final String creditcard = "Credit cards";
        final String pay = "Pay Check";
        final DefaultCategoryDataset barDataset = new DefaultCategoryDataset();

        barDataset.addValue(75.00, expense, childcare);
        barDataset.addValue(50.00, expense, household);
        barDataset.addValue(100.00, expense, creditcard);
        barDataset.addValue(350.00, income, pay);

        JFreeChart barChart = ChartFactory.createBarChart(
                "Finance Tracker",
                "Category",
                "Amount",
                barDataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        return barChart;
    }

}
