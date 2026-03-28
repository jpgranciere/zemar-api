package com.zemar_api.controller;

import com.zemar_api.domain.produto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @GetMapping
    public ResponseEntity<Page<DadosListagemProduto>> listar(@PageableDefault(size = 10, sort = "nomeProduto") Pageable paginacao){
        var page =  repository.findAll(paginacao).map(DadosListagemProduto::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity listarProdutoDetalhado(@PathVariable Long id){
        var produto = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoProduto(produto));
    }

    @GetMapping(value = "/categoria")
    public ResponseEntity<Categoria[]> listarCategoria(){
        Categoria[] categorias = Categoria.values();

        return ResponseEntity.ok(categorias);
    }

    @GetMapping(value = "/categoria/{categoria}")
    public ResponseEntity<Page<DadosDetalhamentoProduto>> getPorCategoria(@PathVariable String categoria, Pageable paginacao){
        Categoria cat = Categoria.valueOf(categoria.toUpperCase());

        var categoriaFormatada = repository.findByCategoria(cat, paginacao);

        return ResponseEntity.ok(categoriaFormatada);
    }

    @GetMapping(value = "/nome/{nomeProduto}")
    public ResponseEntity<Page<DadosDetalhamentoProduto>> getProdutoProNome(@PathVariable String nomeProduto, Pageable paginacao){
        String nomeFormatado = nomeProduto.toUpperCase();

        var produtos = repository.findByNomeProdutoContainingIgnoreCase(nomeFormatado, paginacao);
        System.out.println(nomeFormatado);

        return ResponseEntity.ok(produtos);
    }
}
