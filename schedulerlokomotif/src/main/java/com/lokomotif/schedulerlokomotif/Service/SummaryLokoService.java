package com.lokomotif.schedulerlokomotif.Service;

import com.lokomotif.schedulerlokomotif.Model.Loko;
import com.lokomotif.schedulerlokomotif.Model.SummaryLoko;
import com.lokomotif.schedulerlokomotif.Repository.LokoRepository;
import com.lokomotif.schedulerlokomotif.Repository.SummaryLokoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class SummaryLokoService {
    @Autowired
    private SummaryLokoRepository summaryLokoRepository;

    @Autowired
    private LokoRepository lokoRepository;

    @Autowired
    private TelegramBotService telegramBotService;

    private static final Logger logger = LoggerFactory.getLogger(SummaryLokoService.class);

    public void createSummaryLoko() {
        try {
            List<Loko> lokoData = lokoRepository.findAll();

            int totalAktif = 0;
            int totalNonaktif = 0;
            int totalMaintenance = 0;

            for (Loko loko : lokoData) {
                if ("Aktif".equals(loko.getStatus())) {
                    totalAktif++;
                } else if ("Nonaktif".equals(loko.getStatus())) {
                    totalNonaktif++;
                } else if ("Maintenance".equals(loko.getStatus())) {
                    totalMaintenance++;
                }
            }

            SummaryLoko summaryLoko = new SummaryLoko();
            summaryLoko.setId(UUID.randomUUID().toString());
            summaryLoko.setTimestamps(LocalDateTime.now());
            summaryLoko.setTotalLoko(lokoData.size());
            summaryLoko.setTotalAktif(totalAktif);
            summaryLoko.setTotalNonaktif(totalNonaktif);
            summaryLoko.setTotalMaintenance(totalMaintenance);

            summaryLokoRepository.save(summaryLoko);

//            System.out.println("Data transfer completed.");
            logger.info("Successfully data transfer to PostgreSQL completed.");
            // Mengirim notifikasi ke Telegram setelah data disimpan
            String message = "Data transfer completed. Total Loko: " + summaryLoko.getTotalLoko() + " Date and Time: " + summaryLoko.getTimestamps();
            telegramBotService.sendTelegramMessage(message);
            logger.info("Successfully send summary report to Telegram.");
        } catch (Exception e) {
            e.printStackTrace(); // Gantilah dengan penanganan yang sesuai seperti log atau pesan kesalahan yang relevan.
        }
    }

    public List<SummaryLoko> getAllSummaryLoko() {
        // Mengambil semua data SummaryLoko dari PostgreSQL
        return summaryLokoRepository.findAll();
    }

    public List<SummaryLoko> getAllSummaryLokoLatest() {
        // Mengambil semua data SummaryLoko dari PostgreSQL dengan urutan terbaru ke terlama
        return summaryLokoRepository.findAllByOrderByTimestampsDesc();
    }
}