package com.imd.entrevista.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.imd.entrevista.domain.Tarefa;
import com.imd.entrevista.services.TarefaService;

@RestController
@RequestMapping(value="/tarefas")
public class TarefaResource {
	
	@Autowired
	private TarefaService tarefaserv;
	
	/*End point para buscar uma tarefa especifica*/
	@GetMapping(value="/buscar")
	public ResponseEntity<?> buscar(@RequestParam Integer id) {
		
		Tarefa obj = tarefaserv.find(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value="/buscarnome")
	public ResponseEntity<List<Tarefa>> buscarTitulo(@RequestParam String titulo) {
		
		System.out.println("titulo "+titulo);
		List<Tarefa> list = tarefaserv.findTitulo(titulo);
		
		return ResponseEntity.ok().body(list);
				//ResponseEntity.ok().body(obj);
	}
	
	/*End point para inserir uma tarefa*/
	@PostMapping("/inserir")
	public ResponseEntity<Void> inserir(@RequestBody Tarefa obj) {
		
		//System.out.println("O que veio da requisição: "+ obj.getData());
		obj = tarefaserv.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(obj.getId()).toUri(); 
		
		return ResponseEntity.created(uri).build();
	}
	
	/*End point para atualizar uma tarefa*/
	@PutMapping(value="/atualizar/{id}")
	public ResponseEntity<Void> atualizar(@RequestBody Tarefa obj, @PathVariable Integer id ) {
		obj = tarefaserv.update(obj);
		return ResponseEntity.noContent().build();
		
	}
	/*End point para deletar uma tarefa*/
	@DeleteMapping(value="/delete")
	public ResponseEntity<Void> Deletar(@RequestParam Integer id) {
		System.out.println("O id recebido "+ id);
		tarefaserv.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	/*End point para listar todas as tarefas*/
	@GetMapping(value="/listartudo")
	public ResponseEntity<List<Tarefa>> listar() {
		List <Tarefa> list = tarefaserv.findAll();
		return ResponseEntity.ok().body(list);
		
		}
	/*End point para listar de modo paginado*/
	@GetMapping(value="/page")
	public ResponseEntity<Page<Tarefa>> listarPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="prioridade") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page <Tarefa> list = tarefaserv.findPage(page,linesPerPage,orderBy,direction);
		return ResponseEntity.ok().body(list);
		
		}
	}
		
		


