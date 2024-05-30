package com.project.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
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

import com.project.api.dto.AuthRequest;
import com.project.api.dto.AuthResponse;
import com.project.api.model.ResponseModel;
import com.project.api.model.UsuarioModel;
import com.project.api.repository.UsuarioRepository;
import com.project.api.service.EmailService;
import com.project.api.service.MyUserDetailsService;
import com.project.api.util.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/usuario")
public class UserController {
	
	@Autowired
	private final PasswordEncoder encoder;
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
    private MyUserDetailsService userDetailsService;
	
	@Autowired
    private JwtUtil jwtUtil;
	
	@Autowired
    private UsuarioRepository usuarioRepository;
	
	@Autowired
	private EmailService emailService;
	
	
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
	public ResponseEntity<?> filtrar(@PathVariable Integer idUsuario) {
	    UsuarioModel usuario = actions.findByIdUsuario(idUsuario);
	    if (usuario != null) {
	        return ResponseEntity.ok(usuario);
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
	    }
	}

	
	// Cadastrar usuarios
	@PostMapping("")
	public ResponseEntity<?> cadastrar(@RequestBody UsuarioModel usuario) {
		UsuarioModel novoUsuario;
		Optional<UsuarioModel> usuarioOptional = usuarioRepository.findByEmail(usuario.getEmail());
		if(usuarioOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Já existe um usuário cadastrado com este email");
		}
		
		UsuarioModel usuarioExistente = actions.findByCpf(usuario.getCpf());
		if (usuarioExistente != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Já existe um usuário cadastrado com este CPF");
		}
		try {
			String senhaCriptografada = encoder.encode(usuario.getSenha());
			usuario.setSenha(senhaCriptografada);
			usuario.setActivated(false);
			usuario.setActivationToken(UUID.randomUUID().toString());
			novoUsuario = actions.save(usuario);	
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.badRequest().body("Email ou CPF já cadastrados");
		}
		String subject = "Bem-vindo ao PrideJob Connect!";
		String text = "Olá " + usuario.getNome() + ",\n\nObrigado por se cadastrar no PrideJob Connect.";
		emailService.sendEmail(usuario.getEmail(), subject, text);
		return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
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
	    	UsuarioModel usuarioExistente = actions.findByCpf(usuarioAtualizado.getCpf());
			if (usuarioExistente != null && usuarioExistente.getIdUsuario() != idUsuario) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Já existe um usuário cadastrado com este CPF");
			}
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
	    	usuarioOptional = usuarioRepository.findByEmail(usuarioAtualizado.getEmail());
			if(usuarioOptional.isPresent() && usuarioOptional.get().getIdUsuario() != idUsuario) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Já existe um usuário cadastrado com este email");
			}
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
			UsuarioModel usuario = actions.findByIdUsuario(idUsuario);
			if (usuario == null) {
				response.setMessage("Usuário não encontrado");
				return response;
			}
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
		final String jwt = jwtUtil.createToken(usuarioLogin.getEmail());
		AuthResponse authResponse = new AuthResponse(jwt, usuario.getIdUsuario(), usuario.getNome(), usuario.getEmail());
        return ResponseEntity.ok(authResponse);	
	}
	
	
	
}
