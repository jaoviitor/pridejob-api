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

import com.project.api.model.EmpresaModel;
import com.project.api.model.ResponseModel;
import com.project.api.repository.EmpresaRepository;

@RestController
@RequestMapping("/api/empresa")
public class EmpresaController {
    
    @Autowired
    private final PasswordEncoder encoder;
    
    @Autowired
    private EmpresaRepository empresaRepository;
    
    public EmpresaController(PasswordEncoder encoder) {
        this.encoder = encoder;
    }
    
    // Ações
    @Autowired
    private EmpresaRepository actions;
    
    // Listar empresas
    @GetMapping("")
    public @ResponseBody List<EmpresaModel> listar() {
        return actions.findAll();
    }
    
    // Filtrar empresa
    @GetMapping("/{idEmpresa}")
    public @ResponseBody EmpresaModel filtrar(@PathVariable int idEmpresa) {
        return actions.findByIdEmpresa(idEmpresa);
    }
    
    // Cadastrar empresas
    @PostMapping("")
    public @ResponseBody EmpresaModel cadastrar(@RequestBody EmpresaModel empresa) {
        String senhaCriptografada = encoder.encode(empresa.getSenha());
        empresa.setSenha(senhaCriptografada);
        return actions.save(empresa);
    }
    
    // Editar empresa
    @PutMapping("/{idEmpresa}")
    public ResponseEntity<?> alterar(@PathVariable int idEmpresa, @RequestBody EmpresaModel empresaAtualizada) {
        Optional<EmpresaModel> empresaOptional = empresaRepository.findById(idEmpresa);
        if(empresaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empresa não encontrada");
        }
        EmpresaModel empresa = empresaOptional.get();
        
        if (empresaAtualizada.getNomeEmpresa() != null) {
            empresa.setNomeEmpresa(empresaAtualizada.getNomeEmpresa());
        }
        if (empresaAtualizada.getCnpj() != null) {
            empresa.setCnpj(empresaAtualizada.getCnpj());
        }
        if (empresaAtualizada.getEmail() != null) {
            empresa.setEmail(empresaAtualizada.getEmail());
        }
        if (empresaAtualizada.getEndereco() != null) {
            empresa.setEndereco(empresaAtualizada.getEndereco());
        }
        if (empresaAtualizada.getNomeResponsavel() != null) {
            empresa.setNomeResponsavel(empresaAtualizada.getNomeResponsavel());
        }
        if (empresaAtualizada.getCpfResponsavel() != null) {
            empresa.setCpfResponsavel(empresaAtualizada.getCpfResponsavel());
        }
        if (empresaAtualizada.getTelResponsavel() != null) {
            empresa.setTelResponsavel(empresaAtualizada.getTelResponsavel());
        }
        if (empresaAtualizada.getSenha() != null) {
            String senhaCriptografada = encoder.encode(empresaAtualizada.getSenha());
            empresa.setSenha(senhaCriptografada);
        }
        
        empresa = empresaRepository.save(empresa);
        
        return ResponseEntity.ok(empresa);
    }
    
    // Deletar empresa
    @DeleteMapping("/{idEmpresa}")
    public @ResponseBody ResponseModel remover(@PathVariable int idEmpresa) {
        ResponseModel response = new ResponseModel();
        
        try {
            EmpresaModel empresa = filtrar(idEmpresa);
            this.actions.delete(empresa);
            response.setMessage("Empresa removida com sucesso");
        }catch(Exception erro) {
            response.setMessage("Falha ao remover: " + erro.getMessage());
        }
        return response;
    }
    
    // Login da empresa
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody EmpresaModel empresaLogin) {
        Optional<EmpresaModel> empresaOptional = empresaRepository.findByEmail(empresaLogin.getEmail());
        if(empresaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email não encontrado");
        }
        EmpresaModel empresa = empresaOptional.get();
        if (!encoder.matches(empresaLogin.getSenha(), empresa.getSenha())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha inválida");
        }
        return ResponseEntity.ok("Login bem-sucedido");
    }
}
