package com.eliasflach.bolsozen.model.entity;

import com.eliasflach.bolsozen.model.enums.TipoTransacao;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "transacoes")
public class Transacao {

    @Id
    private String id;

    private String descricao;
    private BigDecimal valor;
    private LocalDate data;
    private TipoTransacao tipo;
    private Boolean paga;
    private String idUsuario;
    private String idCategoria;
    private String idContaBancaria;
    private String idCartaoCredito;
}