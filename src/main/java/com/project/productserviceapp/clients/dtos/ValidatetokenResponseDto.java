package com.project.productserviceapp.clients.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidatetokenResponseDto {
    private UserDto userDto;
    private SessionStatus sessionStatus;
}