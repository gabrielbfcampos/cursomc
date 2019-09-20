package com.gabrielcampos.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielcampos.cursomc.domain.Cliente;
import com.gabrielcampos.cursomc.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;

	// Para o spring saber que o id da url vai ir pro id da variável tem que inserir
	// a anotação @PathVariable

	// O tipo ResponseEntity é um tipo especial do spring que já encapsula várias
	// informações de uma resposta http para um serviço REST

	// o endpoint agora vai ser o /clientes/algumId
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {

		Cliente obj = service.find(id);

		// retorna um objeto ResponseEntity falando que está tudo ok e que tem como
		// corpo o obj de cliente
		return ResponseEntity.ok().body(obj);
	}
}
