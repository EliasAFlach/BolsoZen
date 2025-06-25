package com.eliasflach.bolsozen.service.impl;

import com.eliasflach.bolsozen.exceptions.BusinessException;
import com.eliasflach.bolsozen.exceptions.ResourceNotFoundException;
import com.eliasflach.bolsozen.model.entity.ContaBancaria;
import com.eliasflach.bolsozen.repository.ContaBancariaRepository;
import com.eliasflach.bolsozen.service.ContaBancariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaBancariaServiceImpl implements ContaBancariaService {

    @Autowired
    private ContaBancariaRepository contaBancariaRepository;

    @Override
    public ContaBancaria createContaBancaria(ContaBancaria contaBancaria) {
        contaBancariaRepository.findByNomeAndIdUsuario(contaBancaria.getNome(), contaBancaria.getIdUsuario())
                .ifPresent(c -> {
                    throw new BusinessException("Já existe uma conta com este nome.");
                });
        return contaBancariaRepository.save(contaBancaria);
    }

    @Override
    public ContaBancaria updateContaBancaria(String id, ContaBancaria contaBancariaDetails) {
        ContaBancaria contaBancaria = findContaBancariaById(id);

        contaBancaria.setNome(contaBancariaDetails.getNome());
        contaBancaria.setInstituicao(contaBancariaDetails.getInstituicao());
        contaBancaria.setSaldo(contaBancariaDetails.getSaldo());
        return contaBancariaRepository.save(contaBancaria);
    }

    @Override
    public List<ContaBancaria> findContasBancariasByUsuario(String idUsuario) {
        return contaBancariaRepository.findByIdUsuario(idUsuario);
    }

    @Override
    public ContaBancaria findContaBancariaById(String id) {
        return contaBancariaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ContaBancaria não encontrado com o id: " + id));
    }

    @Override
    public void deleteContaBancaria(String id) {
        ContaBancaria contaBancaria = findContaBancariaById(id);
        contaBancariaRepository.delete(contaBancaria);
    }
}