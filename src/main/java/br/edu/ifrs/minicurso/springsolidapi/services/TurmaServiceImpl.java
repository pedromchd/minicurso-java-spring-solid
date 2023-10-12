package br.edu.ifrs.minicurso.springsolidapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrs.minicurso.springsolidapi.dtos.TurmaDTO;
import br.edu.ifrs.minicurso.springsolidapi.models.Aluno;
import br.edu.ifrs.minicurso.springsolidapi.models.Disciplina;
import br.edu.ifrs.minicurso.springsolidapi.models.Turma;
import br.edu.ifrs.minicurso.springsolidapi.repositories.TurmaRepository;
import br.edu.ifrs.minicurso.springsolidapi.services.exceptions.NotFoundException;
import br.edu.ifrs.minicurso.springsolidapi.services.interfaces.AlunoService;
import br.edu.ifrs.minicurso.springsolidapi.services.interfaces.DisciplinaService;
import br.edu.ifrs.minicurso.springsolidapi.services.interfaces.TurmaService;

@Service
public class TurmaServiceImpl implements TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private DisciplinaService disciplinaService;

    @Autowired
    private AlunoService alunoService;

    @Override
    public List<Turma> getAll() {
        return turmaRepository.findAll();
    }

    @Override
    public Turma getById(Integer id) {
        return turmaRepository.findById(id).orElseThrow(() -> new NotFoundException("Turma não encontrada.", 404));
    }

    @Override
    public Turma save(TurmaDTO turmaDto) {
        Turma turma = new Turma();
        Disciplina disciplina = disciplinaService.getById(turmaDto.disciplina_id());

        turma.setNome(turmaDto.nome());
        turma.setDisciplina(disciplina);

        return turmaRepository.save(turma);
    }

    @Override
    public Turma update(Integer id, TurmaDTO turmaDto) {
        Turma turma = getById(id);
        Disciplina disciplina = disciplinaService.getById(turmaDto.disciplina_id());

        turma.setNome(turmaDto.nome());
        turma.setDisciplina(disciplina);

        return turmaRepository.save(turma);
    }

    @Override
    public boolean delete(Integer id) {
        if (turmaRepository.existsById(id)) {
            turmaRepository.deleteById(id);
            return true;
        } else {
            throw new NotFoundException(id);
        }
    }

    public Turma matricular(Integer turma_id, Integer aluno_id) {
        Aluno aluno = alunoService.getById(aluno_id);
        Turma turma = getById(turma_id);

        if (turma.getAlunos().contains(aluno)) {
            throw new IllegalArgumentException("Aluno já está inserido na turma.");
        }

        turma.getAlunos().add(aluno);
        aluno.getTurmas().add(turma);
        turmaRepository.save(turma);
        alunoService.save(aluno);
        return turma;
    }

    public Turma cancelarMatricula(Integer turma_id, Integer aluno_id) {
        Aluno aluno = alunoService.getById(aluno_id);
        Turma turma = getById(turma_id);

        if (!turma.getAlunos().contains(aluno)) {
            throw new NotFoundException("Aluno não pertence a turma.", aluno_id);
        }

        turma.getAlunos().remove(aluno);
        aluno.getTurmas().remove(turma);
        turmaRepository.save(turma);
        alunoService.save(aluno);
        return turma;
    }

}
