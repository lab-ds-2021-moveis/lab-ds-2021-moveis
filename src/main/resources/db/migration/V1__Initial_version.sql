-- Initial database version

DROP TABLE IF EXISTS funcionario;
DROP TABLE IF EXISTS cliente;
DROP TABLE IF EXISTS produto;
DROP TABLE IF EXISTS montagem;
DROP TABLE IF EXISTS compra;
DROP TABLE IF EXISTS compra_produto;
DROP TABLE IF EXISTS entrada_estoque;
DROP TABLE IF EXISTS solicitacao_estoque;

CREATE SEQUENCE IF NOT EXISTS funcionario_id_seq INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS cliente_id_seq INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS produto_id_seq INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS montagem_id_seq INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS compra_id_seq INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS compra_produto_id_seq INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS entrada_estoque_id_seq INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS solicitacao_estoque_id_seq INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS funcionario (
    id_funcionario BIGSERIAL NOT NULL PRIMARY KEY,
    nome VARCHAR NOT NULL,
    senha VARCHAR NOT NULL,
    cargo VARCHAR NOT NULL,
    credencial VARCHAR UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS cliente (
    id_cliente BIGSERIAL NOT NULL PRIMARY KEY,
    nome VARCHAR NOT NULL,
    senha VARCHAR NOT NULL,
    cpf VARCHAR UNIQUE NOT NULL,
    email VARCHAR UNIQUE NOT NULL,
    telefone VARCHAR UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS produto (
    id_produto BIGSERIAL NOT NULL PRIMARY KEY,
    nome VARCHAR NOT NULL,
    valor DOUBLE PRECISION NOT NULL,
    descricao VARCHAR NULL,
    estoque INTEGER DEFAULT 0,
    fabricante VARCHAR NULL,
    modelo VARCHAR NULL,
    tipo_ambiente VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS montagem (
    id_montagem BIGSERIAL NOT NULL PRIMARY KEY,
    data TIMESTAMP NOT NULL,
    local VARCHAR NOT NULL,
    status VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS compra (
    id_compra BIGSERIAL NOT NULL PRIMARY KEY,
    cpf_cliente VARCHAR NULL
        REFERENCES cliente (cpf)
        ON DELETE CASCADE,
    valor_total DOUBLE PRECISION DEFAULT 0.0,
    id_funcionario BIGINT NULL
        REFERENCES funcionario (id_funcionario)
        ON DELETE CASCADE,
    id_montagem BIGINT NULL
        REFERENCES montagem (id_montagem)
        ON DELETE CASCADE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS compra_produto (
    id_compra BIGINT NULL
        REFERENCES compra (id_compra)
        ON DELETE CASCADE,
    id_produto BIGINT NULL
        REFERENCES produto (id_produto)
        ON DELETE CASCADE,
    quantidade INTEGER DEFAULT 0,
    PRIMARY KEY (id_compra, id_produto)
);

CREATE TABLE IF NOT EXISTS entrada_estoque (
    id_estoque BIGSERIAL NOT NULL PRIMARY KEY,
    id_produto BIGINT NULL
        REFERENCES produto (id_produto)
        ON DELETE CASCADE,
    quantidade INTEGER DEFAULT 0,
    data_recebimento TIMESTAMP NULL
);

CREATE TABLE IF NOT EXISTS solicitacao_estoque (
    id_solicitacao BIGSERIAL NOT NULL PRIMARY KEY,
    id_produto BIGINT NULL
        REFERENCES produto (id_produto)
        ON DELETE CASCADE,
    data_solicitacao TIMESTAMP NULL,
    qtd_solicitada INTEGER DEFAULT 0,
    status VARCHAR NOT NULL
);
