package com.gabrielcampos.cursomc.domain.enums;

public enum TipoCliente {
	
	PESSOAFISICA(1,"Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	private int cod;
	private String descricao;
	
	private TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	//É static porque essa operação vai ser possível sem precisar instanciar objetos
	public static TipoCliente toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		//Esse é um for que percorre todos os valores do tipo cliente
		for(TipoCliente x : TipoCliente.values()) {
			//apos percorrer os valores se o código x for igual ao codigo cod que capturamos
			//entao retorna o x
			if(cod.equals(x.getCod())){
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
