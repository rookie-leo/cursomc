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

-- Consultas
--SELECT * FROM ESTADO ;
--SELECT * FROM CIDADE ;