package com.project.productserviceapp.repositories;

import com.project.productserviceapp.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product save(Product product);

    Product findProductById(Long id);

    Product findProductByIdIs(Long id);

    Product findProductByIdEquals(Long id);

    Product findByPriceBetweenAndTitleLessThanEqual(double greaterThanEqualPrice, double lessThanEqualPrice, String titleLessThan);
    // select * from products where


    Product findByPriceLessThanEqual(double price);
    // select * from products where prices <= 100

    // productRepository.findByPriceLessThanEqual(100);

    List<Product> findByImageUrlIsNullOrderByIdDesc();

    List<Product> findByTitleIgnoreCaseStartingWith(String title);

    List<Product> findByTitleLikeIgnoreCase(String titleLike);

    // productRepository.findByImageUrlIsNull()
    // select * from products where image_url is null;
    // @Query("select * from users join product join category on product.c_id = category.id")

    @Query("select p.title from Product p where p.id = :id and p.category.name = :categoryName")
    public List<Product> getByIdAndTitle(Long id, String categoryName);
}