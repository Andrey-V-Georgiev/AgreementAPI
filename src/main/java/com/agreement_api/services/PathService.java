package com.agreement_api.services;

import com.agreement_api.models.service.Agreement;
import com.agreement_api.models.service.Product;

public interface PathService {

    String generateDirPath(Agreement agreement);

    String generateDirPath(String agreementFolderName);

    String generateAgreementFilePath(Agreement agreement);

    String generateProductFilePath(Product product);
}
