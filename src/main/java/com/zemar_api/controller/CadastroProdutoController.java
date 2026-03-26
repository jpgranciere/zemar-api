package com.zemar_api.controller;

import com.zemar_api.domain.produto.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/admin")
public class CadastroProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private SupabaseStorageService storageService;

    @PostMapping(value = "/cadastrar-produto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Transactional
    public ResponseEntity cadastrar(@Valid @RequestPart("dados") DadosCadastroProduto dados, @RequestPart("imagem")MultipartFile imagem, UriComponentsBuilder uriBuilder) throws Exception{
        String imagemUrl = storageService.uploadImagem(imagem);
        Produto produto = new Produto(dados, imagemUrl);
        repository.save(produto);

        var uri = uriBuilder.path("/cadastrar-produto/{id}").buildAndExpand(produto.getId()).toUri();


        return ResponseEntity.created(uri).body(new DadosDetalhamentoProduto(produto));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemProduto>> listar(@PageableDefault(size = 10, sort = "nomeProduto") Pageable paginacao){
        var page =  repository.findAll(paginacao).map(DadosListagemProduto::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping(value = "/editar-produto/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Transactional
    public ResponseEntity atualizarProduto(@PathVariable Long id ,
                                           @RequestPart(value = "dados", required = false)DadosAtualizarProduto dados,
                                           @RequestPart(value = "imagem", required = false)MultipartFile imagem) throws Exception{
        var produto = repository.getReferenceById(id);

        if(imagem != null && !imagem.isEmpty()){
            storageService.deletarArquivo(produto.getImagemUrl());
            String imagemUrlNova = storageService.uploadImagem(imagem);
            produto.atualizarProduto(dados, imagemUrlNova);
            return ResponseEntity.ok(new DadosDetalhamentoProduto(produto));
        }

        if(dados != null){
            produto.atualizarProduto(dados, produto.getImagemUrl());
            return ResponseEntity.ok(new DadosDetalhamentoProduto(produto));
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "/deletar-produto/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id){
        var produto = repository.getReferenceById(id);
        storageService.deletarProduto(produto);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "{id}")
    public ResponseEntity listarProdutoDetalhado(@PathVariable Long id){
        var produto = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoProduto(produto));
    }

    @GetMapping(value = "/categoria")
    public ResponseEntity<Categoria[]> listarCategoria() {
        Categoria[] categorias = Categoria.values();

        return ResponseEntity.ok(categorias);
    }


}

