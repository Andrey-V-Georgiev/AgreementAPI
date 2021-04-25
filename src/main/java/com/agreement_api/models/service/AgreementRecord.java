package com.agreement_api.models.service;

import com.google.gson.annotations.Expose;

public class AgreementRecord {

    @Expose
    private String agreementFolderName;
    @Expose
    private String fullPath;

    public AgreementRecord() {
    }

    public AgreementRecord(String agreementFolderName, String fullPath) {
        this.agreementFolderName = agreementFolderName;
        this.fullPath = fullPath;
    }

    public String getAgreementFolderName() {
        return agreementFolderName;
    }

    public void setAgreementFolderName(String agreementFolderName) {
        this.agreementFolderName = agreementFolderName;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }
}
