package com.gabrielcampos.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielcampos.cursomc.domain.Pedido;
import com.gabrielcampos.cursomc.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService service;

	// Para o spring saber que o id da url vai ir pro id da variável tem que inserir
	// a anotação @PathVariable

	// O tipo ResponseEntity é um tipo especial do spring que já encapsula várias
	// informações de uma resposta http para um serviço REST

	// o endpoint agora vai ser o /pedidos/algumId
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {

		Pedido obj = service.buscar(id);

		// retorna um objeto ResponseEntity falando que está tudo ok e que tem como
		// corpo o obj de categoria
		return ResponseEntity.ok().body(obj);
	}
}
