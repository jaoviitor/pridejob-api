package com.project.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.project.api.model.UsuarioModel;

public interface UsuarioRepository extends CrudRepository<UsuarioModel, Integer>{
	
	//Listar todos os usuários
	List<UsuarioModel> findAll();
	
	//Pesquisar por ID
	UsuarioModel findByIdUsuario(int IdUsuario);
	
	//Cadastrar / Alterar usuários
	<UserMod extends UsuarioModel> UserMod save(UserMod usuario);
	
	//Remover usuários
	void delete(UsuarioModel usuario);
	
	//Login do usuario
	public Optional<UsuarioModel> findByEmail(String email); //vai buscar no banco um valor especifico da coluna email
	
}
