package com.agreement_api.models.service;

import com.agreement_api.constants.GlobalConstants;
import com.google.gson.annotations.Expose;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Agreement extends Identity {

    @Expose
    private String name;
    @Expose
    private String signBy;
    @Expose
    private List<Product> products;

    public Agreement() {
    }

    public Agreement(String id, String dirPath, String name, String signBy, List<Product> products) {
        super(id, dirPath);
        this.name = name;
        this.signBy = signBy;
        this.products = new ArrayList<>(products);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setName() {
        LocalDateTime now = LocalDateTime.now();
        String dateFormatted = String.format("%s%s%s", now.getDayOfMonth(), now.getMonth(), now.getYear());
        this.name = String.format("Agreement %s", dateFormatted);
    }

    public String getSignBy() {
        return signBy;
    }

    public void setSignBy(String signBy) {
        this.signBy = signBy;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        /* Construct String representation of Agreement object */
        sb.append(String.format("%s: %s\n", GlobalConstants.AGREEMENT_ID, super.getId()));
        sb.append(String.format("%s: %s\n", GlobalConstants.AGREEMENT_NAME, this.name));
        sb.append(String.format("%s: %s\n", GlobalConstants.SIGNATURE_OWNER, this.signBy));
        sb.append(String.format("%s:\n", GlobalConstants.AGREEMENT_PRODUCTS));
        sb.append(GlobalConstants.SEPARATOR);
        if (this.products.size() > 0) {
            for (Product product : products) {
                sb.append(String.format("%s", product.toStringAgreement()));
            }
        } else {
            sb.append(GlobalConstants.NO_PRODUCTS);
            sb.append(GlobalConstants.SEPARATOR);
        }
        String stringRepresentation = sb.toString();
        return stringRepresentation;
    }
}
