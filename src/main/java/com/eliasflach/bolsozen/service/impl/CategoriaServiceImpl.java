package com.eliasflach.bolsozen.service.impl;

import com.eliasflach.bolsozen.exceptions.BusinessException;
import com.eliasflach.bolsozen.exceptions.ResourceNotFoundException;
import com.eliasflach.bolsozen.model.entity.Categoria;
import com.eliasflach.bolsozen.repository.CategoriaRepository;
import com.eliasflach.bolsozen.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public Categoria createCategoria(Categoria categoria) {
        categoriaRepository.findByNomeAndIdUsuario(categoria.getNome(), categoria.getIdUsuario())
                .ifPresent(c -> {
                    throw new BusinessException("Já existe uma categoria com este nome.");
                });
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria updateCategoria(String id, Categoria categoriaDetails) {
        Categoria categoria = findCategoriaById(id);

        categoria.setNome(categoriaDetails.getNome());
        categoria.setIconeIdentificador(categoriaDetails.getIconeIdentificador());
        return categoriaRepository.save(categoria);
    }

    @Override
    public List<Categoria> findCategoriasByUsuario(String idUsuario) {
        return categoriaRepository.findByIdUsuario(idUsuario);
    }

    @Override
    public Categoria findCategoriaById(String id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrado com o id: " + id));
    }

    @Override
    public void deleteCategoria(String id) {
        Categoria categoria = findCategoriaById(id);
        categoriaRepository.delete(categoria);
    }
}