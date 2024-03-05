package com.project.productserviceapp.services;

public interface CategoryService {
     String getAllCategories();

     String getProductsInCategory(Long categoryId);
}