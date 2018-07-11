package com.gtdollar.gtbapi;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface GTBBlockRepository extends MongoRepository<GTBBlock, BigInteger> {
    Optional<GTBBlock> findFirstByOrderByCreatedDateDesc();
}
