package com.project.productserviceapp.clients.dtos;

import com.project.productserviceapp.models.BaseModel;
//import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Role extends BaseModel {
    private String name;

//    private List<User> users;
}