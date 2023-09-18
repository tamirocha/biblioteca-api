CREATE TABLE livro_usuario(
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    codigo_livro BIGINT(20),
    codigo_usuario BIGINT(20),
    FOREIGN KEY (codigo_livro) REFERENCES livro(codigo),
    FOREIGN KEY (codigo_usuario) REFERENCES usuario(codigo)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;