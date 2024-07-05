package com.example.demo.controller;

import com.example.demo.service.ReportService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ReportRestController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/export/{classcode}/{mocktestid}")
    public void generateExcelReport(HttpServletResponse response, @PathVariable int classcode, @PathVariable int mocktestid) throws IOException {
        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=score.xls";
        response.setHeader(headerKey, headerValue);

        reportService.generateExcel(response, classcode, mocktestid);

    }

}
