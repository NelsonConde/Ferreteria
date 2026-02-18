package com.hibernate.ferreteria;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeneraPassword {
    
    //Lo uso para generar el password encriptado de los usuarios (Esta encriptacion es la que se guarda en bd)
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("admin123"));
    }
}
