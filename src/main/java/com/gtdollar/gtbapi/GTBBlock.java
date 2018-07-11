package com.gtdollar.gtbapi;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.Instant;

@Data
@Document(collection = "block")
public class GTBBlock implements Serializable {
    @Id
    private String id;

    private BigInteger blockNumber;

    private Instant createdDate = Instant.now();

    public GTBBlock(BigInteger blockNumber) {
        this.blockNumber = blockNumber;
    }
}
