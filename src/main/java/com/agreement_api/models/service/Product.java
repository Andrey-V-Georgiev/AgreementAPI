package com.agreement_api.models.service;

import com.agreement_api.constants.GlobalConstants;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Product extends Identity {

    private String name;
    private Identity parent;
    private List<Product> products;
    private BigDecimal price;

    public Product() {
    }

    public Product(String id, String dirPath, String name, List<Product> products, BigDecimal price) {
        super(id, dirPath);
        this.name = name;
        this.products = new ArrayList<>(products);
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Identity getParent() {
        return parent;
    }

    public void setParent(Identity parent) {
        this.parent = parent;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String toStringAgreement() {

        StringBuilder sb = new StringBuilder();

        /* Construct String representation of Product object */
        sb.append(String.format("%s: %s\n", GlobalConstants.NAME, this.name));
        sb.append(String.format("%s: %s\n", GlobalConstants.PRODUCT_PRICE, this.price));
        sb.append(GlobalConstants.SEPARATOR);

        String stringRepresentation = sb.toString();
        return stringRepresentation;
    }

    public String toStringProduct() {

        StringBuilder sb = new StringBuilder();

        /* Construct String representation of Product object */
        sb.append(String.format("%s: %s\n", GlobalConstants.ID, super.getId()));
        sb.append(String.format("%s: %s\n", GlobalConstants.PARENT_ID, this.parent.getId()));
        sb.append(String.format("%s: %s\n", GlobalConstants.NAME, this.name));
        sb.append(String.format("%s: %s\n", GlobalConstants.PRODUCT_PRICE, this.price));
        sb.append(GlobalConstants.SEPARATOR);

        String stringRepresentation = sb.toString();
        return stringRepresentation;
    }
}
