package com.lhs.www.entity;

public class Product {
    private Integer id;

    private String name;

    private Integer totalnum;

    private Integer usednum;

    private Integer version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getTotalnum() {
        return totalnum;
    }

    public void setTotalnum(Integer totalnum) {
        this.totalnum = totalnum;
    }

    public Integer getUsednum() {
        return usednum;
    }

    public void setUsednum(Integer usednum) {
        this.usednum = usednum;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}