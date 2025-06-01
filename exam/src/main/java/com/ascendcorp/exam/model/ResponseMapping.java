package com.ascendcorp.exam.model;

import lombok.Data;

@Data
public class ResponseMapping {

    private String responseCode;
    private String responseDescription;
    private String outputCode;
    private String outputDescription;

    public ResponseMapping() {

    }

    public ResponseMapping(String responseCode, String responseDescription, String outputCode, String outputDescription) {
        this.responseCode = responseCode;
        this.responseDescription = responseDescription;
        this.outputCode = outputCode;
        this.outputDescription = outputDescription;
    }

}
