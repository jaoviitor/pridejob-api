package com.project.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.project.api.model.CursoModel;
import com.project.api.model.EmpresaModel;
import com.project.api.model.ResponseModel;
import com.project.api.repository.CursoRepository;
import com.project.api.repository.EmpresaRepository;

@RestController
@RequestMapping("/api/curso")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;
    
    @Autowired
    private EmpresaRepository empresaRepository;

    // Listar todos os cursos
    @GetMapping("")
    public @ResponseBody List<CursoModel> listar() {
        return cursoRepository.findAll();
    }

    // Filtrar curso por ID
    @GetMapping("/{idCurso}")
    public @ResponseBody CursoModel filtrar(@PathVariable int idCurso) {
        return cursoRepository.findByIdCurso(idCurso);
    }

    // Cadastrar curso
    @PostMapping("")
    public @ResponseBody CursoModel cadastrar(@RequestBody CursoModel curso) {
        return cursoRepository.save(curso);
    }

    // Editar curso
    @PutMapping("/{idCurso}")
    public ResponseEntity<?> alterar(@PathVariable int idCurso, @RequestBody CursoModel cursoAtualizado) {
        Optional<CursoModel> cursoOptional = cursoRepository.findById(idCurso);
        if(cursoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso não encontrado");
        }
        CursoModel curso = cursoOptional.get();
        if (cursoAtualizado.getNome() != null) {
            curso.setNome(cursoAtualizado.getNome());
        }
        if (cursoAtualizado.getDescricao() != null) {
            curso.setDescricao(cursoAtualizado.getDescricao());
        }
        if (cursoAtualizado.getCategoria() != null) {
            curso.setCategoria(cursoAtualizado.getCategoria());
        }
        if (cursoAtualizado.getCargaHoraria() != null) {
            curso.setCargaHoraria(cursoAtualizado.getCargaHoraria());
        }
        if (cursoAtualizado.getTotalAulas() != null) {
            curso.setTotalAulas(cursoAtualizado.getTotalAulas());
        }

        return ResponseEntity.ok(cursoRepository.save(curso));
    }

    // Remover curso
    @DeleteMapping("/{idCurso}")
    public @ResponseBody ResponseModel remover(@PathVariable int idCurso) {
        ResponseModel response = new ResponseModel();
        try {
            CursoModel curso = filtrar(idCurso);
            cursoRepository.delete(curso);
            response.setMessage("Curso removido com sucesso");
        } catch (Exception erro) {
            response.setMessage("Falha ao remover curso: " + erro.getMessage());
        }
        return response;
    }
}