package com.agreement_api.services.impl;

import com.agreement_api.constants.GlobalConstants;
import com.agreement_api.models.service.Product;
import com.agreement_api.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final RegisterService registerService;
    private final FileService fileService;
    private final AgreementService agreementService;

    @Autowired
    public ProductServiceImpl(RegisterService registerService, FileService fileService, AgreementService agreementService) {
        this.registerService = registerService;
        this.fileService = fileService;
        this.agreementService = agreementService;
    }

    @Override
    public List<Product> findFirstLevelProducts(String dirPath) throws IOException {


        /* Read products register to get paths */
        List<String> productsRegisterLines = this.registerService.getProductsRegister(dirPath);

        /* Read mapping register to get register paths */
        Map<String, String> mappingRegister = this.registerService.getMappingRegister(dirPath);

        /* Read every Product file by path */
        List<Product> products = new ArrayList<>();

        /* Map all products */
        for (String productFilePath : productsRegisterLines) {

            /* Get all lines from product file */
            List<String> productFileLines = this.fileService.readFileToLines(productFilePath);

            /* Map to Product */
            Product product = convertFileLinesToProductObject(productFileLines);
            products.add(product);
        }

        /**
         * Attach nested products to first level products
         * who are directly in the Agreement product collection
         **/
        String agreementId = this.agreementService.getAgreementID(dirPath);
        for (Product product : products) {
            String parentId = product.getParent().getId();

            if(!parentId.equals(agreementId)) {
                Product parentProduct = products
                        .stream()
                        .filter(p -> p.getId().equals(parentId))
                        .collect(Collectors.toList()).get(0);

                parentProduct.addProduct(product);
            }
        }

        /**
         *  First level products are those that attend directly in the Agreement products collection
         **/
        List<Product> productsFirstLevel = products
                .stream()
                .filter(p->p.getParent().getId().equals(agreementId))
                .collect(Collectors.toList());

        return productsFirstLevel;
    }

    @Override
    public Product findProduct(String filePath) throws IOException {
        List<String> productFileLines = this.fileService.readFileToLines(filePath);
        Product product = convertFileLinesToProductObject(productFileLines);
        return product;
    }


    private Product convertFileLinesToProductObject(List<String> productFileLines) {
        Product product = new Product();
        for (String line : productFileLines) {

            List<String> tokens = Arrays.stream(line.split(":")).map(String::trim).collect(Collectors.toList());
            product.setProducts(new ArrayList<>());

            switch (tokens.get(0)) {

                case GlobalConstants.PRODUCT_ID:
                    product.setId(tokens.get(1));
                    break;

                case GlobalConstants.PARENT_ID:
                    product.setParent(new Product(tokens.get(1)));
                    break;

                case GlobalConstants.PRODUCT_NAME:
                    product.setName(tokens.get(1));
                    break;

                case GlobalConstants.PRODUCT_PRICE:
                    product.setPrice(new BigDecimal(tokens.get(1)));
                    break;
            }
        }
        return product;
    }
}
