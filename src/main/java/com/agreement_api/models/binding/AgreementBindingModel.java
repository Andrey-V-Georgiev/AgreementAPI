package com.agreement_api.models.binding;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class AgreementBindingModel {

    @Expose
    private String name;
    @Expose
    private String signBy;
    @Expose
    private List<ProductBindingModel> products;

    public AgreementBindingModel(String name, String signBy, List<ProductBindingModel> products) {
        this.name = name;
        this.signBy = signBy;
        this.products = products;
    }

    @NotBlank
    @Length(min = 2)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotBlank
    @Length(min = 2)
    public String getSignBy() {
        return signBy;
    }

    public void setSignBy(String signBy) {
        this.signBy = signBy;
    }

    public List<ProductBindingModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductBindingModel> products) {
        this.products = products;
    }
}
