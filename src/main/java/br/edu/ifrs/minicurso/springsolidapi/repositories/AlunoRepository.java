package br.edu.ifrs.minicurso.springsolidapi.repositories;

import br.edu.ifrs.minicurso.springsolidapi.models.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Aluno a WHERE a.matricula = :matricula")
    boolean existsByMatricula(@Param("matricula") String matricula);
    
}
