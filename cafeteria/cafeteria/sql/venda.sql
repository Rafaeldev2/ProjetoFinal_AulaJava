-- Vamos precisar criar a parte do banco para 
-- Fazer as funcionalidades do service



-- CREATE TABLE venda (
--     id INT AUTO_INCREMENT PRIMARY KEY,
--     data_hora TIMESTAMP NOT NULL,
--     cliente_id INT NOT NULL,
--     desconto DECIMAL(10, 2),
--     total DECIMAL(10, 2),
--     FOREIGN KEY (cliente_id) REFERENCES cliente(id)
-- );


-- CREATE TABLE item_venda (
--     id INT AUTO_INCREMENT PRIMARY KEY,
--     venda_id INT NOT NULL,
--     produto_id INT NOT NULL,
--     quantidade INT NOT NULL,
--     preco DECIMAL(10, 2) NOT NULL,
--     FOREIGN KEY (venda_id) REFERENCES venda(id),
--     FOREIGN KEY (produto_id) REFERENCES produto(id)
-- );
