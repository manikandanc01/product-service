package dev.mani.productservice.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceFactory {
    private final ProductService databaseProductService;
    private final ProductService fakeStorageProductService;
    public ProductServiceFactory(
            @Qualifier("databaseProductService") ProductService databaseProductService,
            @Qualifier("fakeStoreProductService") ProductService fakeStorageProductService
                                 ) {
        this.databaseProductService = databaseProductService;
        this.fakeStorageProductService = fakeStorageProductService;
    }

    public ProductService getService(boolean isDatabase) {
        return isDatabase ? databaseProductService : fakeStorageProductService;
    }

}
