package com.agreement_api.services.impl;

import com.agreement_api.constants.GlobalConstants;
import com.agreement_api.models.service.Product;
import com.agreement_api.services.FileService;
import com.agreement_api.services.PathService;
import com.agreement_api.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class RegisterServiceImpl implements RegisterService {

    private final FileService fileService;
    private final PathService pathService;

    @Autowired
    public RegisterServiceImpl(FileService fileService, PathService pathService) {
        this.fileService = fileService;
        this.pathService = pathService;
    }

    /* Infrastructure */

    @Override
    public void createRootFolderRegister() throws IOException {
        String fullPath = String.format("%s\\%s", GlobalConstants.ROOT_FOLDER_PATH, GlobalConstants.REGISTER_ROOT_FOLDER_TXT);
        this.fileService.writeFile(GlobalConstants.BLANK, fullPath);
    }

    @Override
    public void createRegisterInfrastructure(String agreementFilePath, String dirPath) throws IOException {
        this.createAgreementRegister(agreementFilePath, dirPath);
        this.createProductsRegister(dirPath);
    }

    /* Agreement register */

    @Override
    public String getAgreementRegister(String dirPath) {
        return null;
    }

    @Override
    public void createAgreementRegister(String agreementFilePath, String dirPath) throws IOException {
        String fullPath = String.format("%s\\%s", dirPath, GlobalConstants.REGISTER_AGREEMENT_TXT);
        this.fileService.writeFile(agreementFilePath, fullPath);
    }

    @Override
    public void signAgreementRecordToRootRegister(String agreementFolderName) throws IOException, URISyntaxException {
        String fullPath = String.format("%s\\%s", GlobalConstants.ROOT_FOLDER_PATH, GlobalConstants.REGISTER_ROOT_FOLDER_TXT);
        this.fileService.addLineToFile(agreementFolderName, fullPath);
    }

    /* Products register */

    @Override
    public List<String> getProductsRegister(String dirPath) throws IOException {
        String productsRegisterPath = String.format("%s\\%s", dirPath, GlobalConstants.REGISTER_PRODUCTS_TXT);
        List<String> productsRegisterLines = this.fileService.readFileToLines(productsRegisterPath);
        return productsRegisterLines;
    }

    @Override
    public void createProductsRegister(String dirPath) throws IOException {
        String fullPath = String.format("%s\\%s", dirPath, GlobalConstants.REGISTER_PRODUCTS_TXT);
        this.fileService.writeFile(GlobalConstants.BLANK, fullPath);
    }

    @Override
    public void addToProductsRegister(String filePath, String dirPath) throws IOException, URISyntaxException {
        String productRegisterPath = String.format("%s\\%s", dirPath, GlobalConstants.REGISTER_PRODUCTS_TXT);
        this.fileService.addLineToFile(filePath, productRegisterPath);
    }

    @Override
    public void signProductToProductRegister(Product product) throws IOException, URISyntaxException {
        String productFilePath = this.pathService.generateProductFilePath(product);
        this.addToProductsRegister(productFilePath, product.getDirPath());
    }
}
