package com.agreement_api.services;

import com.agreement_api.models.binding.AgreementBindingModel;

import java.io.IOException;
import java.net.URISyntaxException;

public interface AppService {

    String findAgreementAsJSON(String agreementFolderName) throws IOException;

    String storeInput(AgreementBindingModel agreementBindingModel) throws IOException, URISyntaxException;
}
