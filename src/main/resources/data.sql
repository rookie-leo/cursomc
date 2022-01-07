-- Categoria
INSERT INTO categoria(nome) values('Informatica');
INSERT INTO categoria(nome) values('Escritorio');
INSERT INTO categoria(nome) values('Casa, mesa e banho');
INSERT INTO categoria(nome) values('Eletronicos');
INSERT INTO categoria(nome) values('Jardinagem');
INSERT INTO categoria(nome) values('Decoração');

-- Produto
INSERT INTO produto(nome, preco) values('Computador', 2000.00);
INSERT INTO produto(nome, preco) values('Impressora', 800.00);
INSERT INTO produto(nome, preco) values('Mouse', 79.99);
INSERT INTO produto(nome, preco) values('Caixa de som', 129.30);
INSERT INTO produto(nome, preco) values('Vaso de plantas', 15.00);
INSERT INTO produto(nome, preco) values('TV true color', 2500.50);
INSERT INTO produto(nome, preco) values('Colcha', 39.99);
INSERT INTO produto(nome, preco) values('Quadro', 79.99);

-- Produto_Categoria
insert into produto_categoria(produto_id, categoria_id) values(1 , 1);
insert into produto_categoria(produto_id, categoria_id) values(2 , 1);
insert into produto_categoria(produto_id, categoria_id) values(3 , 1);
insert into produto_categoria(produto_id, categoria_id) values(1 , 2);
insert into produto_categoria(produto_id, categoria_id) values(2 , 2);
insert into produto_categoria(produto_id, categoria_id) values(3 , 2);
insert into produto_categoria(produto_id, categoria_id) values(4 , 4);
insert into produto_categoria(produto_id, categoria_id) values(5 , 5);
insert into produto_categoria(produto_id, categoria_id) values(8 , 6);
insert into produto_categoria(produto_id, categoria_id) values(6 , 4);
insert into produto_categoria(produto_id, categoria_id) values(7 , 3);

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

-- Cliente
insert into cliente(nome, documento, email, tipo) values('João', '11111111111', 'joao@email.com', 1);
insert into cliente(nome, documento, email, tipo) values('Maria', '22222222222', 'maria@email.com', 1);
insert into cliente(nome, documento, email, tipo) values('Saci Comercios', '11111111000100', 'atendimento@saci.com', 2);
insert into cliente(nome, documento, email, tipo) values('Ana', '54523216455', 'ana@email.com', 1);

-- Telefones
insert into telefones(cliente_id, telefones) values(1, ('11 911112222', '11 33339797'));
insert into telefones(cliente_id, telefones) values(2, ('11 931647985'));
insert into telefones(cliente_id, telefones) values(1, ('11 978465832', '11 39783215'));
insert into telefones(cliente_id, telefones) values(1, ('11 33339797'));

-- Endereço
insert into endereco(logradouro, numero, complemento, bairro, cep, cliente_id, cidade_id)
    values('rua das reposas', '01', 'A', 'Bosque das Raposas', '01010-010', 1, 1);
insert into endereco(logradouro, numero, complemento, bairro, cep, cliente_id, cidade_id)
    values('rua coqueiro', '200', 'Ao lado do mercado coqueiro', 'Jd. Dos Cocos', '07474-070', 2, 2);
insert into endereco(logradouro, numero, complemento, bairro, cep, cliente_id, cidade_id)
    values('av Brasil', '1565', 'Predio B', 'Irajá', '21230-280', 3, 3);
insert into endereco(logradouro, numero, complemento, bairro, cep, cliente_id, cidade_id)
    values('Avenida Francisco Glicério', '10', 'B', 'Guanabara', '13012-000', 4, 4);
    
-- Pedido
insert into pedido(instante, cliente_id, endereco_de_entrega_id) values('2007-12-03T10:15:30', 1, 1);
insert into pedido(instante, cliente_id, endereco_de_entrega_id) values('2015-01-03T23:49:30', 2, 2);
insert into pedido(instante, cliente_id, endereco_de_entrega_id) values('2021-12-04T05:19:30', 3, 3);


-- Pagamento_com_Cartao
insert into pagamento(estado, pedido_id) values(2, 1);
insert into pagamento_com_cartao(numero_parcelas, pedido_id) values((6), select id from pedido where id = 1);
insert into pagamento(estado, pedido_id) values(1, 2);
insert into pagamento_com_boleto(data_vencimento, data_pagamento, pedido_id) values('2007-12-03T10:15:30', '2007-12-03T10:15:30', 2);

-- Item_Pedido
insert into item_pedido(desconto, preco, quantidade, pedido_id, produto_id) values(35.0, (
	select preco from produto where id = 1),
2, 1, 1);
insert into item_pedido(desconto, preco, quantidade, pedido_id, produto_id) values(10.0, (
	select preco from produto where id = 2),
5, 2, 2);
insert into item_pedido(desconto, preco, quantidade, pedido_id, produto_id) values(10.0, (
	select preco from produto where id = 3),
10, 3, 3);


-- Consultas
--SELECT * FROM ESTADO ;
--SELECT * FROM CIDADE ;