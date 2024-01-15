package com.lokomotif.schedulerlokomotif.Service;

import com.lokomotif.schedulerlokomotif.Model.LokoDummy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class LokoApiService {

    private final String apiUrl = "http://localhost:8080/api/receive-data"; // Ganti dengan URL API sesuai kebutuhan

    private final RestTemplate restTemplate;

    @Autowired
    public LokoApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void callAPI(List<LokoDummy> lokoDummyList) {

        // Membuat header untuk permintaan POST
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); // Sesuaikan dengan jenis media yang Anda kirim

        // Membuat entitas HTTP dengan data InfoLokomotif yang akan dikirimkan
        HttpEntity<List<LokoDummy>> requestEntity = new HttpEntity<>(lokoDummyList, headers);

        // Melakukan permintaan POST ke API
        String response = restTemplate.postForObject(apiUrl, requestEntity, String.class);
    }
}
