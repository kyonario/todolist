package com.imd.entrevista.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Tarefa implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //definindo a estratégia automática dos ids de tarefa
	private Integer  id;
	private String titulo;
	private String descricao;
	private String data;
	private Integer prioridade;
	
	
	/*Construtor vazio*/
	public Tarefa() {
		
	}
	
	/*Construtor com parametros*/
	public Tarefa(Integer id, String titulo, String descricao, String data, Integer prioridade) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.data = data;
		this.prioridade = prioridade;
	}


	/*Getters and Setters*/
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Integer getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}

	/*Hash Code and Equals find object only id*/
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarefa other = (Tarefa) obj;
		return Objects.equals(id, other.id);
	}
	
}
