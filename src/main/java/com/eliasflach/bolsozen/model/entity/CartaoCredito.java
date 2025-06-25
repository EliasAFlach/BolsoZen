package com.eliasflach.bolsozen.model.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;

@Data
@Document(collection = "cartoes_de_credito")
public class CartaoCredito {

    @Id
    private String id;

    private String nome;
    private BigDecimal limite;
    private int diaFechamento;
    private int diaVencimento;
    private String idUsuario;
}