package com.zemar_api.controller;

import com.zemar_api.produto.DadosCadastroProduto;
import com.zemar_api.produto.Produto;
import com.zemar_api.produto.ProdutoRepository;
import com.zemar_api.produto.SupabaseStorageService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
