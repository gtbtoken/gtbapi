package com.gtdollar.gtbapi;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GTBTransactionRepository extends MongoRepository<GTBTransaction, String> {
    List<GTBTransaction> findByFromOrToAllIgnoreCaseOrderByTimestampDesc(String from, String to);
}
