package com.lokomotif.schedulerlokomotif.Repository;


import com.lokomotif.schedulerlokomotif.Model.Loko;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LokoRepository extends MongoRepository<Loko, String> {
    // Tambahan metode khusus jika diperlukan
}
