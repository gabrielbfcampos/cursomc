package com.gabrielcampos.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielcampos.cursomc.domain.Categoria;
import com.gabrielcampos.cursomc.repositories.CategoriaRepository;
import com.gabrielcampos.cursomc.services.exceptions.ObjectNotFoundException;



@Service
public class CategoriaService {

	// Quando você cria uma dependencia com a anotaçao Autowired, essa dependência
	// vai ser automaticamente instanciada pelo spring pelo mecanismo de injeção de
	// dependencia ou inversão de controle
	@Autowired
	private CategoriaRepository repo;

	// implementação do serviço que busca uma categoria
	public Categoria buscar(Integer id) {
		
		Optional<Categoria> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
						"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

}
