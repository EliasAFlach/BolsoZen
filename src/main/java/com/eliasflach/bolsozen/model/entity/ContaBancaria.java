package com.eliasflach.bolsozen.model.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;

@Data
@Document(collection = "contas_bancarias")
public class ContaBancaria {

    @Id
    private String id;
    private String nome;
    private String instituicao;
    private BigDecimal saldo;
    private String idUsuario;
}