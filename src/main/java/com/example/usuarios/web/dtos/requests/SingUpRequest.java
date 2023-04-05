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

    @NotNull
    private String fullName;

    @Email
    private String email;

    @Size(min = 8, message = "The password should have 8 characters min.")
    private String password;

    @NotNull
    private String rol;

    @NotEmpty
    private String sessionId;

}
