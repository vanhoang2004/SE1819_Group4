package com.example.demo.util;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@Service
public class ChartService {
    public String generatePieChart(int a, int b, int c) throws IOException {

        // chứa dữ liệu cho biểu đồ
        DefaultPieDataset dataset = new DefaultPieDataset();

        dataset.setValue("Under 5: \n"+a, a);
        dataset.setValue("From 5-8: \n"+b, b);
        dataset.setValue("From 8-10: \n"+c, c);

        JFreeChart pieChart = ChartFactory.createPieChart(
                "Sample Pie Chart",
                dataset,
                false, // hiển thi chú giải
                true, // hiển thị tooltips
                false  // không tạo url cho biểu đồ
        );

        // Thiết lập màu nền cho biểu đồ là màu trắng
        pieChart.setBackgroundPaint(Color.WHITE);
        pieChart.setBorderPaint(Color.WHITE);

        // chỉnh biểu đồ
        PiePlot plot = (PiePlot) pieChart.getPlot();
        // SectionPaint để thiết lập màu cho biểu đồ
        plot.setBackgroundPaint(Color.WHITE);
        plot.setSectionPaint("Under 5", new Color(255, 100, 100));
        plot.setSectionPaint("From 5 - 8", new Color(100, 255, 100));
        plot.setSectionPaint("From 8-10", new Color(100, 100, 255));
        plot.setOutlinePaint(Color.WHITE);

        // tên biểu đồ
        pieChart.setTitle(new TextTitle("Student's Score", new Font("Serif", Font.BOLD, 18)));


        // tạo hình ảnh cho biểu đồ với kich thước 600x400 pixel
        BufferedImage chartImage = pieChart.createBufferedImage(600, 400);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(chartImage, "png", baos);
        byte[] imageBytes = baos.toByteArray();
        return Base64.getEncoder().encodeToString(imageBytes);
    }
}
