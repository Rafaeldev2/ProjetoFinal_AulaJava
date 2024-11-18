INSERT INTO cliente (nome, telefone) VALUES ('Arnaldo Augusto Nora Antunes Filho', '489999988888');
INSERT INTO cliente (nome, telefone) VALUES ('Roberto Frejat', '11999994545');
INSERT INTO cliente (nome, telefone) VALUES ('Priscilla Novaes Leone', '71999991212');
INSERT INTO cliente (nome, telefone) VALUES ('Rita Lee Jones de Carvalho', '11999996363');

INSERT INTO produto (nome, medida, preco) VALUES ('Cafe Expresso', 1, 6.50);
INSERT INTO produto (nome, medida, preco) VALUES ('Cheesecake de Frutas Vermelhas', 1, 13);
INSERT INTO produto (nome, medida, preco, estoque) VALUES ('Agua com Gas - 350ml', 2, 4, 60);
INSERT INTO produto (nome, medida, preco, estoque) VALUES ('Pacoca', 1, 4, 80);

INSERT INTO venda (data_hora, cliente_id, desconto) VALUES ('2024-10-10 17:35', 1, 0);
INSERT INTO venda (data_hora, cliente_id, desconto) VALUES ('2024-10-15 09:35', 3, 2.25);

INSERT INTO item_venda (nome, medida, quantidade, preco, venda_id) VALUES ('Cafe Expresso', 1, 1, 6.50, 1);
INSERT INTO item_venda (nome, medida, quantidade, preco, venda_id) VALUES ('Cheesecake de Frutas Vermelhas', 1, 1, 13, 1);
INSERT INTO item_venda (nome, medida, quantidade, preco, venda_id) VALUES ('Agua com Gas - 350ml', 2, 2, 4, 2);
INSERT INTO item_venda (nome, medida, quantidade, preco, venda_id) VALUES ('Pacoca', 1, 1, 4, 2);
INSERT INTO item_venda (nome, medida, quantidade, preco, venda_id) VALUES ('Cheesecake de Frutas Vermelhas', 1, 1, 13, 2);
