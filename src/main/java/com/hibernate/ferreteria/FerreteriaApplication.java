package com.hibernate.ferreteria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hibernate.ferreteria.entity.Articulo;
import com.hibernate.ferreteria.repositorio.I_RepoArticulo;

@SpringBootApplication
//CommandLineRunner permite implementar codigo al iniciar la aplicacion
public class FerreteriaApplication implements CommandLineRunner{

	@Autowired
	private I_RepoArticulo repositorio;

	public static void main(String[] args) {
		SpringApplication.run(FerreteriaApplication.class, args);
	}

	//Este es el que va a correr al levantar el servidor
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Aplicacion iniciada correspondiente");
		List<Articulo> articulos = repositorio.findAll();
		articulos.stream().forEach(articulo -> System.out.println(articulo));
	}
}
