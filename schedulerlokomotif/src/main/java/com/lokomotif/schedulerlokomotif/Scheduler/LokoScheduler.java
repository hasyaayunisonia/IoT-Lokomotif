package com.lokomotif.schedulerlokomotif.Scheduler;

import com.lokomotif.schedulerlokomotif.Model.LokoDummy;
import com.lokomotif.schedulerlokomotif.Service.LokoApiService;
import com.lokomotif.schedulerlokomotif.Service.LokoDummyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LokoScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(LokoScheduler.class);

    private final LokoApiService lokoApiService;
    private final LokoDummyService lokoDummyService;

    @Autowired
    public LokoScheduler(LokoApiService lokoApiService, LokoDummyService lokoDummyService) {
        this.lokoApiService = lokoApiService;
        this.lokoDummyService = lokoDummyService;
    }

   @Scheduled(fixedRate = 10000) // 10000 milidetik = 10 detik
   public void generateAndPrintDummyData() {
       List<LokoDummy> dummyDataList = lokoDummyService.generateDummyData();

       lokoApiService.callAPI(dummyDataList);

       for (LokoDummy apiData : dummyDataList) {
           LOGGER.info("API Data: {}", apiData.toString());
       }
   }
}
