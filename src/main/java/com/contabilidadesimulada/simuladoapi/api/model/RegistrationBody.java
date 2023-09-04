package com.contabilidadesimulada.simuladoapi.api.model;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationBody {

    @NotEmpty(message = "Username canot be null")
    @Size(min = 3, max = 255)
    private String username;

    @Email(message = "Write a valid email")
    @NotEmpty
    private String email;

    @NotEmpty
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$", message = "Password must contain at least six characters and one letter")
    private String password;

}
