package com.univiser.test.inventory.controlles;


import com.univiser.test.inventory.dto.ProductRequest;
import com.univiser.test.inventory.dto.ProductResponse;
import com.univiser.test.inventory.repositories.model.Product;
import com.univiser.test.inventory.services.ProductService;
import com.univiser.test.inventory.utils.exeptions.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory/product")
@Slf4j
public class ProductController extends BaseController {


         @Autowired
         private ProductService productService;


     @PostMapping("/create")
     public ResponseEntity<ProductResponse<Product>> createProduct(@RequestBody ProductRequest productRequest) throws ApiException {
                log.info("createProduct,x-reqeust-id={}", MDC.get("x-request-id"));
                 ProductResponse<Product> productResponse = productService.createProduct(productRequest);
                 return response(productResponse);

     }
     @GetMapping("/all")
    public ResponseEntity<ProductResponse<List<Product>>> getAllProduct() throws ApiException {

        ProductResponse<List<Product>> productResponse = productService.getAllProduct();
        return response(productResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse<Product>> getProductById(@PathVariable("id") Long id) throws ApiException {


        ProductResponse<Product> productResponse = productService.getProductById(id);

        return response(productResponse);
     }

     @PutMapping("/update/{id}")
     ResponseEntity<ProductResponse<Product>> updateProduct(@PathVariable("id") Long id, @RequestBody ProductRequest productRequest) throws ApiException {

        ProductResponse<Product> productResponse = productService.updateProduct(id ,productRequest);

        return response(productResponse);
     }
     @DeleteMapping("/delete/{id}")
     ResponseEntity<ProductResponse<Product>> deleteProduct(@PathVariable("id") Long id) throws ApiException {

        ProductResponse<Product> productResponse = productService.deleteProduct(id);

        return response(productResponse);
     }

}
