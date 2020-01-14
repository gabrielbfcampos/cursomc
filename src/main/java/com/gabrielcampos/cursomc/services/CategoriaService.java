package com.gabrielcampos.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gabrielcampos.cursomc.domain.Categoria;
import com.gabrielcampos.cursomc.dto.CategoriaDTO;
import com.gabrielcampos.cursomc.repositories.CategoriaRepository;
import com.gabrielcampos.cursomc.services.exceptions.DataIntegrityException;
import com.gabrielcampos.cursomc.services.exceptions.ObjectNotFoundException;



@Service
public class CategoriaService {

	// Quando você cria uma dependencia com a anotaçao Autowired, essa dependência
	// vai ser automaticamente instanciada pelo spring pelo mecanismo de injeção de
	// dependencia ou inversão de controle
	@Autowired
	private CategoriaRepository repo;

	// implementação do serviço que busca uma categoria
	public Categoria find(Integer id) {
		
		Optional<Categoria> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
						"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	//implementacao do servico que insere uma categoria
	//setId null pra sempre inserir um novo
	public Categoria insert (Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	//implementacao do servico que atualiza uma categoria
	public Categoria update (Categoria obj) {
		//chamo o find pra buscar o objeto no banco e caso n exista ele já retorna a exceçao
		find(obj.getId());
		return repo.save(obj);
	}
	
	//implementacao do servico que deleta uma categoria pelo Id
	public void delete(Integer id) {
		find(id);
		try {
		repo.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	}
	
	public List<Categoria> findAll(){
		return repo.findAll();
	}
	
	//page vai capturar e encapsular informações sobre a paginação
	//linesperpage quantas linhas por páginas eu quero
	//O properties é o que a gente vai utilizar para ordenar
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repo.findAll(pageRequest);
		
	}
	
	//método auxiliar que instancia uma categoria a partir de um DTO
	public Categoria fromDTO(CategoriaDTO objDTO) {
		return new Categoria(objDTO.getId(),objDTO.getNome());
	}

}
