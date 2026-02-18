package com.hibernate.ferreteria.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hibernate.ferreteria.repositorio.I_RespoUsuario;

//UserDetailsService sirve para autenticar usuarios en spring security
@Service
public class UsuarioService implements UserDetailsService{

    @Autowired
    private I_RespoUsuario repoUsuario;
    
    //Spring lo usa para autentica en login
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        var usuario = repoUsuario.findByUsuario(username)
            .orElseThrow(() ->
                new UsernameNotFoundException("Usuario no encontrado: " + username));

        return new User(
            usuario.getUsuario(),
            usuario.getPassword(),
            //SimpleGrantedAuthority comvierte el rol en un formato que entiende spring security
            List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getRol()))
        );
    }
}
