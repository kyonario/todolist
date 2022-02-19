package com.imd.entrevista.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.imd.entrevista.domain.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Integer>{
	List<Tarefa> findByTitulo(@Param("titulo") String titulo);

}
