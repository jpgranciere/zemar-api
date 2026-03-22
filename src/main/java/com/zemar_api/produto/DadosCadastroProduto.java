package com.zemar_api.produto;

public record DadosCadastroProduto(String nomeProduto, String codigoProduto, Categoria categoria, String descricao, String imagemUrl) {
}
