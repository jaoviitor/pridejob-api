package com.project.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.project.api.model.EmpresaModel;

public interface EmpresaRepository extends CrudRepository<EmpresaModel, Integer> {

    // Listar todas as empresas
    List<EmpresaModel> findAll();

    // Pesquisar por ID
    EmpresaModel findByIdEmpresa(int idEmpresa);

    // Cadastrar / Alterar empresas
    <EmpMod extends EmpresaModel> EmpMod save(EmpMod empresa);

    // Remover empresas
    void delete(EmpresaModel empresa);

    // Login da empresa
    public Optional<EmpresaModel> findByEmail(String email);
    
}
