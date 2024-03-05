package com.project.productserviceapp.controllers;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

    public String getAllCategories(){
        return "Returning all categories";
    }
}
