package com.lightart.shop.controller;

import com.lightart.shop.exception.ResourceNotFoundException;
import com.lightart.shop.model.ProductItem;
import com.lightart.shop.repository.ProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired
    ProductItemRepository productRepo;

    @GetMapping("/get/products")
    public ResponseEntity<List<ProductItem>> getAllProductItems(@RequestParam(required = false) String title) {
        try {
            List<ProductItem> products = new ArrayList<>();
            if (title == null)
                products.addAll(productRepo.findAll());
            else
                products.addAll(productRepo.findProductByTitle(title));
            if (products.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/product/{id}")
    public ResponseEntity<ProductItem> getProductById(@PathVariable("id") long paramId) {
        ProductItem product = productRepo.findById(paramId)
                .orElseThrow(() -> new ResourceNotFoundException(paramId));
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/get/exception")
    public ResponseEntity<Exception> getException() throws Exception {
        throw new Exception("Yep, this is an exception kinda");
    }

    @GetMapping("/get/resourcenotfoundexception")
    public ResponseEntity<ResourceNotFoundException> getError() throws Exception {
        throw new ResourceNotFoundException("Yep, this is a resourcenotfoundexception kinda");
    }

    @PostMapping("/post/product")
    public ResponseEntity<ProductItem> createProduct(@RequestBody ProductItem product) {
        try {
            ProductItem result = productRepo.save(new ProductItem(product.getTitle(), product.getDescription()));
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/post/products")
    public ResponseEntity<List<ProductItem>> createProducts(@RequestBody List<ProductItem> products) {
        try {
            List<ProductItem> results = productRepo.saveAllAndFlush(products);
            return new ResponseEntity<>(results, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/put/product/{id}")
    public ResponseEntity<ProductItem> updateProduct(@PathVariable("id") long id, @RequestBody ProductItem product) {
        Optional<ProductItem> optProduct = productRepo.findById(id);
        if (optProduct.isPresent()) {
            ProductItem resultProduct = optProduct.get();
            resultProduct.setTitle(product.getTitle());
            resultProduct.setDescription(product.getDescription());
            resultProduct.setPrice(product.getPrice());
            resultProduct.setPictureUrl(product.getPictureUrl());
            return new ResponseEntity<>(productRepo.save(resultProduct), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/product/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") long id) {
        try {
            productRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/products")
    public ResponseEntity<HttpStatus> deleteAllProducts() {
        try {
            productRepo.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
