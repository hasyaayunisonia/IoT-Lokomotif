package com.lokomotif.schedulerlokomotif.Model;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Document(collection = "scheduler")
public class LokoDummy {
    @Id
    private UUID kodeLoko;
    private String namaLoko;
    private String dimensiLoko;
    private String status;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate tanggal;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime jam;

    @Override
    public String toString() {
        return "LokoDummy{" +
                "kodeLoko='" + kodeLoko + '\'' +
                ", namaLoko='" + namaLoko + '\'' +
                ", dimensiLoko='" + dimensiLoko + '\'' +
                ", status='" + status + '\'' +
                ", tanggal=" + tanggal +
                ", jam='" + jam + '\'' +
                '}';
    }
}
