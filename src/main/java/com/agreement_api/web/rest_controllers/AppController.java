package com.agreement_api.web.rest_controllers;

import com.google.gson.Gson;
import com.agreement_api.models.binding.AgreementBindingModel;
import com.agreement_api.services.AppService;
import com.agreement_api.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/agreement")
public class AppController {

    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final AppService appService;

    @Autowired
    public AppController(Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil, AppService appService) {
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.appService = appService;
    }

    @GetMapping("/{agreement-folder-name}")
    public ResponseEntity<Object> findAgreementByFilePath(
            @PathVariable("agreement-folder-name") String agreementFolderName) throws IOException {

        String agreementJson = this.appService.findAgreementAsJSON(agreementFolderName);

        return ResponseEntity.ok(agreementJson);
    }

    @PostMapping("/store")
    public ResponseEntity<Object> storeInput(@RequestBody String inputJson) throws IOException, URISyntaxException {

        AgreementBindingModel agreementBindingModel = this.gson.fromJson(inputJson, AgreementBindingModel.class);
        if (this.validationUtil.isValid(agreementBindingModel)) {

            String pathJson = this.appService.storeInput(agreementBindingModel);
            return ResponseEntity.ok(pathJson);

        } else {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        }
    }

}
