package com.Icwd.electronic.store.services.Impl;

import com.Icwd.electronic.store.dtos.PageableResponse;
import com.Icwd.electronic.store.dtos.ProductDto;
import com.Icwd.electronic.store.entities.Product;
import com.Icwd.electronic.store.exceptions.ResourceNotFoundException;
import com.Icwd.electronic.store.helper.Helper;
import com.Icwd.electronic.store.repositories.ProductRepository;
import com.Icwd.electronic.store.services.ProductService;
import net.bytebuddy.TypeCache;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public ProductDto create(ProductDto productDto) {
        Product product = mapper.map(productDto, Product.class);
        Product saveProduct = productRepository.save(product);
        return mapper.map(saveProduct , ProductDto.class);
    }

    @Override
    public ProductDto update(ProductDto productDto, String productId) {
        //fetch the entity
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found with the given id!!"));
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setDiscountedPrice(productDto.getDiscountedPrice());
        product.setQuantity(productDto.getQuantity());
        product.setLive(productDto.isLive());
        product.setStock(productDto.isStock());

        //save the entity
        Product updatedProduct = productRepository.save(product);
        return mapper.map(updatedProduct, ProductDto.class);
    }

    @Override
    public void delete(String productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found with the given id!!"));
        productRepository.delete(product);

    }

    @Override
    public ProductDto get(String productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found with the given id!!"));

        return mapper.map(product, ProductDto.class);
    }

    @Override
    public PageableResponse<ProductDto> getAll(int pageNumber,int pageSize, String sortBy, String sortDir) {
        Sort sort=(sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) :(Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Product> page = productRepository.findAll(pageable);
        return Helper.getUPageableResponse(page, ProductDto.class);
    }

    @Override
    public PageableResponse<ProductDto> getAllLive(int pageNumber,int pageSize, String sortBy, String sortDir) {
        Sort sort=(sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) :(Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Product> page = productRepository.findByLiveTrue(pageable);
        return Helper.getUPageableResponse(page, ProductDto.class);
    }

    @Override
    public PageableResponse<ProductDto> searchByTitle(String subTitle, int pageNumber,int pageSize, String sortBy, String sortDir) {
        Sort sort=(sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) :(Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Product> page = productRepository.findByTitleContaining(subTitle, pageable);
        return Helper.getUPageableResponse(page, ProductDto.class);
    }
}
