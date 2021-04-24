package com.agreement_api.services;

import com.agreement_api.models.service.Product;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    List<Product> findProducts(String dirPath) throws IOException;

    Product findProduct(String filePath) throws IOException;
}
