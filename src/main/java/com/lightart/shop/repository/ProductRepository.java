package com.lightart.shop.repository;

import com.lightart.shop.model.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductItem, Long> {
    List<ProductItem> findProductById(Long id);
    List<ProductItem> findProductByTitle(String title);
    List<ProductItem> findProductByDescription(String description);
    List<ProductItem> findProductByPrice(String price);
    List<ProductItem> findProductByPictureUrl(String pictureUrl);
}
