package com.project.productserviceapp.dtos;

import com.project.productserviceapp.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetSingleProductResponseDto {
    private Product product;
}