package com.agreement_api.models.service;


import com.google.gson.annotations.Expose;

public abstract class Identity {

    @Expose
    private String id;
    @Expose
    private String dirPath;

    public Identity() {
    }

    public Identity(String id) {
        this.id = id;
    }

    public Identity(String id, String dirPath) {
        this.id = id;
        this.dirPath = dirPath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDirPath() {
        return dirPath;
    }

    public void setDirPath(String dirPath) {
        this.dirPath = dirPath;
    }
}

