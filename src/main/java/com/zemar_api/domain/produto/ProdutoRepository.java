package com.zemar_api.domain.produto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Page<DadosDetalhamentoProduto> findByCategoria(Categoria categoria, Pageable paginacao);

    Page<DadosDetalhamentoProduto> findByNomeProdutoContainingIgnoreCase(String nomeProd, Pageable paginacao);
}
