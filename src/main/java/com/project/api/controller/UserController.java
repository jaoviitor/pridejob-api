package com.project.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.api.model.UsuarioModel;
import com.project.api.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/usuario")
public class UserController {
	
	//Ações
	@Autowired
	private UsuarioRepository actions;
	
	
	//Listar Usuários
	@GetMapping("")
	public @ResponseBody List<UsuarioModel> listar() {
		return actions.findAll();
	}
	
	// Cadastrar usuarios
	@PostMapping("")
	public @ResponseBody UsuarioModel cadastrar(@RequestBody UsuarioModel usuario) {
		return actions.save(usuario);
	}
	
	
	
}
