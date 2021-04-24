package com.agreement_api.services;

import com.agreement_api.models.service.Agreement;

import java.io.IOException;

public interface AgreementService {

    Agreement findAgreement(String dirPath) throws IOException;
}
