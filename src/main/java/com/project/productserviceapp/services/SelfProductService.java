package com.project.productserviceapp.services;

import com.project.productserviceapp.dtos.ProductDto;
import com.project.productserviceapp.exceptions.NotFoundException;
import com.project.productserviceapp.models.Product;
import com.project.productserviceapp.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(value = "selfProductService")
//@Primary
public class SelfProductService implements ProductService {
    private ProductRepository productRepository;

    public SelfProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getSingleProduct(Long productId) throws NotFoundException {
        Product product = productRepository.findProductById(productId);

        if (product == null) {
            throw new NotFoundException("Product Doesn't Exist");
        }

        return Optional.of(product);
    }

    @Override
    public Product addNewProduct(ProductDto product) {
        return null;
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {
        return null;
    }

    @Override
    public boolean deleteProduct(Long productId) {
        return false;
    }
}