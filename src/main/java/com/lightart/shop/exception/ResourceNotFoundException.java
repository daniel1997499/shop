package com.lightart.shop.exception;

public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public ResourceNotFoundException(String msg) {
        super(msg);
    }
    public ResourceNotFoundException(Long id) {
        super("Resource with id: " + id + " could not be found");
    }
}
