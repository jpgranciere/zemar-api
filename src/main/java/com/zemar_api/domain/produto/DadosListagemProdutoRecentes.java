package com.zemar_api.domain.produto;

public record DadosListagemProdutoRecentes(Long id, String nomeProduto, String codigoProduto, String imagemUrl) {

    public DadosListagemProdutoRecentes(Produto produto) {
        this(produto.getId(), produto.getNomeProduto(), produto.getCodigoProduto(), produto.getImagemUrl());
    }
}
