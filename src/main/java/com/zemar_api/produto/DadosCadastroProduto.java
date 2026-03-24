package com.zemar_api.produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroProduto(

        @NotBlank
        String nomeProduto,

        @NotBlank
        String codigoProduto,

        @NotNull
        Categoria categoria,

        @NotBlank
        String descricao,

        String imagemUrl
    ) {
}
