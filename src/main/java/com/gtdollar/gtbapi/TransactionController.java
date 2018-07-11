package com.gtdollar.gtbapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TransactionController {
    private GTBTransactionRepository repository;

    public TransactionController(GTBTransactionRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/getAddressTransactions/{address}")
    public List<GTBTransaction> list(@PathVariable String address) {
        return repository.findByFromOrToAllIgnoreCaseOrderByTimestampDesc(address, address)
                .stream()
                .filter(t -> t.getTo() != null)
                .filter(t -> t.getValue().compareTo(BigInteger.ZERO) > 0)
                .collect(Collectors.toList());
    }
}
