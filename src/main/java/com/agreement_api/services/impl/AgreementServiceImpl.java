package com.agreement_api.services.impl;

import com.agreement_api.models.binding.AgreementBindingModel;
import com.agreement_api.models.service.Agreement;
import com.agreement_api.models.service.Identity;
import com.agreement_api.models.service.Product;
import com.agreement_api.services.AgreementService;
import com.agreement_api.utils.FileUtil;
import com.agreement_api.utils.IdUtil;
import com.agreement_api.utils.PathUtil;
import com.agreement_api.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class AgreementServiceImpl implements AgreementService {

    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final FileUtil fileUtil;
    private final IdUtil idUtil;
    private final PathUtil pathUtil;

    @Autowired
    public AgreementServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil, FileUtil fileUtil, IdUtil idUtil, PathUtil pathUtil) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.fileUtil = fileUtil;
        this.idUtil = idUtil;
        this.pathUtil = pathUtil;
    }

    @Override
    public void storeInput(AgreementBindingModel agreementBindingModel) throws IOException {

        /* Map dtos to entities and set IDs */
        Agreement agreement = this.modelMapper.map(agreementBindingModel, Agreement.class);

        /* Set agreement */
        agreement.setId(this.idUtil.generateID());
        agreement.setDirPath(this.pathUtil.generateDirPath(agreement));
        agreement.setName();

        /* Check for dir and if not exists create it */
        File dir = new File(agreement.getDirPath());
        if (!dir.exists()) {
            boolean success = dir.mkdirs();
        }

        /* Write Agreement to files */
        String filePath = this.pathUtil.generateAgreementFilePath(agreement);
        this.fileUtil.writeFile(agreement.toString(), filePath);

        this.iterateOverList(agreement.getProducts(), 0, agreement);
    }

    private void iterateOverList(List<Product> products, int index, Identity parent) throws IOException {
        /* Bottom of the recursion */
        if (products.size() == index) {
            return;
        }
        /* Set fields and store to file */
        Product currentProduct = products.get(index);
        this.setProductFields(currentProduct, parent, parent.getDirPath());
        this.writeProductToFile(currentProduct);

        /* Get deeper */
        if (currentProduct.getProducts().size() > 0) {
            List<Product> nestedProducts = currentProduct.getProducts();
            Product nestedProduct = nestedProducts.get(0);
            this.setProductFields(nestedProduct, currentProduct, currentProduct.getDirPath());
            this.iterateOverList(nestedProducts, 0, nestedProduct);
        }

        /* Call the function recursively */
        this.iterateOverList(products, ++index, currentProduct);
    }

    private void setProductFields(Product product, Identity parent, String dirPath) throws IOException {
        product.setId(this.idUtil.generateID());
        product.setDirPath(dirPath);
        product.setParent(parent);
    }

    private void writeProductToFile(Product product) throws IOException {
        String filePath = this.pathUtil.generateProductFilePath(product);
        this.fileUtil.writeFile(product.toStringProduct(), filePath);
    }
}
