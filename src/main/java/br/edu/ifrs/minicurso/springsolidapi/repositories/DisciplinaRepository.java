package br.edu.ifrs.minicurso.springsolidapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifrs.minicurso.springsolidapi.models.Disciplina;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer> {
    
}
