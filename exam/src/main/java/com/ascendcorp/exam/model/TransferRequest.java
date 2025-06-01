package com.ascendcorp.exam.model;

import lombok.Data;

import java.util.Date;

@Data
public class TransferRequest {

    String transactionId;
    Date tranDateTime;
    String channel;
    String bankCode;
    String bankNumber;
    double amount;
    String reference1;
    String reference2;

    public TransferRequest() {

    }

    public TransferRequest(String transactionId, Date tranDateTime, String channel, String bankCode, String bankNumber, double amount, String reference1, String reference2) {
        this.transactionId = transactionId;
        this.tranDateTime = tranDateTime;
        this.channel = channel;
        this.bankCode = bankCode;
        this.bankNumber = bankNumber;
        this.amount = amount;
        this.reference1 = reference1;
        this.reference2 = reference2;
    }

}
