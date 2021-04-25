package com.agreement_api.services;

import com.agreement_api.models.service.Product;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface RegisterService {

    /* Infrastructure */
    void createRootFolderRegister() throws IOException;
    void createRegisterInfrastructure(String agreementFilePath, String dirPath) throws IOException;

    /* Agreement register */
    String getAgreementRegister(String dirPath);
    void createAgreementRegister(String agreementFilePath, String dirPath) throws IOException;
    void signAgreementRecordToRootRegister(String agreementFolderName) throws IOException, URISyntaxException;

    /* Products register */
    List<String> getProductsRegister(String dirPath) throws IOException;
    void createProductsRegister(String dirPath) throws IOException;
    void addToProductsRegister(String filePath, String dirPath) throws IOException, URISyntaxException;
    void signProductToProductRegister(Product product) throws IOException, URISyntaxException;
}
