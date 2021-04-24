package com.agreement_api.services;

import com.agreement_api.models.service.Product;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

public interface RegisterService {

    /* Infrastructure */
    void createRegisterInfrastructure(String agreementFilePath, String dirPath) throws IOException;

    /* Agreement register */
    String getAgreementRegister(String dirPath);
    void createAgreementRegister(String agreementFilePath, String dirPath) throws IOException;

    /* Products register */
    List<String> getProductsRegister(String dirPath) throws IOException;
    void createProductsRegister(String dirPath) throws IOException;
    void addToProductsRegister(String filePath, String dirPath) throws IOException, URISyntaxException;
    void signProductToProductRegister(Product product) throws IOException, URISyntaxException;

    /* Mapping register */
    Map<String, String> getMappingRegister(String dirPath) throws IOException;
    void createMappingRegister(String dirPath) throws IOException;
    void addToMappingRegister(String fileId, String filePath, String dirPath) throws IOException, URISyntaxException;
    void signProductToMappingRegister(Product product) throws IOException, URISyntaxException;
}
