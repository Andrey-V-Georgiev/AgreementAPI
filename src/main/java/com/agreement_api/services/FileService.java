package com.agreement_api.services;

import com.agreement_api.models.service.Product;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface FileService {

    List<String> readFileToLines(String filePath) throws IOException;

    void writeFile(String content, String filePath) throws IOException;

    boolean createDir(String dirPath);

    void writeProductToFile(Product product) throws IOException;

    void addLineToFile(String rowContent, String filePath) throws IOException, URISyntaxException;
}
