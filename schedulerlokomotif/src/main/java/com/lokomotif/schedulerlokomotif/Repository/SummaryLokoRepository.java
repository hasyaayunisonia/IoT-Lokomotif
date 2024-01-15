package com.lokomotif.schedulerlokomotif.Repository;


import com.lokomotif.schedulerlokomotif.Model.SummaryLoko;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SummaryLokoRepository extends JpaRepository<SummaryLoko, Long> {
    // Tambahan metode khusus jika diperlukan
    // Tambahkan metode untuk mengambil data terbaru ke terlama
    List<SummaryLoko> findAllByOrderByTimestampsDesc();
}
