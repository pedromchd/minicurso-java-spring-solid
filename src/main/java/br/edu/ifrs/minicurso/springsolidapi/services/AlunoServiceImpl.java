package br.edu.ifrs.minicurso.springsolidapi.services;

import java.time.Year;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrs.minicurso.springsolidapi.dtos.AlunoDTO;
import br.edu.ifrs.minicurso.springsolidapi.models.Aluno;
import br.edu.ifrs.minicurso.springsolidapi.repositories.AlunoRepository;
import br.edu.ifrs.minicurso.springsolidapi.services.exceptions.NotFoundException;
import br.edu.ifrs.minicurso.springsolidapi.services.interfaces.AlunoService;

@Service
public class AlunoServiceImpl implements AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    private final Random random = new Random();

    @Override
    public List<Aluno> getAll() {
        return alunoRepository.findAll();
    }

    @Override
    public Aluno getById(Integer id) {
        return alunoRepository.findById(id).orElseThrow(() -> new NotFoundException("Aluno não encontrado.", id));
    }

    @Override
    public Aluno save(AlunoDTO alunoDto) {
        Aluno aluno = new Aluno();
        aluno.setNome(alunoDto.nome());
        aluno.setSobrenome(alunoDto.sobrenome());

        String matricula = generateMatricula();
        while (alunoRepository.existsByMatricula(matricula)) {
            matricula = generateMatricula();
        }

        aluno.setMatricula(matricula);
        aluno.setEmail(generateEmail(alunoDto.nome(), alunoDto.sobrenome()));
        return alunoRepository.save(aluno);
    }

    private String generateMatricula() {
        return String.format("%04d%06d", Year.now().getValue(), random.nextInt(1000000));
    }

    private String generateEmail(String nome, String sobrenome) {
        return String.format("%s.%s@aluno.riogrande.ifrs.edu.br", nome.toLowerCase(), sobrenome.toLowerCase());
    }

    // Sobrecarga do método de salvar aluno.
    @Override
    public Aluno save(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    @Override
    public Aluno update(Integer id, AlunoDTO alunoDto) {
        Aluno aluno = getById(id);
        aluno.setNome(alunoDto.nome());
        aluno.setSobrenome(alunoDto.sobrenome());
        aluno.setEmail(generateEmail(alunoDto.nome(), alunoDto.sobrenome()));
        return alunoRepository.save(aluno);
    }

    @Override
    public boolean delete(Integer id) {
        if (alunoRepository.existsById(id)) {
            alunoRepository.deleteById(id);
            return true;
        } else {
            throw new NotFoundException(id);
        }
    }
    
}
