CREATE TABLE usuario(
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    cpf VARCHAR(50) NOT NULL,
    nome VARCHAR(50) NOT NULL,
    matricula VARCHAR(50) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;