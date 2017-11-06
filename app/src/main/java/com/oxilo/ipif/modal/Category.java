package com.oxilo.ipif.modal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikk on 24/7/17.
 */

public class Category {
    private String title;
    private List<Service> service = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Service> getService() {
        return service;
    }

    public void setService(List<Service> service) {
        this.service = service;
    }
}
