package com.zemar_api.controller;

import com.zemar_api.domain.produto.Categoria;
import com.zemar_api.domain.produto.DadosDetalhamentoProduto;
import com.zemar_api.domain.produto.DadosListagemProduto;
import com.zemar_api.domain.produto.ProdutoRepository;
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

}
