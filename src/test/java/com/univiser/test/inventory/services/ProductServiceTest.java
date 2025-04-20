package com.univiser.test.inventory.services;

import com.univiser.test.inventory.dto.ProductRequest;
import com.univiser.test.inventory.dto.ProductResponse;
import com.univiser.test.inventory.repositories.model.Product;
import com.univiser.test.inventory.repositories.repository.ProductRepository;
import com.univiser.test.inventory.utils.exeptions.ApiException;
import com.univiser.test.inventory.utils.message.ErrorMessage;
import com.univiser.test.inventory.utils.message.SucessMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;
    private ProductRequest productRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setPrice(1000.0);
        product.setQuantity(10);

        productRequest = new ProductRequest();
        productRequest.setName("Laptop");
        productRequest.setPrice(1000.0);
        productRequest.setQuantity(10);
    }
    @Test
    void createProduct() throws ApiException {


        ProductResponse<Product> response = productService.createProduct(productRequest);

        assertEquals(200, response.getCode());
        assertEquals(SucessMessage.PRODUCT_CREATED_SUCCESSFULLY, response.getMessage());

    }

    @Test
    void getAllProduct() throws ApiException {
        List<Product> products = Collections.singletonList(product);
        when(productRepository.findAll()).thenReturn(products);

        ProductResponse<List<Product>> response = productService.getAllProduct();

        assertEquals(200, response.getCode());
        assertEquals(SucessMessage.PRODUCT_FOUND_SUCCESSFULLY, response.getMessage());
        assertEquals(products, response.getData());
    }

    @Test
    void getProductById() throws ApiException {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        ProductResponse<Product> response = productService.getProductById(1L);

        assertEquals(200, response.getCode());
        assertEquals(SucessMessage.PRODUCT_FOUND_SUCCESSFULLY, response.getMessage());
        assertEquals(product, response.getData());
    }
    @Test
    void getProductById_NotFound() {
        when(productRepository.findById(2L)).thenReturn(Optional.empty());

        ApiException exception = assertThrows(ApiException.class, () -> {
            productService.getProductById(2L);
        });

        assertEquals(400, exception.getCode());
        assertEquals(ErrorMessage.PRODUCT_NOT_FOUND, exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }
    @Test
    void updateProduct() throws ApiException {
        Product updatedProduct = new Product();
        updatedProduct.setId(1L);
        updatedProduct.setName("Updated Laptop");
        updatedProduct.setPrice(1500.0);
        updatedProduct.setQuantity(5);

        productRequest.setName("Updated Laptop");
        productRequest.setPrice(1500.0);
        productRequest.setQuantity(5);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));


        ProductResponse<Product> response = productService.updateProduct(1L, productRequest);

        assertEquals(200, response.getCode());
        assertEquals(SucessMessage.PRODUCT_UPDATED_SUCCESSFULLY, response.getMessage());

    }
    @Test
    void updateProduct_NotFound() {
        when(productRepository.findById(2L)).thenReturn(Optional.empty());

        ApiException exception = assertThrows(ApiException.class, () -> {
            productService.updateProduct(2L, productRequest);
        });

        assertEquals(400, exception.getCode());
        assertEquals(ErrorMessage.PRODUCT_NOT_FOUND, exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void deleteProduct() throws ApiException {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        doNothing().when(productRepository).deleteById(1L);

        ProductResponse<Product> response = productService.deleteProduct(1L);

        assertEquals(200, response.getCode());
        assertEquals(SucessMessage.PRODUCT_DELETED_SUCCESSFULLY, response.getMessage());
    }

    @Test
    void deleteProduct_NotFound() {
        when(productRepository.findById(2L)).thenReturn(Optional.empty());

        ApiException exception = assertThrows(ApiException.class, () -> {
            productService.deleteProduct(2L);
        });

        assertEquals(400, exception.getCode());
        assertEquals(ErrorMessage.PRODUCT_NOT_FOUND, exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }
}