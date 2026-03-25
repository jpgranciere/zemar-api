package com.zemar_api.domain.produto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Produto")
@Table(name = "produtos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeProduto;
    private String codigoProduto;

    @Enumerated(value = EnumType.STRING)
    private Categoria categoria;

    private String descricao;
    private String imagemUrl;

    public Produto(DadosCadastroProduto dados, String imagemUrl) {
        this.nomeProduto = dados.nomeProduto();
        this.codigoProduto = dados.codigoProduto();
        this.categoria = dados.categoria();
        this.descricao = dados.descricao();
        this.imagemUrl = imagemUrl;
    }

    public void atualizarProduto(DadosAtualizarProduto dados, String imagemUrl) {
        if(dados != null) {
            if(dados.nomeProduto() != null){
                this.nomeProduto = dados.nomeProduto();
            }
            if(dados.codigoProduto() != null){
                this.codigoProduto = dados.codigoProduto();
            }
            if(dados.categoria() != null){
                this.categoria = dados.categoria();
            }
            if(dados.descricao() != null){
                this.descricao = dados.descricao();
            }
        }
        this.imagemUrl = imagemUrl;
    }
}