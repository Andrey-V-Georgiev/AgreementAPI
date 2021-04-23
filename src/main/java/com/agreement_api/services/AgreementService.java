package com.agreement_api.services;

import com.agreement_api.models.binding.AgreementBindingModel;

import java.io.IOException;

public interface AgreementService {
    void storeInput(AgreementBindingModel agreementBindingModel) throws IOException;
}
