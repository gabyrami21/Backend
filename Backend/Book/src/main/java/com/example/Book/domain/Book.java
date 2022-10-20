package com.example.Book.domain;

import javax.persistence.*;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;
    private String imageUrl;

    private int editorialId;

    public Book() {
    }

    public Book(String name) {
        this.name = name;
    }

    public Book( Integer id,String name, String description, String imageUrl, int editorialId)
    {
        this.id=id;
        this.name=name;
        this.description=description;
        this.imageUrl=imageUrl;
        this.editorialId=editorialId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getEditorialId() {
        return editorialId;
    }

    public void setEditorialId(int editorialId) {
        this.editorialId = editorialId;
    }
}
