package com.Icwd.electronic.store.services;

import com.Icwd.electronic.store.dtos.ProductDto;

import java.util.List;

public interface ProductService {

    //create
    ProductDto create(ProductDto productDto);

    // update
    ProductDto update(ProductDto productDto, String productId);

    //delete
    void delete(String productId);

    //get single
    ProductDto get(String productId);

    //get all
    List<ProductDto> getAll();

    //get all:live
    List<ProductDto> getAllLive();

    //search product
    List<ProductDto> searchByTitle(String subTitle);
}
