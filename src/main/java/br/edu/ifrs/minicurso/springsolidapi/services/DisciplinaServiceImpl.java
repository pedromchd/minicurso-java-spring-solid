package br.edu.ifrs.minicurso.springsolidapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrs.minicurso.springsolidapi.dtos.DisciplinaDTO;
import br.edu.ifrs.minicurso.springsolidapi.models.Disciplina;
import br.edu.ifrs.minicurso.springsolidapi.repositories.DisciplinaRepository;
import br.edu.ifrs.minicurso.springsolidapi.services.exceptions.NotFoundException;
import br.edu.ifrs.minicurso.springsolidapi.services.interfaces.DisciplinaService;

@Service
public class DisciplinaServiceImpl implements DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Override
    public List<Disciplina> getAll() {
        return disciplinaRepository.findAll();
    }

    @Override
    public Disciplina getById(Integer id) {
        return disciplinaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Disciplina não encontrada no banco de dados.", id));
    }

    @Override
    public Disciplina save(DisciplinaDTO disciplinaDto) {
        Disciplina disciplina = new Disciplina();
        disciplina.setNome(disciplinaDto.nome());
        disciplina.setSemestre(disciplinaDto.semestre());
        return disciplinaRepository.save(disciplina);
    }

    @Override
    public Disciplina update(Integer id, DisciplinaDTO disciplinaDto) {
        Disciplina disciplina = getById(id);
        disciplina.setNome(disciplinaDto.nome());
        disciplina.setSemestre(disciplinaDto.semestre());
        return disciplinaRepository.save(disciplina);
    }

    @Override
    public boolean delete(Integer id) {
        if (disciplinaRepository.existsById(id)) {
            disciplinaRepository.deleteById(id);
            return true;
        } else {
            throw new NotFoundException(id);
        }
    }

}