package com.univiser.test.inventory.services;


import com.univiser.test.inventory.dto.ProductRequest;
import com.univiser.test.inventory.dto.ProductResponse;
import com.univiser.test.inventory.repositories.model.Product;
import com.univiser.test.inventory.repositories.repository.ProductRepository;
import com.univiser.test.inventory.utils.exeptions.ApiException;
import com.univiser.test.inventory.utils.message.ErrorMessage;
import com.univiser.test.inventory.utils.message.SucessMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public ProductResponse<Product> createProduct(ProductRequest productRequest) throws ApiException {
        try {
            Product product = new Product();
            product.setName(productRequest.getName());
            product.setPrice(productRequest.getPrice());
            product.setQuantity(productRequest.getQuantity());
            product.setCreated_at(new Timestamp(System.currentTimeMillis()));
            Product response = productRepository.save(product);
            ProductResponse<Product> productResponse = new ProductResponse();
            productResponse.setCode(200);
            productResponse.setMessage(SucessMessage.PRODUCT_CREATED_SUCCESSFULLY);
            productResponse.setData(response);
            return productResponse;
        } catch (Exception e) {
            throw new ApiException(500, ErrorMessage.INTERAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ProductResponse<List<Product>> getAllProduct() throws ApiException {
        try {
            List<Product> response = productRepository.findAll();
            ProductResponse<List<Product>> productResponse = new ProductResponse();
            productResponse.setCode(200);
            productResponse.setMessage(SucessMessage.PRODUCT_FOUND_SUCCESSFULLY);
            productResponse.setData(response);
            return productResponse;
        } catch (Exception e) {
            throw new ApiException(500, ErrorMessage.INTERAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ProductResponse<Product> getProductById(Long id) throws ApiException {

        try {
            Optional<Product> response = productRepository.findById(id);
            if (response.isPresent()) {
                ProductResponse<Product> productResponse = new ProductResponse();
                productResponse.setCode(200);
                productResponse.setMessage(SucessMessage.PRODUCT_FOUND_SUCCESSFULLY);
                productResponse.setData(response.get());
                return productResponse;
            }else {
                throw new ApiException(400,ErrorMessage.PRODUCT_NOT_FOUND, HttpStatus.BAD_REQUEST);
            }
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException(500, ErrorMessage.INTERAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ProductResponse<Product> updateProduct(long id, ProductRequest productRequest) throws ApiException {
        try {
            Optional<Product> response = productRepository.findById(id);
            if (response.isPresent()) {
                Product existingProduct = response.get();
                existingProduct.setName(validateName(productRequest.getName(), existingProduct.getName()));
                existingProduct.setPrice(pricevalidate(productRequest, existingProduct));
                existingProduct.setQuantity(Objects.nonNull(productRequest.getQuantity())?productRequest.getQuantity():existingProduct.getQuantity());
                existingProduct.setUpdated_at(new Timestamp(System.currentTimeMillis()));
                Product updatedProduct = productRepository.save(existingProduct);
                ProductResponse<Product> productResponse = new ProductResponse();
                productResponse.setCode(200);
                productResponse.setMessage(SucessMessage.PRODUCT_UPDATED_SUCCESSFULLY);
                productResponse.setData(updatedProduct);
                return productResponse;
            } else {
                throw new ApiException(400, ErrorMessage.PRODUCT_NOT_FOUND, HttpStatus.BAD_REQUEST);
            }
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException(500, ErrorMessage.INTERAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private static double pricevalidate(ProductRequest productRequest, Product existingProduct) {
        if(Objects.isNull(productRequest.getPrice()) || productRequest.getPrice() == 0.0) return existingProduct.getPrice();
        return productRequest.getPrice();
    }

    private static String validateName(String productRequest, String existingProduct) {
        if (Objects.isNull(productRequest) || productRequest.isEmpty()) {
            return existingProduct;
        }
        return productRequest;
    }


    public ProductResponse<Product> deleteProduct(Long id) throws ApiException {
        try {
            Optional<Product> response = productRepository.findById(id);
            if (response.isPresent()) {
                productRepository.deleteById(id);
                ProductResponse<Product> productResponse = new ProductResponse();
                productResponse.setCode(200);
                productResponse.setMessage(SucessMessage.PRODUCT_DELETED_SUCCESSFULLY);

                return productResponse;
            }else {
                throw new ApiException(400, ErrorMessage.PRODUCT_NOT_FOUND, HttpStatus.BAD_REQUEST);
            }
        } catch (ApiException e) {
            throw e;
        }catch (Exception e) {
            throw new ApiException(500, ErrorMessage.INTERAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
