package com.example.usuarios.web.dtos.requests;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SingUpRequest {

    @NotEmpty @NotNull
    private String fullName;

    @Email @NotNull @NotEmpty
    private String email;

    @NotEmpty @Size(min = 8, message = "The password should have 8 characters min.")
    private String password;

    @NotEmpty @NotNull
    private String rol;

    @NotEmpty @NotNull
    private String sesionId;

}
