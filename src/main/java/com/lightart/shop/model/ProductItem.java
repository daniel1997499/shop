package com.lightart.shop.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "products_table")
public class ProductItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name = "title", nullable = false)
    private String title;
    @NotNull
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "pictureUrl")
    private String pictureUrl;
    @Lob
    @Column(name="product_picture")
    private byte[] productPic;

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

    public ProductItem(String title, String description, BigDecimal price, String pictureUrl, byte[] productPic) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.pictureUrl = pictureUrl;
        this.productPic = productPic;
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

    public byte[] getProductPic() {
        return productPic;
    }

    public void setProductPic(byte[] productPic) {
        this.productPic = productPic;
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
}
