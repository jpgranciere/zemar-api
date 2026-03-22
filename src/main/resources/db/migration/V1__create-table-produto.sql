CREATE TABLE produtos(
    id BIGINT GENERATED ALWAYS AS IDENTITY,
    nome_produto VARCHAR(100) NOT NULL,
    codigo_produto VARCHAR(20) NOT NULL,
    categoria VARCHAR(100) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    imagem_url VARCHAR(255) NOT NULL,

    PRIMARY KEY (id)
);