package com.project.api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.project.api.model.VagaModel;

public interface VagaRepository extends CrudRepository<VagaModel, Integer>{
	
	//Listar todas as vagas
	List<VagaModel> findAll();
	
	//Pesquisar por ID
	VagaModel findByIdVaga(int idVaga);
	
	//Cadastrar / alterar vagas
	<EmpMod extends VagaModel> EmpMod save(EmpMod vaga);
	
	//Remover vagas
	void delete(VagaModel vaga);

}
