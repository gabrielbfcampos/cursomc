package com.gabrielcampos.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabrielcampos.cursomc.domain.Pagamento;

//A interface JpaRepository é capaz de acessar os dados com base em um tipo que a gente passa, 
//no caso Pagamento e tambem tem que se colocar o tipo do atributo identificador que no caso 
//é o Integer

//basta criar o repository da superclasse que abrange as subclasses
@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

}
