package br.edu.ifrs.minicurso.springsolidapi.services.interfaces;

import java.util.List;

public interface CrudService<Model, DTO> {
    
    List<Model> getAll();

    Model getById(Integer id);

    Model save(DTO dto);

    Model update(Integer id, DTO dto);

    boolean delete(Integer id);

}
