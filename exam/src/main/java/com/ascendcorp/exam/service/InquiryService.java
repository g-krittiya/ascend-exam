package com.ascendcorp.exam.service;

import com.ascendcorp.exam.exception.GeneralValidateException;
import com.ascendcorp.exam.model.*;
import com.ascendcorp.exam.proxy.BankProxyGateway;
import com.ascendcorp.exam.repository.ResponseMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.WebServerException;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;

import java.util.List;

@Service
public class InquiryService {

    @Autowired
    private BankProxyGateway bankProxyGateway;

    @Autowired
    private ResponseMappingRepository responseMappingRepository;


    final static Logger log = Logger.getLogger(InquiryService.class);

    public InquiryServiceResultDTO inquiry(InquiryRequest inquiryRequest) {

        InquiryServiceResultDTO respDTO;
        try {
            log.info("validate request parameters.");
            if (inquiryRequest.validate()) {
                log.info("request parameters are valid");
            }

            log.info("call bank web service");
            TransferRequest transferRequest = new TransferRequest(inquiryRequest.getTransactionId(), inquiryRequest.getTranDateTime(), inquiryRequest.getChannel(),
                    inquiryRequest.getBankCode(), inquiryRequest.getBankNumber(), inquiryRequest.getAmount(), inquiryRequest.getReference1(), inquiryRequest.getReference2());
            TransferResponse response = bankProxyGateway.requestTransfer(transferRequest);

            log.info("check bank response code");
            if (response != null) {
                log.debug("found response code");
                // map transfer response to result
                respDTO = responseMapping(response);
            } else {
                // no report from bank
                throw new Exception("Unable to inquiry from service.");
            }
        } catch (GeneralValidateException ne) {
            respDTO = new InquiryServiceResultDTO();
            respDTO.setReasonCode("500");
            respDTO.setReasonDesc("General Invalid Data");

        } catch (WebServerException r) {
            // handle error from bank web service
            String faultString = r.getMessage();
            respDTO = new InquiryServiceResultDTO();
            if (faultString != null &&
                    (faultString.contains("java.net.SocketTimeoutException")
                            || faultString.contains("Connection timed out"))) {
                // bank timeout
                respDTO.setReasonCode("503");
                respDTO.setReasonDesc("Error timeout");
            } else {
                // bank general error
                respDTO.setReasonCode("504");
                respDTO.setReasonDesc("Internal Application Error");
            }
        } catch (Exception e) {
            log.error("inquiry exception", e);
            respDTO = new InquiryServiceResultDTO();
            respDTO.setReasonCode("504");
            respDTO.setReasonDesc("Internal Application Error");
        }
        return respDTO;
    }

    private InquiryServiceResultDTO responseMapping(TransferResponse response) throws Exception {
        InquiryServiceResultDTO respDTO = new InquiryServiceResultDTO();
        String responseCode = response.getResponseCode();
        String responseDesc = response.getDescription();
        List<ResponseMapping> resMappings = responseMappingRepository.getAllResponseMapping();
        ResponseMapping out = null;

        for (ResponseMapping mapping : resMappings) {
            if (mapping.getResponseCode().trim().equalsIgnoreCase(responseCode.trim())) {
                if (mapping.getResponseDescription() == null && responseDesc == null) {
                    out = mapping;
                    break;
                } else if (responseDesc != null && mapping.getResponseDescription() != null
                        && mapping.getResponseDescription().trim().equalsIgnoreCase(responseDesc.trim())) {
                    out = mapping;
                    break;
                }
            }
        }

        if (out != null) {
            respDTO.setReasonCode(out.getOutputCode());
            respDTO.setReasonDesc(out.getOutputDescription());
            respDTO.setRef_no1(response.getReferenceCode1());
            respDTO.setRef_no2(response.getReferenceCode2());
            respDTO.setAmount(response.getBalance());
            respDTO.setTranID(response.getBankTransactionID());
        } else {
            throw new Exception("Unable to map inquiry response.");
        }

        return respDTO;
    }
}
