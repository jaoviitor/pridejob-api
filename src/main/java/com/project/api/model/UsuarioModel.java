package com.project.api.model;

import java.sql.Date;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="usuario")
@Access(AccessType.FIELD)
public class UsuarioModel{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long idUsuario;
	
	@Column(columnDefinition = "VARCHAR(60)")
	private String nome;
	
	@Column(columnDefinition = "VARCHAR(60)")
	private String nomeSocial;
	
	@Column(columnDefinition = "VARCHAR(14)", unique = true)
	private Long cpf;
	
	@Column(columnDefinition = "DATE")
	private Date dtNascimento;
	
	@Column(columnDefinition = "VARCHAR(8)")
	private String cep;
	
	@Column(columnDefinition = "VARCHAR(20)")
	private String telefone;
	
	@Column(columnDefinition = "VARCHAR(60)", unique = true)
	private String email;
	
	@Column(columnDefinition = "VARCHAR(60)")
	private String senha;
	
	private boolean activated;
	
	private String activationToken;
	
	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeSocial() {
		return nomeSocial;
	}

	public void setNomeSocial(String nomeSocial) {
		this.nomeSocial = nomeSocial;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public Date getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isActivated() {
		return activated;
	}
	
	public void setActivated(boolean activated) {
		this.activated = activated;
	}
	
	public String getActivationToken() {
        return activationToken;
    }
	
	public void setActivationToken(String activationToken) {
        this.activationToken = activationToken;
    }
	
}