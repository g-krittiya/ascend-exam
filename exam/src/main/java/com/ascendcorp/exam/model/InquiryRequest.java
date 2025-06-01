package com.ascendcorp.exam.model;

import com.ascendcorp.exam.exception.GeneralValidateException;
import lombok.Data;

import java.util.Date;

@Data
public class InquiryRequest {

    private String transactionId;
    private Date tranDateTime;
    private String channel;
    private String locationCode;
    private String bankCode;
    private String bankNumber;
    private double amount;
    private String reference1;
    private String reference2;
    private String firstName;
    private String lastName;

    public InquiryRequest() {

    }

    public InquiryRequest(String transactionId, Date tranDateTime, String channel, String locationCode, String bankCode, String bankNumber, double amount, String reference1, String reference2, String firstName, String lastName) {
        this.transactionId = transactionId;
        this.tranDateTime = tranDateTime;
        this.channel = channel;
        this.locationCode = locationCode;
        this.bankCode = bankCode;
        this.bankNumber = bankNumber;
        this.amount = amount;
        this.reference1 = reference1;
        this.reference2 = reference2;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public boolean validate() throws GeneralValidateException {
        if (this.transactionId == null) {
            throw new GeneralValidateException("Transaction id is required!");
        }
        if (this.getTranDateTime() == null) {
            throw new GeneralValidateException("Transaction DateTime is required!");
        }
        if (this.channel == null) {
            throw new GeneralValidateException("Channel is required!");
        }
        if (this.bankCode == null || this.bankCode.equalsIgnoreCase("")) {
            throw new GeneralValidateException("Bank Code is required!");
        }
        if (this.bankNumber == null || this.bankNumber.equalsIgnoreCase("")) {
            throw new GeneralValidateException("Bank Number is required!");
        }
        if (this.amount <= 0) {
            throw new GeneralValidateException("Amount must more than zero!");
        }
        return true;
    }

}
