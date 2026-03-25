package com.zemar_api.domain.produto;

public record DadosDetalhamentoProduto(Long id, String nomeProduto, String codigoProduto, Categoria categoria, String imagemUrl, String descricao) {

    public DadosDetalhamentoProduto(Produto produto) {
        this(produto.getId(), produto.getNomeProduto(), produto.getCodigoProduto(), produto.getCategoria(), produto.getImagemUrl(), produto.getDescricao());
    }
}
