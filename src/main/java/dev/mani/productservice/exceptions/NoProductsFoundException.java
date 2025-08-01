package dev.mani.productservice.exceptions;

public class NoProductsFoundException extends RuntimeException{
    public NoProductsFoundException() {
        super("No products found");
    }
}
