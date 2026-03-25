package com.zemar_api.domain.produto;

public record DadosListagemProduto(Long id ,String nomeProduto, String codigoProduto, String imagemUrl) {

    public DadosListagemProduto(Produto produto){
        this(produto.getId() ,produto.getNomeProduto(), produto.getCodigoProduto(), produto.getImagemUrl());
    }
}
