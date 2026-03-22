package com.zemar_api.produto;

public record DadosAtualizarProduto(Long id ,String nomeProduto, String codigoProduto, Categoria categoria, String imagemUrl, String descricao) {
}
