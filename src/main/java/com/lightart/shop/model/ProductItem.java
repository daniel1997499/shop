package com.lightart.shop.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "products")
public class ProductItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "pictureUrl")
    private String pictureUrl;

    public ProductItem() {
    }

    public ProductItem(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public ProductItem(String title, String description, BigDecimal price, String pictureUrl) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.pictureUrl = pictureUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @Override
    public String toString() {
        return "ProductItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", pictureUrl='" + pictureUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductItem that = (ProductItem) o;
        return id.equals(that.id) && Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(price, that.price) && Objects.equals(pictureUrl, that.pictureUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, price, pictureUrl);
    }
}
