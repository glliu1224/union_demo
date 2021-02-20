package com.example.demo.entity;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;


public class UserRequestLog {

    private Integer id;

    private String url;

    private String method;

    private String parameters;

    private Integer result;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        String param = "";
        for (Object parameter : parameters) {
            param += parameter + ",";
        }
        if (StringUtils.isNotEmpty(param)) {
            param = param.substring(0, param.length() - 1);
        }
        this.parameters = param;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}