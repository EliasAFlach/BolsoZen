package com.eliasflach.bolsozen.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelatorioGastosPorCategoriaDTO {

    private String idCategoria;
    private String nomeCategoria;
    private BigDecimal totalGasto;
    private long quantidadeTransacoes;

}