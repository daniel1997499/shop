package com.lightart.shop.controller;

import com.lightart.shop.exception.ResourceNotFoundException;
import com.lightart.shop.model.ProductItem;
import com.lightart.shop.repository.ProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MainRestController {
    private final static String ERROR_MESSAGE_RESOURCES_NOT_FOUND = "Resources not found";

    @Autowired
    ProductItemRepository productRepo;

    @GetMapping("/get/products")
    public ResponseEntity<List<ProductItem>> getAllProductItems() {
        List<ProductItem> results;
        try {
            results = productRepo.findAll();
            if (results != null && !results.isEmpty())
                return new ResponseEntity<List<ProductItem>>(results, HttpStatus.OK);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        throw new ResourceNotFoundException(ERROR_MESSAGE_RESOURCES_NOT_FOUND);
    }

    @GetMapping("/get/product/{id}")
    public ResponseEntity<ProductItem> getProductItemById(@PathVariable("id") Long id) {
        ProductItem result;
        if (id != null && id > 0L) {
            try {
                result = productRepo.findById(id).orElse(null);
                if (result != null)
                    return new ResponseEntity<>(result, HttpStatus.OK);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        throw new ResourceNotFoundException(ERROR_MESSAGE_RESOURCES_NOT_FOUND);
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
        try {
            productDTO = productRepo.save(new ProductItem(productDTO.getTitle(), productDTO.getDescription(), productDTO.getPrice(), productDTO.getPictureUrl()));
            if (productDTO.getId() != null && productDTO.getId() > 0L)
                return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        //TODO-end--------------------------------------------------

        throw new ResourceNotFoundException(ERROR_MESSAGE_RESOURCES_NOT_FOUND);
    }

    @PostMapping("/post/products")
    public ResponseEntity<List<ProductItem>> createProducts(@RequestBody List<ProductItem> products) {
        List<ProductItem> productDTOs = new ArrayList<>();
        if (products != null && !products.isEmpty()) {
            for (ProductItem prod : products) {
                if (prod.getId() != null)
                    prod.setId(null);
                if (prod.getTitle() != null && prod.getDescription() != null && prod.getPrice() != null && prod.getPictureUrl() != null)
                    productDTOs.add(prod);
            }
            if (!productDTOs.isEmpty()) {
                try {
                    List<ProductItem> results = productRepo.saveAll(productDTOs);
                    if (results != null && !results.isEmpty())
                        return new ResponseEntity<>(results, HttpStatus.CREATED);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        throw new ResourceNotFoundException(ERROR_MESSAGE_RESOURCES_NOT_FOUND);
    }

    @PutMapping("/put/product/{id}")
    public ResponseEntity<ProductItem> updateProduct(@PathVariable("id") Long id, @RequestBody ProductItem product) {
        ProductItem productDTO;
        ProductItem result;
        if (id != null && id > 0L) {
            try {
                productDTO = productRepo.findById(id).orElse(null);
                if (productDTO != null && productDTO.getId() != null && productDTO.getId().equals(id)) {
                    productDTO.setTitle(product.getTitle());
                    productDTO.setDescription(product.getDescription());
                    productDTO.setPrice(product.getPrice());
                    productDTO.setPictureUrl(product.getPictureUrl());
                    result = productRepo.save(productDTO);
                    return new ResponseEntity<>(result, HttpStatus.OK);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        throw new ResourceNotFoundException(ERROR_MESSAGE_RESOURCES_NOT_FOUND);
    }

    @DeleteMapping("/delete/product/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") Long id) {
        if (id != null && id > 0L) {
            try {
                if (productRepo.findById(id).isPresent())
                    productRepo.deleteById(id);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        throw new ResourceNotFoundException(ERROR_MESSAGE_RESOURCES_NOT_FOUND);
    }

    @DeleteMapping("/delete/products")
    public ResponseEntity<HttpStatus> deleteAllProducts() {
        try {
            productRepo.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new ResourceNotFoundException(ERROR_MESSAGE_RESOURCES_NOT_FOUND);
    }
}