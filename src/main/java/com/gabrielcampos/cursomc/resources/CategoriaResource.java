package com.gabrielcampos.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gabrielcampos.cursomc.domain.Categoria;
import com.gabrielcampos.cursomc.dto.CategoriaDTO;
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
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {

		Categoria obj = service.find(id);

		// retorna um objeto ResponseEntity falando que está tudo ok e que tem como
		// corpo o obj de categoria
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	//o RequestBody faz com que o obj categoria seja construido com os dados json que eu enviar
	//faz o json ser convertido automaticamente
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDTO) {
		
		//Converção do objeto dto para um objeto entity
		Categoria obj = service.fromDTO(objDTO);
		
		obj = service.insert(obj);
		//Chamada que pega a id do novo recurso que foi inserido e fornecer como argumento da URI
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		//Retornar as respostas certinhas
		return ResponseEntity.created(uri).build();
	}
	
	//Como o id vai estar lá para ser atualizado a gente vai ter que passar o id
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO objDTO, @PathVariable Integer id){
		
		Categoria obj = service.fromDTO(objDTO);
		
		obj.setId(id);
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	//Trazer todas as categorias
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll() {

		List<Categoria> list = service.findAll();
		
		//instanciando o dto correspondente a lista de categoria
		//stream para percorrer toda a lista
		//operacao map para efetuar uma operacao para cada elemento da lista no caso obj
		//pra cada elemento obj a gente cria um arrowfunction (- >) que vai criar um new categoriaDTO que vai receber o obj
		//Pra converter a lista na listDTO é só colocar o .collect(Collectors.toList())
		List<CategoriaDTO> listDto = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
	
		return ResponseEntity.ok().body(listDto);
	}
	
	//trazer as categorias por paginação
	//não é como variáveis do path igual o ID para buscar uma específica e sim por parâmetros
	// a ideia é ficar /categorias/page?page=0&linesPerPage=20 e assim por diante, para que eles sejam parametros
	// vamos botar a anotação RequestParam
	@RequestMapping(value="/page",method = RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> findPage(
			@RequestParam(value="page", defaultValue = "0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue = "nome") String orderBy,
			//Ou ASC ou DESC
			@RequestParam(value="direction", defaultValue = "ASC") String direction) {
		
		//por ser java8 compliance não precisa nem do stream nem do collect
		Page<Categoria> list = service.findPage(page, linesPerPage, orderBy, direction);
		
		
		Page<CategoriaDTO> listDto = list.map(obj -> new CategoriaDTO(obj));
	
		return ResponseEntity.ok().body(listDto);
	}

}
