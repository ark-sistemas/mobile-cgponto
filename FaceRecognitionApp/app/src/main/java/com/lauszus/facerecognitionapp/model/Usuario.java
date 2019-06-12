package com.lauszus.facerecognitionapp.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    private String login;

    private String senha;

    private String nome;

    private Date dataAlteracaoSenha;

    private Date dataCriacao;

    private Date dataUltimoAcesso;

    private boolean ativo;
}
