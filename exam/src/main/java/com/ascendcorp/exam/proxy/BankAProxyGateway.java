package com.ascendcorp.exam.proxy;

import com.ascendcorp.exam.model.TransferRequest;
import com.ascendcorp.exam.model.TransferResponse;
import org.springframework.boot.web.server.WebServerException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("bank1")
@Primary
public class BankAProxyGateway implements BankProxyGateway {

    public TransferResponse requestTransfer(TransferRequest transferRequest) throws WebServerException {
        System.out.println("---BankAProxyGateway requestTransfer " + transferRequest);
        return new TransferResponse();
    }

}

