package com.imd.entrevista.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.imd.entrevista.domain.Tarefa;
import com.imd.entrevista.exceptions.ObjectNotFoundException;
import com.imd.entrevista.repositories.TarefaRepository;

@Service
public class TarefaService {
	
	@Autowired
	private TarefaRepository tarefarepo;
	
	public Tarefa find(Integer id) {
		
		Optional<Tarefa> obj = tarefarepo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Tarefa.class.getName()));
		
	}
	
	public List<Tarefa> findTitulo(String titulo){
		
		List<Tarefa> obj = tarefarepo.findByTitulo(titulo);
		return obj;
		
	}
	
	public Tarefa insert(Tarefa obj) {
		obj.setId(null);
		return tarefarepo.save(obj);
	}
	
	public Tarefa update(Tarefa obj) {
		find(obj.getId()); //verifica se realmente existe
		return tarefarepo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id); //verifica se realmente existe
		tarefarepo.deleteById(id);
	}
	public List<Tarefa> findAll(){
		return tarefarepo.findAll();
	}
	
	public Page<Tarefa> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy ); 
		return tarefarepo.findAll(pageRequest);
	}
}
