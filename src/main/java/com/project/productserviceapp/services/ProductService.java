package com.project.productserviceapp.services;

import com.project.productserviceapp.dtos.ProductDto;


public interface ProductService {
    public String getAllProducts();

    public String getSingleProduct( Long productId);

    public String addNewProduct(ProductDto productDto);
    public String updateProduct();
    public String deleteProduct();
}
