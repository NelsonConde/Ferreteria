package com.hibernate.ferreteria.controles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        try {
            // Intenta autenticar con el usuario y contraseña proporcionados
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsuario(),
                    loginRequest.getPassword()
                )
            );
            
            // Si la autenticación es exitosa, retorna un mensaje
            return "Autenticación exitosa para usuario: " + authentication.getName();
        } catch (AuthenticationException e) {
            // Si falla, retorna un mensaje de error
            return "Error en la autenticación: " + e.getMessage();
        }
    }
}

// Clase auxiliar para recibir las credenciales
class LoginRequest {
    private String usuario;
    private String password;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
