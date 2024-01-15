package com.lokomotif.schedulerlokomotif.Scheduler;

import com.lokomotif.schedulerlokomotif.Service.SummaryLokoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SummaryScheduler {
    @Autowired
    private SummaryLokoService summaryLokoService;

   @Scheduled(fixedRate = 60000) // Atur sesuai dengan kebutuhan jadwal Anda
   public void generateSchedulerReport() {
       try {
           // Hitung summary dan simpan ke PostgreSQL dan push Telegram
           summaryLokoService.createSummaryLoko();
       } catch (Exception e) {
           // Tangani kesalahan
           e.printStackTrace();
       }
   }
}
