package com.project.api.dto;

public class VagaResponse {

	private Long idVaga;
    private String nome;
    private String descricao;
    private String categoria;
    private String localizacao;
    private String requisitos;
    private double salario;
    private Long idEmpresa;
    
    public VagaResponse(Long idVaga, String nome, String descricao, String categoria, String localizacao, String requisitos, double salario, Long idEmpresa) {
    	this.idVaga = idVaga;
    	this.nome = nome;
    	this.descricao = descricao;
    	this.categoria = categoria;
    	this.localizacao = localizacao;
    	this.requisitos = requisitos;
    	this.salario = salario;
    	this.idEmpresa = idEmpresa;
    }

	public Long getIdVaga() {
		return idVaga;
	}

	public void setIdVaga(Long idVaga) {
		this.idVaga = idVaga;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public String getRequisitos() {
		return requisitos;
	}

	public void setRequisitos(String requisitos) {
		this.requisitos = requisitos;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
    
    
	
}
