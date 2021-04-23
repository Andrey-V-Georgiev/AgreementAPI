package com.agreement_api.web.rest_controllers;

import com.google.gson.Gson;
import com.agreement_api.models.binding.AgreementBindingModel;
import com.agreement_api.services.AgreementService;
import com.agreement_api.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/agreement")
public class AppController {

    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final AgreementService appService;

    @Autowired
    public AppController(Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil, AgreementService appService) {
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.appService = appService;
    }

    @PostMapping("/store")
    public ResponseEntity<Object> storeInput(@RequestBody String inputJson) throws IOException {

        AgreementBindingModel agreementBindingModel = this.gson.fromJson(inputJson, AgreementBindingModel.class);
        if (this.validationUtil.isValid(agreementBindingModel)) {

            this.appService.storeInput(agreementBindingModel);

            return ResponseEntity.ok(""); // add json with the file path
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/as-text/{file-path}")
    public String findAgreementByFilePath(@PathVariable("file-path") String filePath) {
        System.out.println("HIT GET");
        return "";
    }
}
