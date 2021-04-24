package com.agreement_api.services.impl;

import com.agreement_api.constants.GlobalConstants;
import com.agreement_api.models.service.Agreement;
import com.agreement_api.models.service.Product;
import com.agreement_api.services.AgreementService;
import com.agreement_api.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgreementServiceImpl implements AgreementService {

    private final FileService fileService;

    @Autowired
    public AgreementServiceImpl(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public Agreement findAgreement(String dirPath) throws IOException {

        /* Read Agreement register to get register path */
        String agreementRegisterPath = String.format("%s\\%s", dirPath, GlobalConstants.REGISTER_AGREEMENT_TXT);
        List<String> agreementRegisterLines = this.fileService.readFileToLines(agreementRegisterPath);
        String agreementFilePath = agreementRegisterLines.get(0);

        /* Read Agreement file by path, on by one */
        List<String> agreementFileLines = this.fileService.readFileToLines(agreementFilePath);

        /* Convert the file info to object in AgreementService */
        Agreement agreement = convertFileLinesToAgreementObject(agreementFileLines);
        return agreement;
    }

    private Agreement convertFileLinesToAgreementObject(List<String> agreementFileLines) {
        Agreement agreement = new Agreement();

        for (String line : agreementFileLines) {
            List<String> tokens = Arrays.stream(line.split(":")).map(String::trim).collect(Collectors.toList());
            switch (tokens.get(0)) {

                case GlobalConstants.AGREEMENT_ID:
                    agreement.setId(tokens.get(1));
                    break;

                case GlobalConstants.AGREEMENT_NAME:
                    agreement.setName(tokens.get(1));
                    break;

                case GlobalConstants.SIGNATURE_OWNER:
                    agreement.setSignBy(tokens.get(1));
                    break;

                case GlobalConstants.AGREEMENT_PRODUCTS:
                    agreement.setProducts(new ArrayList<>());
                    break;

                case GlobalConstants.PRODUCT_ID:
                    agreement.addProduct(new Product(tokens.get(1)));
                    break;
            }
        }
        return agreement;
    }
}
