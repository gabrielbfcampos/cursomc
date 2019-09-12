package com.gabrielcampos.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabrielcampos.cursomc.domain.Pedido;

//A interface JpaRepository é capaz de acessar os dados com base em um tipo que a gente passa, 
//no caso Pedido e tambem tem que se colocar o tipo do atributo identificador que no caso 
//é o Integer
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
