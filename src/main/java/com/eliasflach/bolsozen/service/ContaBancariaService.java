package com.eliasflach.bolsozen.service;

import com.eliasflach.bolsozen.model.entity.ContaBancaria;

import java.util.List;

public interface ContaBancariaService {
    ContaBancaria createContaBancaria(ContaBancaria contaBancaria);
    ContaBancaria updateContaBancaria(String id, ContaBancaria contaBancaria);
    List<ContaBancaria> findContasBancariasByUsuario(String idUsuario);
    ContaBancaria findContaBancariaById(String id);
    void deleteContaBancaria(String id);
}