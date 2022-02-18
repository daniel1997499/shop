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
    public ResponseEntity<List<ProductItem>> getAllProductItems() {
        List<ProductItem> results = new ArrayList<>(productRepo.findAll());
        if (!results.isEmpty()) {
            return new ResponseEntity<>(results, HttpStatus.OK);
        }

        throw new ResourceNotFoundException("Resources not found");
    }

    @GetMapping("/get/product/{id}")
    public ResponseEntity<ProductItem> getProductItemById(@PathVariable("id") Long id) {
        ProductItem result;
        if (id != null && id > 0L) {
            result = productRepo.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Resource with id: " + id + " could not be found"));
            return new ResponseEntity<>(result, HttpStatus.OK);
        }

        throw new ResourceNotFoundException("Resource 'id' needs to be specified");
    }

    @GetMapping("/get/exception")
    public ResponseEntity<Exception> getException() throws Exception {
        throw new Exception("Yep, this is an exception kinda");
    }

    @GetMapping("/get/resourcenotfoundexception")
    public ResponseEntity<ResourceNotFoundException> getResourceNotFoundException() {
        throw new ResourceNotFoundException("Yep, this is a resourcenotfoundexception kinda");
    }

    @PostMapping("/post/product")
    public ResponseEntity<ProductItem> createProduct(@RequestBody ProductItem product) {
        ProductItem productDTO = new ProductItem();
        if (product.getId() != null)
            productDTO.setId(null);
        if (product.getTitle() != null)
            productDTO.setTitle(product.getTitle());
        if (product.getDescription() != null)
            productDTO.setDescription(product.getDescription());
        if (product.getPrice() != null)
            productDTO.setPrice(product.getPrice());
        if (product.getPictureUrl() != null)
            productDTO.setPictureUrl(product.getPictureUrl());
        //TODO optimize this way of saving data to database------
        productDTO = productRepo.save(new ProductItem(productDTO.getTitle(), productDTO.getDescription(), productDTO.getPrice(), productDTO.getPictureUrl()));
        //TODO---------------------------------------------------
        if (productDTO.getId() != null)
            return new ResponseEntity<>(productDTO, HttpStatus.CREATED);

        throw new ResourceNotFoundException("Could not create object");
    }

    @PostMapping("/post/products")
    public ResponseEntity<List<ProductItem>> createProducts(@RequestBody List<ProductItem> products) {
        List<ProductItem> results = new ArrayList<>();
        if (products != null && !products.isEmpty()) {
            for (ProductItem prod : products) {
                if (prod.getId() != null)
                    prod.setId(null);
                if (prod.getTitle() != null && prod.getDescription() != null && prod.getPrice() != null && prod.getPictureUrl() != null)
                 results.add(prod);
            }
            if (!results.isEmpty())
                return new ResponseEntity<>(productRepo.saveAll(results), HttpStatus.CREATED);
        }

        throw new ResourceNotFoundException("List of products is empty");
    }

    @PutMapping("/put/product/{id}")
    public ResponseEntity<ProductItem> updateProduct(@PathVariable("id") Long id, @RequestBody ProductItem product) {
        ProductItem result;
        if (id != null && id > 0L) {
             result = productRepo.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Resource with id: " + id + " could not be found"));
            if (result != null && result.getId() != null && result.getId().equals(id) && result.getTitle().equalsIgnoreCase(product.getTitle()))
                return new ResponseEntity<>(productRepo.save(result), HttpStatus.OK);
        }
        throw new ResourceNotFoundException("Resources not provided");
    }

    @DeleteMapping("/delete/product/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") Long id) {
        if (id != null && id > 0L && productRepo.findById(id).isPresent()) {
            productRepo.deleteById(id);
            throw new ResourceNotFoundException("Resources removed");
        }

        throw new ResourceNotFoundException("Resources already removed or missing");
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
