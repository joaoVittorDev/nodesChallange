package com.test.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.test.model.Node;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class NodeDTO implements Serializable {


    private Long id;
    private Long parentid;
    private String code;
    private String detail;
    private boolean hasChildren;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentid() {
        return parentid;
    }

    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }


    public NodeDTO (@NotNull Node node){

        this.id = node.getId();
        this.code = node.getCode();
        this.parentid = node.getParent() == null ? null : node.getParent().getId();
        this.detail = node.getDetail();

    }

    public NodeDTO(){

    }


}
