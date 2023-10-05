package br.edu.ifrs.minicurso.springsolidapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifrs.minicurso.springsolidapi.models.Turma;

public interface TurmaRepository extends JpaRepository<Turma, Integer> {
    
}
