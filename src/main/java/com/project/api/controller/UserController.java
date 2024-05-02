package com.project.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.api.model.ResponseModel;
import com.project.api.model.UsuarioModel;
import com.project.api.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/usuario")
public class UserController {
	
	@Autowired
	private final PasswordEncoder encoder;
	
	@Autowired
    private UsuarioRepository usuarioRepository;
	
	
	public UserController(PasswordEncoder encoder) {
		this.encoder = encoder;
	}

	//Ações
	@Autowired
	private UsuarioRepository actions;
	
	
	//Listar usuários
	@GetMapping("")
	public @ResponseBody List<UsuarioModel> listar() {
		return actions.findAll();
	}
	
	//Filtrar usuário
	@GetMapping("/{idUsuario}")
	public @ResponseBody UsuarioModel filtrar(@PathVariable Integer idUsuario) {
		return actions.findByIdUsuario(idUsuario);
	}
	
	// Cadastrar usuarios
	@PostMapping("")
	public @ResponseBody UsuarioModel cadastrar(@RequestBody UsuarioModel usuario) {
		String senhaCriptografada = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaCriptografada);
		return actions.save(usuario);
	}
	
	//Editar usuário
	@PutMapping("/{idUsuario}")
	public ResponseEntity<?> alterar(@PathVariable int idUsuario, @RequestBody UsuarioModel usuarioAtualizado) {
	    Optional<UsuarioModel> usuarioOptional = usuarioRepository.findById(idUsuario);
	    if(usuarioOptional.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
	    }
	    UsuarioModel usuario = usuarioOptional.get();
	    
	    if (usuarioAtualizado.getNome() != null) {
	        usuario.setNome(usuarioAtualizado.getNome());
	    }
	    if (usuarioAtualizado.getNomeSocial() != null) {
	        usuario.setNomeSocial(usuarioAtualizado.getNomeSocial());
	    }
	    if (usuarioAtualizado.getCpf() != null) {
	        usuario.setCpf(usuarioAtualizado.getCpf());
	    }
	    if (usuarioAtualizado.getDtNascimento() != null) {
	        usuario.setDtNascimento(usuarioAtualizado.getDtNascimento());
	    }
	    if (usuarioAtualizado.getCep() != null) {
	        usuario.setCep(usuarioAtualizado.getCep());
	    }
	    if (usuarioAtualizado.getTelefone() != null) {
	        usuario.setTelefone(usuarioAtualizado.getTelefone());
	    }
	    if (usuarioAtualizado.getEmail() != null) {
	        usuario.setEmail(usuarioAtualizado.getEmail());
	    }
	    if (usuarioAtualizado.getSenha() != null) {
	        String senhaCriptografada = encoder.encode(usuarioAtualizado.getSenha());
	        usuario.setSenha(senhaCriptografada);
	    }
	    
	    usuario = usuarioRepository.save(usuario);
	    
	    return ResponseEntity.ok(usuario);
	}
	
	//Deletar usuário
	@DeleteMapping("/{idUsuario}")
	public @ResponseBody ResponseModel remover(@PathVariable int idUsuario) {
		ResponseModel response = new ResponseModel();
		
		try {
			UsuarioModel usuario = filtrar(idUsuario);
			this.actions.delete(usuario);
			response.setMessage("Usuário removido com sucesso");
		}catch(Exception erro) {
			response.setMessage("Falha ao remover: " + erro.getMessage());
		}
		return response;
	}
	
	//Validar senha
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UsuarioModel usuarioLogin) {
		Optional<UsuarioModel> usuarioOptional = usuarioRepository.findByEmail(usuarioLogin.getEmail());
		if(usuarioOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email não encontrado");
		}
		UsuarioModel usuario = usuarioOptional.get();
		if (!encoder.matches(usuarioLogin.getSenha(), usuario.getSenha())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha inválida");
        }
		return ResponseEntity.ok("Login bem-sucedido");
	}
	
	
	
}
