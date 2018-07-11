package com.gtdollar.gtbapi;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.Transaction;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Component
@CommonsLog
public class SyncBlock {
    private final Web3j web3j;
    private final GTBTransactionRepository transactionRepository;
    private final GTBBlockRepository blockRepository;

    @Autowired
    public SyncBlock(Web3j web3j, GTBTransactionRepository transactionRepository, GTBBlockRepository blockRepository) {
        this.web3j = web3j;
        this.transactionRepository = transactionRepository;
        this.blockRepository = blockRepository;
    }

    @Scheduled(fixedDelay = 15 * 1000)
    public void syncBlock() throws IOException {
        BigInteger ethBlockNumber = web3j.ethBlockNumber().send().getBlockNumber();

        BigInteger dbBlockNumber = blockRepository.findFirstByOrderByCreatedDateDesc()
                .map(GTBBlock::getBlockNumber)
                .orElse(BigInteger.ZERO.subtract(BigInteger.ONE));

        for (BigInteger blockNumber = dbBlockNumber.add(BigInteger.ONE); blockNumber.compareTo(ethBlockNumber) <= 0; blockNumber = blockNumber.add(BigInteger.ONE)) {
            log.info(blockNumber);
            blockRepository.save(new GTBBlock(blockNumber));

            EthBlock ethBlock = web3j.ethGetBlockByNumber(DefaultBlockParameter.valueOf(blockNumber), true).send();
            EthBlock.Block block = ethBlock.getBlock();
            BigInteger timestamp = block.getTimestamp();
            List<GTBTransaction> transactions = block.getTransactions()
                    .stream()
                    .map(EthBlock.TransactionResult::get)
                    .map(Transaction.class::cast)
                    .map(t -> new GTBTransaction(t, timestamp))
                    .collect(Collectors.toList());
            transactionRepository.saveAll(transactions);
        }
    }
}
