package com.zzw.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzw
 * @see
 * @since 2018/1/17
 */
public class BeanDetails {
    private String bean;
    private List<String> aliases = new ArrayList<>();
    private String scope;
    private String type;
    private String resource;
    private List<String> dependencies = new ArrayList<>();

    public String getBean() {
        return bean;
    }

    public void setBean(String bean) {
        this.bean = bean;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public List<String> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<String> dependencies) {
        this.dependencies = dependencies;
    }
}
