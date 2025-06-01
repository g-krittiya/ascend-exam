package com.ascendcorp.exam.proxy;

import com.ascendcorp.exam.model.TransferRequest;
import com.ascendcorp.exam.model.TransferResponse;
import org.springframework.stereotype.Component;

@Component("bank2")
public class BankBProxyGateway implements BankProxyGateway {

    public TransferResponse requestTransfer(TransferRequest transferRequest) {
        System.out.println("---BankBProxyGateway requestTransfer " + transferRequest);
        return new TransferResponse();
    }

}

