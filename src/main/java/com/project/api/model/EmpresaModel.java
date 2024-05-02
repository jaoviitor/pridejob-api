package com.project.api.model;

import jakarta.persistence.*;

@Entity
@Table(name="empresa")
@Access(AccessType.FIELD)
public class EmpresaModel {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long idEmpresa;

    @Column(columnDefinition = "VARCHAR(14)")
    private String cnpj;
    
    @Column(columnDefinition = "VARCHAR(50)")
    private String nomeEmpresa;

    @Column(columnDefinition = "VARCHAR(60)")
    private String email;

    @Column(columnDefinition = "VARCHAR(100)")
    private String endereco;

    @Column(columnDefinition = "VARCHAR(60)")
    private String nomeResponsavel;

    @Column(columnDefinition = "VARCHAR(14)")
    private Long cpfResponsavel;

    @Column(columnDefinition = "VARCHAR(20)")
    private String telResponsavel;

    @Column(columnDefinition = "VARCHAR(60)")
    private String senha;

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public Long getCpfResponsavel() {
		return cpfResponsavel;
	}

	public void setCpfResponsavel(Long cpfResponsavel) {
		this.cpfResponsavel = cpfResponsavel;
	}

	public String getTelResponsavel() {
		return telResponsavel;
	}

	public void setTelResponsavel(String telResponsavel) {
		this.telResponsavel = telResponsavel;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

    
}
