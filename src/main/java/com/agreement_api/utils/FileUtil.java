package com.agreement_api.utils;

import java.io.IOException;

public interface FileUtil {

    String readFile(String filePath) throws IOException;

    void writeFile(String content, String filePath) throws IOException;
}
