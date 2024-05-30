package com.project.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.api.dto.VagaResponse;
import com.project.api.model.VagaModel;
import com.project.api.repository.VagaRepository;

@RestController
@RequestMapping("/api/vaga")
public class VagaController {
	
	@Autowired
	private VagaRepository vagaRepository;
	
	@GetMapping("")
	public List<VagaResponse> listar(){
		List<VagaModel> vagas = vagaRepository.findAll();
		return vagas.stream().map(this::convertToVagaResponse).collect(Collectors.toList());
	}
	
	private VagaResponse convertToVagaResponse(VagaModel vaga) {
		return new VagaResponse(
				vaga.getIdVaga(), 
				vaga.getNome(), 
				vaga.getDescricao(), 
				vaga.getCategoria(),
				vaga.getLocalizacao(),
				vaga.getRequisitos(),
				vaga.getSalario(),
				vaga.getEmpresa().getIdEmpresa()
		);
	}

}
