DROP DATABASE IF EXISTS trabalhoBd;
CREATE DATABASE trabalhoBd;
SET SQL_SAFE_UPDATES = 0;
SET GLOBAL log_bin_trust_function_creators = 1;

USE trabalhoBd;

CREATE TABLE Cliente (
	id INT NOT NULL AUTO_INCREMENT,
    nome varchar(45),
    telefone varchar(20),
    email varchar(45),
    cpf varchar(14),
    primary key (id)
);

CREATE TABLE Animal (
	id INT NOT NULL AUTO_INCREMENT,
    nome varchar(45),
    especie varchar(45),
    raca varchar(45),
	idCliente INT,
    primary key (id),
    foreign key (idCliente) REFERENCES Cliente(id) ON DELETE CASCADE
);

CREATE TABLE Veterinario (
	id INT NOT NULL AUTO_INCREMENT,
    nome varchar(45),
    cpf varchar(45),
    especialidade varchar(45),
    crmv varchar(45),
    primary key (id)
);

CREATE TABLE Consulta (
	id INT NOT NULL AUTO_INCREMENT,
    dia datetime,
    motivo varchar(45),
    comentarios varchar(300),
	idCliente INT,
    idVeterinario INT,
	primary key (id),
	foreign key (idCliente) REFERENCES Cliente(id) ON DELETE CASCADE,
    foreign key (idVeterinario) REFERENCES Veterinario(id) ON DELETE CASCADE
);

CREATE TABLE Servico (
	id INT NOT NULL AUTO_INCREMENT,
    preco decimal,
    nomeServico varchar(45),
	primary key (id)
);

CREATE TABLE Consulta_has_Servico(
	idConsulta INT,
    idServico INT ,
    PRIMARY KEY (idConsulta, idServico),
    FOREIGN KEY (idConsulta) REFERENCES Consulta(id) ON DELETE CASCADE,
    FOREIGN KEY (idServico) REFERENCES Servico(id) ON DELETE CASCADE
);

SELECT * FROM Veterinario;

