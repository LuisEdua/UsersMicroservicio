package com.example.usuarios.web.dtos.responses;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserResponse {

    @NotNull @NotEmpty
    private Long id;

    @NotEmpty
    @NotNull
    private String fullName;

    @Email
    @NotNull @NotEmpty
    private String email;

    @NotEmpty @NotNull
    private String rol;
}
