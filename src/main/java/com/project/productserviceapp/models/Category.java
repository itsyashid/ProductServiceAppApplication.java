package com.project.productserviceapp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Set;

@Getter
@Setter
@Entity
//@Table(name = "yash")
public class Category extends BaseModel {
    private String name;
    private String description;

    // C : P
    // 1 : m
    // 1 : 1
    // 1 : m

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = {CascadeType.REMOVE})
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 1)
    private Set<Product> products;
}
