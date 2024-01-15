package com.lokomotif.schedulerlokomotif.Controller;

import com.lokomotif.schedulerlokomotif.Model.SummaryLoko;
import com.lokomotif.schedulerlokomotif.Service.SummaryLokoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/summary-loko")
@CrossOrigin(origins = "http://localhost:5173")
public class SummaryLokoController {

    @Autowired
    private SummaryLokoService summaryLokoService;

    @GetMapping("/get-summary")
    public List<SummaryLoko> getSummaryLoko() {
        // Panggil method dari service yang mengambil data dari PostgreSQL
        return summaryLokoService.getAllSummaryLoko();
    }

    @GetMapping("/latest")
    public List<SummaryLoko> getAllSummaryLokoLatestFirst() {
        // Mengambil data SummaryLoko dari service dengan urutan terbaru ke terlama
        return summaryLokoService.getAllSummaryLokoLatest();
    }
}