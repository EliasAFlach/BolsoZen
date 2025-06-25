package com.eliasflach.bolsozen.model.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "categorias")
public class Categoria {

    @Id
    private String id;
    private String nome;
    private String iconeIdentificador;

    private String idUsuario;
    private String grupo;
}