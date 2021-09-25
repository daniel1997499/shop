package com.lightart.shop.model;

import com.sun.istack.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "orders_table")
public class OrderItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;
    @NotNull
    @Column(name = "address", nullable = false)
    private String address;
    @NotNull
    @Column(name = "phone", nullable = false)
    private String phone;
    @NotNull
    @Column(name = "email", nullable = false)
    private String email;
    @NotNull
    @Column(name = "item", nullable = false)
    private String item;
    @NotNull
    @Column(name = "count", nullable = false)
    private Long count;
    @Column(name = "comment")
    private String comment;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @NotNull
    @CreationTimestamp
    @Column(name = "created", nullable = false)
    private Timestamp created;

    public OrderItem() {
    }

    public OrderItem(Long id, String name, String address, String phone, String email, String item, Long count) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.item = item;
        this.count = count;
    }

    public OrderItem(String name, String address, String phoneNumber, String email, String item, Long count) {
        this.name = name;
        this.address = address;
        this.phone = phoneNumber;
        this.email = email;
        this.item = item;
        this.count = count;
    }

    public OrderItem(String name, String address, String phoneNumber, String email, String item, Long count, String comment) {
        this.name = name;
        this.address = address;
        this.phone = phoneNumber;
        this.email = email;
        this.item = item;
        this.count = count;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", item='" + item + '\'' +
                ", count=" + count +
                ", comment='" + comment + '\'' +
                ", created=" + created +
                '}';
    }
}
