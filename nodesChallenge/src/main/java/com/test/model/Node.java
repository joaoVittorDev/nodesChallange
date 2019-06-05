package com.test.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "node")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Node {

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String code;

    private String detail;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parentid", referencedColumnName = "id")
    private Node parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade={CascadeType.REMOVE, CascadeType.MERGE})
    private List<Node> children;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
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

}
