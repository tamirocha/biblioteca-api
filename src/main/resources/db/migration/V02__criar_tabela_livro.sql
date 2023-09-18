CREATE TABLE livro(
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(50) NOT NULL,
    autor VARCHAR(50) NOT NULL,
    quantidade_total BIGINT(100),
    quantidade_disponivel BIGINT(100),
    numero_paginas BIGINT(100),
    disponibilidade BOOLEAN NOT NULL,
    data_entrega DATE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;