package com.Icwd.electronic.store.repositories;

import com.Icwd.electronic.store.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    //search
    List<Product> findByTitleContaining(String subTitle);

    List<Product> findByLiveTrue();

}
