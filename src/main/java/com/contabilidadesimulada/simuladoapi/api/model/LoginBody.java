package com.contabilidadesimulada.simuladoapi.api.model;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginBody {

    @NotEmpty(message = "Username cannot be empty or null")
    private String username;

    @NotEmpty(message = "Password cannot by empty or null")
    private String password;

}
