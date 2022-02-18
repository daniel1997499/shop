package com.lightart.shop.model;

import com.lightart.shop.util.OrderStatuses;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class OrderItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "item")
    private String item;
    @Column(name = "count")
    private Long count;
    @Column(name = "comment")
    private String comment;
    @Enumerated(EnumType.STRING)
    private OrderStatuses status;
    @CreationTimestamp
    @Column(name = "created")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return id.equals(orderItem.id) && Objects.equals(name, orderItem.name) && Objects.equals(address, orderItem.address) && Objects.equals(phone, orderItem.phone) && Objects.equals(email, orderItem.email) && Objects.equals(item, orderItem.item) && Objects.equals(count, orderItem.count) && Objects.equals(comment, orderItem.comment) && status == orderItem.status && Objects.equals(created, orderItem.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, phone, email, item, count, comment, status, created);
    }
}
