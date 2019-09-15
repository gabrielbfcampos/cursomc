package com.gabrielcampos.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielcampos.cursomc.domain.Pedido;
import com.gabrielcampos.cursomc.repositories.PedidoRepository;
import com.gabrielcampos.cursomc.services.exceptions.ObjectNotFoundException;



@Service
public class PedidoService {

	// Quando você cria uma dependencia com a anotaçao Autowired, essa dependência
	// vai ser automaticamente instanciada pelo spring pelo mecanismo de injeção de
	// dependencia ou inversão de controle
	@Autowired
	private PedidoRepository repo;

	// implementação do serviço que busca um pedido
	public Pedido buscar(Integer id) {
		
		Optional<Pedido> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
						"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

}
