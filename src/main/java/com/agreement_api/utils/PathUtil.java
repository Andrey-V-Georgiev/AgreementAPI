package com.agreement_api.utils;

import com.agreement_api.models.service.Agreement;
import com.agreement_api.models.service.Product;

public interface PathUtil {

    String generateDirPath(Agreement agreement);

    String generateAgreementFilePath(Agreement agreement);

    String generateProductFilePath(Product product);
}
