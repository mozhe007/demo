package com.company.JMS.domain;

import java.io.Serializable;

/**
 * Created by James on 2017/5/30.
 */
public class User implements Serializable{
    private static final long serialVersionUID = -6849794470754655710L;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
