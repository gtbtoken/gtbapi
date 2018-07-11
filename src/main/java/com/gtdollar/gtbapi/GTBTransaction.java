package com.gtdollar.gtbapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.web3j.protocol.core.methods.response.Transaction;

import java.io.Serializable;
import java.math.BigInteger;

@Data
@Document(collection = "transaction")
public class GTBTransaction implements Serializable {
    @Id
    private String hash;

    private BigInteger block;
    private BigInteger timestamp;
    private String from;
    private String to;
    private BigInteger value;
    private String input;
    private BigInteger gas;
    private BigInteger gasPrice;

    public GTBTransaction(Transaction transaction, BigInteger timestamp) {
        this.hash = transaction.getHash();
        this.block = transaction.getBlockNumber();
        this.timestamp = timestamp;
        this.from = transaction.getFrom();
        this.to = transaction.getTo();
        this.value = transaction.getValue();
        this.input = transaction.getInput();
        this.gas = transaction.getGas();
        this.gasPrice = transaction.getGasPrice();
    }

    @JsonProperty("value")
    public String getDisplayValue() {
        return value.toString();
    }

    @JsonProperty("gas")
    public String getDisplayGas() {
        return gas.toString();
    }

    @JsonProperty("gasPrice")
    public String getDisplayGasPrice() {
        return String.valueOf(gasPrice);
    }
}
