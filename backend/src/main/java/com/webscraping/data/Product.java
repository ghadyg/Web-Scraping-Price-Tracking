package com.webscraping.data;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "Product")
public class Product {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column(name = "Url")
    private String url;
    @Column(name = "Image")
    private String image;
    @Column(name = "Name")
    private String name;
    @Column(name = "Price")
    private Double price;

    @Column(name = "Date")
    private Date createdAt;

    @Column(name = "searchText")
    private String searchText;

    @Column(name = "Source")
    private String source ;


    @Column(name = "Tracked")
    private Boolean tracked;

    public Product(String url, String image, String name, Double price) {
        this.url = url;
        this.image = image;
        this.name = name;
        this.price = price;
        this.createdAt = Date.from(Instant.now());
        this.tracked = true;
    }

    public Product() {
        this.createdAt = Date.from(Instant.now());
        this.tracked = true;
    }

    public Product(String url, String image, String name, Double price, String searchText, String source) {
        this.url = url;
        this.image = image;
        this.name = name;
        this.price = price;
        this.createdAt = Date.from(Instant.now());;
        this.searchText = searchText;
        this.source = source;
        this.tracked = true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Boolean getTracked() {
        return tracked;
    }

    public void setTracked(Boolean tracked) {
        this.tracked = tracked;
    }


    @Override
    public String toString() {
        return "{ \"url\":\"" + url + "\", "
                + " \"image\": \"" + image + "\", "
                + "\"name\":\"" + name + "\", "
                + "\"price\": \"" + price + "\" }\n";
    }

}
