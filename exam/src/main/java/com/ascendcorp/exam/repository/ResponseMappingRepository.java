package com.ascendcorp.exam.repository;

import com.ascendcorp.exam.model.ResponseMapping;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ResponseMappingRepository {

    private static List<ResponseMapping> responseMappings;

    public List<ResponseMapping> getAllResponseMapping() {
        if (responseMappings == null) {
            responseMappings = new ArrayList<>();
            // get from database
        } // read from cache

        /* Assume that data will get from database
        responseMappings.add(new ResponseMapping("approved","approved"
                ,"200", "approved"));
        responseMappings.add(new ResponseMapping("invalid_data","100:1091:Data type is invalid."
                ,"1091", "Data type is invalid."));
        responseMappings.add(new ResponseMapping("invalid_data","General error."
                ,"400", "General Invalid Data"));
        responseMappings.add(new ResponseMapping("invalid_data",null
                ,"400", "General Invalid Data"));

        responseMappings.add(new ResponseMapping("transaction_error",null
                ,"500", "General Transaction Error"));
        responseMappings.add(new ResponseMapping("transaction_error","Transaction error."
                ,"500", "General Transaction Error"));
        responseMappings.add(new ResponseMapping("transaction_error","100:1091:Transaction is error with code 1091."
                ,"1091", "Transaction is error with code 1091."));
        responseMappings.add(new ResponseMapping("transaction_error","1092:Transaction is error with code 1092."
                ,"1092", "Transaction is error with code 1092."));
        responseMappings.add(new ResponseMapping("transaction_error","98:Transaction is error with code 98."
                ,"98", "Transaction is error with code 98."));

        responseMappings.add(new ResponseMapping("unknown",null
                ,"501", "General Invalid Data"));
        responseMappings.add(new ResponseMapping("unknown","5001:Unknown error code 5001"
                ,"5001", "Unknown error code 5001"));
        responseMappings.add(new ResponseMapping("unknown","5002:"
                ,"5002", "General Invalid Data"));
        responseMappings.add(new ResponseMapping("unknown","General Invalid Data code 501"
                ,"501", "General Invalid Data"));

        responseMappings.add(new ResponseMapping("not_support","Not Support"
                ,"504", "Internal Application Error"));
         */
        return responseMappings;
    }

}
