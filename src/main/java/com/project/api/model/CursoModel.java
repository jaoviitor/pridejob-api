package com.project.api.model;

import jakarta.persistence.*;

@Entity
@Table(name="curso")
@Access(AccessType.FIELD)
public class CursoModel {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long idCurso;
    
    @Column(columnDefinition = "VARCHAR(100)")
    private String nome;
    
    @Column(columnDefinition = "VARCHAR(255)")
    private String descricao;
    
    @Column(columnDefinition = "VARCHAR(60)")
    private String categoria;
    
    @Column(columnDefinition = "INTEGER")
    private Double cargaHoraria;
    
    @Column(columnDefinition = "INTEGER")
    private Double totalAulas;
    
    public Long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Double getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Double cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Double getTotalAulas() {
        return totalAulas;
    }

    public void setTotalAulas(Double totalAulas) {
        this.totalAulas = totalAulas;
    }
    
}
