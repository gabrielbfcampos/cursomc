package com.gabrielcampos.cursomc;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gabrielcampos.cursomc.domain.Categoria;
import com.gabrielcampos.cursomc.domain.Cidade;
import com.gabrielcampos.cursomc.domain.Estado;
import com.gabrielcampos.cursomc.domain.Produto;
import com.gabrielcampos.cursomc.repositories.CategoriaRepository;
import com.gabrielcampos.cursomc.repositories.CidadeRepository;
import com.gabrielcampos.cursomc.repositories.EstadoRepository;
import com.gabrielcampos.cursomc.repositories.ProdutoRepository;

//A interface CommandLineRuner permite executar uma ação quando a aplicação iniciar
@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	EstadoRepository estadoRepository;
	
	@Autowired
	CidadeRepository cidadeRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		//fazendo a associação dos produtos que estão associados a categoria 1
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		
		//fazendo a associação dos produtos que estão associados a categoria 2
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		//fazendo a associação das categorias que estão associados ao produto 1
		p1.getCategorias().addAll(Arrays.asList(cat1));
		
		//fazendo a associação das categorias que estão associados ao produto 2
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		
		//fazendo a associação das categorias que estão associados ao produto 3
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		//Arrays.asList cria uma lista automática
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		//---------------
		
		Estado est1 = new Estado(null, "Minas Gerais");
		
		Estado est2 = new Estado(null, "São Paulo");
		
		//Quando é muitos pra um a gente faz a associação no próprio construtor
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		

	}

}
