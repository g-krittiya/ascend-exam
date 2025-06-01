package com.ascendcorp.exam.proxy;

import com.ascendcorp.exam.model.TransferRequest;
import com.ascendcorp.exam.model.TransferResponse;

public interface BankProxyGateway {

    TransferResponse requestTransfer(TransferRequest transferRequest);

}
