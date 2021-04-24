package com.agreement_api.services.impl;

import com.agreement_api.models.binding.AgreementBindingModel;
import com.agreement_api.models.service.Agreement;
import com.agreement_api.models.service.Identity;
import com.agreement_api.models.service.Path;
import com.agreement_api.models.service.Product;
import com.agreement_api.services.*;
import com.agreement_api.utils.IdUtil;
import com.agreement_api.utils.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class AppServiceImpl implements AppService {

    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final FileService fileService;
    private final IdUtil idUtil;
    private final PathService pathService;
    private final RegisterService registerService;
    private final Gson gson;
    private final AgreementService agreementService;
    private final ProductService productService;


    @Autowired
    public AppServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil, FileService fileService, IdUtil idUtil, PathService pathService, RegisterService registerService, Gson gson, AgreementService agreementService, ProductService productService) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.fileService = fileService;
        this.idUtil = idUtil;
        this.pathService = pathService;
        this.registerService = registerService;
        this.gson = gson;
        this.agreementService = agreementService;
        this.productService = productService;
    }

    @Override
    public String findAgreementAsJSON(String agreementFolderName) throws IOException {

        String dirPath = this.pathService.generateDirPath(agreementFolderName);

        /* Get Agreement object */
        Agreement agreement = this.agreementService.findAgreement(dirPath);
        System.out.println();

        /* Get Product objects */
        List<Product> products = this.productService.findProducts(dirPath);
        System.out.println();

        return null;
    }

    @Override
    public String storeInput(AgreementBindingModel agreementBindingModel) throws IOException, URISyntaxException {

        /* Map binding model to entity */
        Agreement agreement = this.modelMapper.map(agreementBindingModel, Agreement.class);

        /* Set agreement fields */
        this.setAgreementFields(agreement);

        /* Set Agreement products IDs for direct products */
        this.setAgreementProductsIds(agreement.getProducts());

        /* Check for agreement dir and if not exists create it */
        this.fileService.createDir(agreement.getDirPath());

        /* Store Agreement to file */
        String agreementFilePath = this.pathService.generateAgreementFilePath(agreement);
        this.fileService.writeFile(agreement.toString(), agreementFilePath);

        /* Create register infrastructure */
        this.registerService.createRegisterInfrastructure(agreementFilePath, agreement.getDirPath());

        /* Store products to file system as human readable files */
        this.storeProductsToFileSystem(agreement.getProducts(), 0, agreement);



        /* Construct JSON response */
        Path path = new Path(agreement.getId(), agreementFilePath);
        String pathJson = this.gson.toJson(path);
        return pathJson;
    }

    private void storeProductsToFileSystem(List<Product> products, int index, Identity parent) throws IOException, URISyntaxException {
        /* Bottom of the recursion */
        if (products.size() == index) {
            return;
        }
        /* Set fields and store to file */
        Product currentProduct = products.get(index);
        this.setProductFields(currentProduct, parent, parent.getDirPath());
        this.fileService.writeProductToFile(currentProduct);

        /* Sign product to mapping and product registers */
        this.registerService.signProductToProductRegister(currentProduct);
        this.registerService.signProductToMappingRegister(currentProduct);

        /* Get deeper */
        if (currentProduct.getProducts().size() > 0) {
            List<Product> nestedProducts = currentProduct.getProducts();
            Product nestedProduct = nestedProducts.get(0);
            this.setProductFields(nestedProduct, currentProduct, currentProduct.getDirPath());

            /* Recursive call */
            this.storeProductsToFileSystem(nestedProducts, 0, nestedProduct);
        }

        /* Call the function recursively */
        this.storeProductsToFileSystem(products, ++index, currentProduct);
    }

    private void setAgreementFields(Agreement agreement) throws IOException {
        agreement.setId(this.idUtil.generateID());
        agreement.setDirPath(this.pathService.generateDirPath(agreement));
        agreement.setName();
    }

    private void setAgreementProductsIds(List<Product> agreementProducts) {
        for (Product product : agreementProducts) {
            product.setId(this.idUtil.generateID());
        }
    }

    private void setProductFields(Product product, Identity parent, String dirPath) throws IOException {
        if(product.getId() == null) {
            /* Set IDs for nested products */
            product.setId(this.idUtil.generateID());
        }
        product.setDirPath(dirPath);
        product.setParent(parent);
    }
}
