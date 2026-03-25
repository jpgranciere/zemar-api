package com.zemar_api.domain.produto;

public record DadosAtualizarProduto(Long id ,String nomeProduto, String codigoProduto, Categoria categoria, String imagemUrl, String descricao) {
}
