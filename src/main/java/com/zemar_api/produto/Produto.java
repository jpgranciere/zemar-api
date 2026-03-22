package com.zemar_api.produto;

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
}
