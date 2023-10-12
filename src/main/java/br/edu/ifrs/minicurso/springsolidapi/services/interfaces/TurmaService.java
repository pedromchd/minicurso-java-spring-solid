package br.edu.ifrs.minicurso.springsolidapi.services.interfaces;

import br.edu.ifrs.minicurso.springsolidapi.dtos.TurmaDTO;
import br.edu.ifrs.minicurso.springsolidapi.models.Turma;

public interface TurmaService extends CrudService<Turma, TurmaDTO> {

    Turma matricular(Integer turma_id, Integer aluno_id);

    Turma cancelarMatricula(Integer turma_id, Integer aluno_id);
    
}
