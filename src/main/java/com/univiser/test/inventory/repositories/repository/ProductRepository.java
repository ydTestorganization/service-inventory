package com.univiser.test.inventory.repositories.repository;

import com.univiser.test.inventory.repositories.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
