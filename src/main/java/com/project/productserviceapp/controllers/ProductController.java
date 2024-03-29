package com.project.productserviceapp.controllers;

import com.project.productserviceapp.clients.AuthenticationClient;
import com.project.productserviceapp.clients.dtos.Role;
import com.project.productserviceapp.clients.dtos.SessionStatus;
import com.project.productserviceapp.clients.dtos.ValidatetokenResponseDto;
import com.project.productserviceapp.dtos.ProductDto;
import com.project.productserviceapp.exceptions.NotFoundException;
import com.project.productserviceapp.models.Category;
import com.project.productserviceapp.models.Product;
import com.project.productserviceapp.repositories.ProductRepository;
import com.project.productserviceapp.services.ProductService;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    private ProductRepository productRepository;

    private AuthenticationClient authenticationClient;

    public ProductController( ProductService productService, ProductRepository productRepository, AuthenticationClient authenticationClient) {
        this.productRepository = productRepository;
        this.productService = productService;
        this.authenticationClient = authenticationClient;
    }

    // Make only admins be able to access all products
    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts(@Nullable @RequestHeader("AUTH_TOKEN") String token,
                                                        @Nullable @RequestHeader("USER_ID") Long userId) {
       // check if token exists
        if (token == null || userId == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        ValidatetokenResponseDto response = authenticationClient.validate(token, userId);

        // check if token is valid
        if (response.getSessionStatus().equals(SessionStatus.INVALID)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // validate token
        // RestTemplate rt = new RestTRemplate();
        //  rt.get("localhost:9090/auth/validate?)

        // check if user has permissions
        boolean isUserAdmin = false;
        for (Role role: response.getUserDto().getRoles()) {
            if (role.getName().equals("ADMIN")) {
                isUserAdmin = true;
            }
        }

        if (!isUserAdmin) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        List<Product> products = productService.getAllProducts();

//        products.get(0).setPrice(100); /// Bug induced in my code
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("productId") Long productId) throws NotFoundException {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        headers.add(
                "auth-token", "noaccess4uheyhey"
        );

//        try {
        Optional<Product> productOptional = productService.getSingleProduct(productId);
//        } catch (Exception e) {
//            throw e;
//        }
//
//        if (product == null) {
//
//        }

//        Product product = productOptional.get();

        if (productOptional.isEmpty()) {
            throw new NotFoundException("No Product with product id: " + productId);
        }

        ResponseEntity<Product> response = new ResponseEntity(
                productService.getSingleProduct(productId),
                headers,
                HttpStatus.NOT_FOUND
        );

        return response;
//        GetSingleProductResponseDto responseDto = new GetSingleProductResponseDto();
//        responseDto.setProduct(
//              return  ;
//        );

//        return responseDto;
    }


    @PostMapping()
    public ResponseEntity<Product> addNewProduct(@RequestBody ProductDto product) {

//        Product newProduct = productService.addNewProduct(
//                product
//        );

        Product newProduct = new Product();
        newProduct.setDescription(product.getDescription());
        newProduct.setImageUrl(product.getImage());
        newProduct.setTitle(product.getTitle());
        newProduct.setPrice(product.getPrice());

        newProduct = productRepository.save(newProduct);

        ResponseEntity<Product> response = new ResponseEntity<>(newProduct, HttpStatus.OK);

        return response;
    }

    @PatchMapping("/{productId}")
    public Product updateProduct(@PathVariable("productId") Long productId,
                                 @RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setCategory(new Category());
        product.getCategory().setName(productDto.getCategory());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());

        return productService.updateProduct(productId, product);
    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId) {
        return "Deleting a product with id: " + productId;
    }

}
