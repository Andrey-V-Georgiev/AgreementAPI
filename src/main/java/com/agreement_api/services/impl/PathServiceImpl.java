package com.agreement_api.services.impl;

import com.agreement_api.constants.GlobalConstants;
import com.agreement_api.models.service.Agreement;
import com.agreement_api.models.service.Product;
import com.agreement_api.services.PathService;
import org.springframework.stereotype.Service;

@Service
public class PathServiceImpl implements PathService {
    @Override
    public String generateDirPath(Agreement agreement) {
        return String.format("%s\\%s", GlobalConstants.ROOT_FOLDER_PATH, agreement.getId());
    }

    @Override
    public String generateDirPath(String agreementFolderName) {
        return String.format("%s\\%s", GlobalConstants.ROOT_FOLDER_PATH, agreementFolderName);
    }

    @Override
    public String generateAgreementFilePath(Agreement agreement) {
        return  String.format("%s\\%s.txt", this.generateDirPath(agreement), agreement.getName());
    }

    @Override
    public String generateProductFilePath(Product product) {
        /* First is parent id separated by space and second is product id */
        String fileNameProduct = String.format("%s %s", product.getParent().getId(), product.getId());
        return  String.format("%s\\%s.txt", product.getDirPath(), fileNameProduct);
    }
}