package com.project.productserviceapp.controllers;

import com.project.productserviceapp.dtos.ProductDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    @GetMapping()
    public String getAllProducts(){
        return "Getting all Products";
    }
    @GetMapping("/{productId}")
    public String getSingleProduct(@PathVariable("productId") Long productId){
        return "Returning Single Product: "+productId;
    }
    @PostMapping("")
    public String addNewProduct(@RequestBody ProductDto productDto){
        return "Adding new Product: "+ productDto;
    }
    public String updateProduct(){
        return "Updating Product";
    }
    public String deleteProduct(){
        return "deleting a product";
    }
}
