package com.hibernate.ferreteria.seguridad;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.hibernate.ferreteria.servicios.UsuarioService;

//Para definir como se va a manejar la seguridad en la aplicacion
@Configuration
//Permite que el filterchain sustituya la configuracion por defecto
@EnableWebSecurity
public class SecurityConfig{

    private final UsuarioService usuarioService;

    public SecurityConfig(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    //Permite codificar las contraseñas con BCrypt
    @Bean //Registra el objeto en el contenedor gestionado por Spring
    public PasswordEncoder codificaPass(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    //Administra la autenticación que verifica que el usuario y contraseña sean correctas
    public AuthenticationManager autenticacion(AuthenticationConfiguration authConfig)
        throws Exception{
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityChain(HttpSecurity http, //Permite configurar la seguridad web
                                            AuthenticationManager authManager //Gestor de autenticación que valida el usuario y contraseña
    )
            throws Exception{
             http.
                csrf(csrf -> csrf.disable()) //Deshabilita la protección CSRF para facilitar las pruebas con Postman
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/api/auth/**").permitAll() //Permite el acceso sin autenticación a las rutas de autenticación para login
                    .requestMatchers("/api/articulos/**").hasAnyRole("ADMIN", "USER") //Rutas de articulos accesibles para solo para ADMIN y USER
                    //Todas las demas rutas requieren autenticación
                    .anyRequest().authenticated()
                )
                //Inyecta la utenticacion que procesa los intentos de login
                .authenticationManager(authManager)
                //Se le pasa el servicio para que utilice llos datos de la bd para validar
                .userDetailsService(usuarioService)
                //Permite que cualquier usuario pueda acceder al formulario de login
                .formLogin(form -> form.permitAll())
                .httpBasic(basic -> {});
                    //Construye el filterchain apartir de la configuracion dada
                    return http.build();
    }

}
