package com.eliasflach.bolsozen.model.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "usuarios")
public class Usuario {

    @Id
    private String id;

    private String nome;
    private String email;
    private String senha;

    private LocalDateTime deletadoEm;
}