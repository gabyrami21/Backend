package com.example.Book.domain;

import javax.persistence.*;

@Entity
@Table(name = "editorial")
public class Editorial {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;

    private String web_site;

    public Editorial(Integer id) {
        this.id = id;
    }

    public Editorial(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    public Editorial(){

    }

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
        this.name = name;
    }

    public String getWeb_site() {
        return web_site;
    }

    public void setWeb_site(String web_site) {
        this.web_site = web_site;
    }
}
