package com.project.productserviceapp.services;

import com.project.productserviceapp.dtos.ProductDto;


import com.project.productserviceapp.exceptions.NotFoundException;
import com.project.productserviceapp.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();

    Optional<Product> getSingleProduct(Long productId) throws NotFoundException;

    Product addNewProduct(ProductDto product);

    /*
    Product object has only those fields filled which need to be updated.
    Everything else is null
     */
    Product updateProduct(Long productId, Product product);
    // if (product.getImageUrl() != null) {
    //
    // }
    Product replaceProduct(Long productId, Product product);

    boolean deleteProduct(Long productId);
}