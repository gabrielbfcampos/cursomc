package com.gabrielcampos.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gabrielcampos.cursomc.domain.Categoria;
import com.gabrielcampos.cursomc.repositories.CategoriaRepository;

//A interface CommandLineRuner permite executar uma ação quando a aplicação iniciar
@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		//Arrays.asList cria uma lista automática
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
	}

}
