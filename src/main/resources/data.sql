-- Categoria
INSERT INTO categoria(nome) values('Informatica');
INSERT INTO categoria(nome) values('Escritorio');

-- Produto
INSERT INTO produto(nome, preco) values('Computador', 2000.00);
INSERT INTO produto(nome, preco) values('Impressora', 800.00);
INSERT INTO produto(nome, preco) values('Mouse', 79.99);

-- Produto_Categoria
insert into produto_categoria(produto_id, categoria_id) values(1 , 1);
insert into produto_categoria(produto_id, categoria_id) values(2 , 1);
insert into produto_categoria(produto_id, categoria_id) values(3 , 1);
insert into produto_categoria(produto_id, categoria_id) values(1 , 2);
insert into produto_categoria(produto_id, categoria_id) values(2 , 2);
insert into produto_categoria(produto_id, categoria_id) values(3 , 2);

-- Estado
insert into estado(nome) values('São Paulo');
insert into estado(nome) values('Minas Gerais');
insert into estado(nome) values('Rio de Janeiro');
insert into estado(nome) values('Espirito Santo');

-- Cidade
insert into cidade(nome, estado_id) values('São Paulo', 1);
insert into cidade(nome, estado_id) values('Uberlandia', 2);
insert into cidade(nome, estado_id) values('Rio de Janeiro', 3);
insert into cidade(nome, estado_id) values('Campinas', 1);

-- Consultas
--SELECT * FROM ESTADO ;
--SELECT * FROM CIDADE ;