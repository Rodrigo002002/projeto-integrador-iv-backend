package com.apiathletevision.apiathletevision.dtos.entities;

import com.apiathletevision.apiathletevision.dtos.groups.AppGroup;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AlunoDTO extends UsuarioDTO {

    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class})
    private TurmaDTO turma;

    @JsonView({AppGroup.Request.class})
    private Integer turmaId;

    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class, AppGroup.Request.class})
    private List<DocumentoDTO> documentos;

    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class})
    private PlanoDTO plano;

    @JsonView({AppGroup.Request.class})
    private Integer planoId;

    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class})
    private List<ServicoDTO> servicos;

    @JsonView({AppGroup.Request.class})
    private List<Integer> servicosIds;

    @JsonView({AppGroup.Response.class, AppGroup.ResponsePage.class})
    private List<PagamentoDTO> pagamentos;
}