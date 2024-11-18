CREATE TABLE produto
(
    id serial,
    nome character varying(200) NOT NULL,
    medida smallint NOT NULL,
    preco numeric(8, 2) NOT NULL,
    estoque integer,
    CONSTRAINT pk_produto PRIMARY KEY (id)
);

COMMENT ON TABLE produto
    IS 'Tabela de produtos da cafeteria';

CREATE TABLE cliente
(
    id serial,
    nome character varying(200) NOT NULL,
    telefone character varying(15) NOT NULL,
    CONSTRAINT pk_cliente PRIMARY KEY (id)
);

COMMENT ON TABLE cliente
    IS 'Tabela de clientes da cafeteria';    

CREATE TABLE venda
(
    id serial NOT NULL,
    data_hora timestamp with time zone NOT NULL,
    cliente_id integer NOT NULL,
    desconto numeric(8,2) NOT NULL,
    CONSTRAINT pk_venda PRIMARY KEY (id),
    CONSTRAINT fk_cliente FOREIGN KEY (cliente_id)
        REFERENCES cliente (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE RESTRICT
        NOT VALID
);

COMMENT ON TABLE venda
    IS 'Tabela das vendas da cafeteria';    

CREATE TABLE item_venda
(
    id serial,
    nome character varying(200) NOT NULL,
    medida smallint NOT NULL,
    quantidade integer NOT NULL,
    preco numeric(8, 2) NOT NULL,
    venda_id integer NOT NULL,
    CONSTRAINT pk_item_venda PRIMARY KEY (id),
    CONSTRAINT fk_venda FOREIGN KEY (venda_id)
        REFERENCES venda (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

COMMENT ON TABLE item_venda
    IS 'Tabela dos itens de uma venda';