package com.gabrielcampos.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielcampos.cursomc.domain.Cliente;
import com.gabrielcampos.cursomc.repositories.ClienteRepository;
import com.gabrielcampos.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	// Quando você cria uma dependencia com a anotaçao Autowired, essa dependência
	// vai ser automaticamente instanciada pelo spring pelo mecanismo de injeção de
	// dependencia ou inversão de controle
	@Autowired
	private ClienteRepository repo;

	// implementação do serviço que busca um cliente
	public Cliente buscar(Integer id) {
		
		// A principal proposta do Optional é encapsular o retorno de métodos e
		// informar se um valor do tipo <T> está presente ou ausente.
		Optional<Cliente> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

}
