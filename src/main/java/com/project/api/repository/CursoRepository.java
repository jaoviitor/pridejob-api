package com.project.api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.project.api.model.CursoModel;

public interface CursoRepository extends CrudRepository<CursoModel, Integer> {
    
    // Listar todos os cursos
    List<CursoModel> findAll();
    
    // Pesquisar por ID
    CursoModel findByIdCurso(int idCurso);
    
    // Cadastrar / Alterar cursos
    <CourseMod extends CursoModel> CourseMod save(CourseMod curso);
    
    // Remover cursos
    void delete(CursoModel curso);
}