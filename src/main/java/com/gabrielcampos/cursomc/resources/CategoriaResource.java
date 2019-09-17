package com.gabrielcampos.cursomc.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gabrielcampos.cursomc.domain.Categoria;
import com.gabrielcampos.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;

	// Para o spring saber que o id da url vai ir pro id da variável tem que inserir
	// a anotação @PathVariable

	// O tipo ResponseEntity é um tipo especial do spring que já encapsula várias
	// informações de uma resposta http para um serviço REST

	// o endpoint agora vai ser o /categorias/algumId
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {

		Categoria obj = service.buscar(id);

		// retorna um objeto ResponseEntity falando que está tudo ok e que tem como
		// corpo o obj de categoria
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Categoria obj) {
		obj = service.insert(obj);
		//Chamada que pega a URI do novo recurso que foi inserido
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		//Retornar as respostas certinhas
		return ResponseEntity.created(uri).build();
	}
}
