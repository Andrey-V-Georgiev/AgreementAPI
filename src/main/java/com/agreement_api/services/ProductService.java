package com.agreement_api.services;

import com.agreement_api.models.service.Product;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    List<Product> findFirstLevelProducts(String dirPath) throws IOException;
}
