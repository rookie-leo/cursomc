INSERT INTO categoria(nome) values('Informatica');
INSERT INTO categoria(nome) values('Escritorio');

INSERT INTO produto(nome, preco) values('Computador', 2000.00);
INSERT INTO produto(nome, preco) values('Impressora', 800.00);
INSERT INTO produto(nome, preco) values('Mouse', 79.99);

insert into produto_categoria(produto_id, categoria_id) values(1 , 1);
insert into produto_categoria(produto_id, categoria_id) values(2 , 1);
insert into produto_categoria(produto_id, categoria_id) values(3 , 1);
insert into produto_categoria(produto_id, categoria_id) values(1 , 2);
insert into produto_categoria(produto_id, categoria_id) values(2 , 2);
insert into produto_categoria(produto_id, categoria_id) values(3 , 2);