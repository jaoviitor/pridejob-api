package com.project.api.model;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="vaga")
@Access(AccessType.FIELD)
public class VagaModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long idVaga;
	
	@Column(columnDefinition = "VARCHAR(60)")
	private String nome;
	
	@Column(columnDefinition = "VARCHAR(150)")
	private String descricao;
	
	@Column(columnDefinition = "VARCHAR(60)")
	private String categoria;
	
	@Column(columnDefinition = "VARCHAR(60)")
	private String localizacao;
	
	@Column(columnDefinition = "VARCHAR(100)")
	private String requisitos;
	
	@Column(columnDefinition = "DOUBLE")
	private double salario;
	
	@ManyToOne
	@JoinColumn(name = "idEmpresa", nullable = false)
	private EmpresaModel empresa;

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
	
	public EmpresaModel getEmpresa() {
		return empresa;
	}
	
	public void setEmpresa(EmpresaModel empresa) {
		this.empresa = empresa;
	}
	
	
}
