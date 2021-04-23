package com.agreement_api.utils.impl;

import com.agreement_api.utils.IdUtil;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class IdUtilImpl implements IdUtil {
    @Override
    public String generateID() {
        String id = UUID.randomUUID().toString();
        return id;
    }
}
