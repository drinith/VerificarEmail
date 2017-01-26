package br.drinith.teste;

public class Email {

	String nome;
	
	String endereco;
	boolean existe;
	
	public Email(String nome,String endereco) {
		super();
		this.nome = nome;
		this.endereco = endereco;
		
	}
	
	
	public Email(String endereco, boolean existe) {
		super();
		this.endereco = endereco;
		this.existe = existe;
	}
	
	
	public Email(String endereco) {
		super();
		this.endereco = endereco;
		
	}
	
	public Email(String nome2, String endereco2, boolean addressValid) {
		// TODO Auto-generated constructor stub
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public boolean isExiste() {
		return existe;
	}
	public void setExiste(boolean existe) {
		this.existe = existe;
	}
	
	
	
	
}
