package com.example.demo.controller;

import com.example.demo.data.ChapterRepository;
import com.example.demo.data.SubjectRepository;
import com.example.demo.data.TeacherRepository;
import com.example.demo.entity.Chapter;
import com.example.demo.service.ReportService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.util.List;

@RestController
public class ReportRestController {

    @Autowired
    private ReportService reportService;
    TeacherRepository tr;
    ChapterRepository cr;

    public ReportRestController(TeacherRepository tr, ChapterRepository cr) {
        this.tr = tr;
        this.cr = cr;
    }

    @GetMapping("/export/{classcode}/{mocktestid}")
    public void generateExcelReport(HttpServletResponse response, @PathVariable int classcode, @PathVariable int mocktestid) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=score.xlsx";
        response.setHeader(headerKey, headerValue);

        reportService.generateExcel(response, classcode, mocktestid);
    }

    @GetMapping("/template")
    public void generateExcelTemplate(HttpServletResponse response) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        int subjectid = tr.findSubjectIdByUsername(name);

        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=template.xlsx";
        response.setHeader(headerKey, headerValue);

        reportService.generateTemplate(response, subjectid);
    }
}
