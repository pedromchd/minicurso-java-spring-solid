package br.edu.ifrs.minicurso.springsolidapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifrs.minicurso.springsolidapi.models.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
    
}
