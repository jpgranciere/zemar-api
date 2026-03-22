package com.zemar_api.controller;

import com.zemar_api.produto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin")
public class CadastroProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private SupabaseStorageService storageService;

    @PostMapping(value = "/cadastrar-produto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Transactional
    public ResponseEntity cadastrar(@RequestPart("dados")DadosCadastroProduto dados, @RequestPart("imagem")MultipartFile imagem) throws Exception{
        String imagemUrl = storageService.uploadImagem(imagem);
        Produto produto = new Produto(dados, imagemUrl);
        repository.save(produto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public Page<DadosListagemProduto> listar(@PageableDefault(size = 10, sort = "nomeProduto") Pageable paginacao){
        return repository.findAll(paginacao).map(DadosListagemProduto::new);
    }

    @PutMapping(value = "/editar-produto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Transactional
    public void atualizarProduto(@RequestPart("dados")DadosAtualizarProduto dados, @RequestPart(value = "imagem", required = false)MultipartFile imagem) throws Exception{
        var produto = repository.getReferenceById(dados.id());

        if(imagem != null && !imagem.isEmpty()){
            storageService.deletarArquivo(produto.getImagemUrl());
            String imagemUrlNova = storageService.uploadImagem(imagem);
            produto.atualizarProduto(dados, imagemUrlNova);
            return;
        }

        produto.atualizarProduto(dados, produto.getImagemUrl());
    }
}

