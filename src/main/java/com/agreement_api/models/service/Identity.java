package com.agreement_api.models.service;


public abstract class Identity {

    private String id;
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

