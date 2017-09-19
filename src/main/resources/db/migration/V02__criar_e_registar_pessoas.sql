CREATE TABLE pessoa (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	logradouro VARCHAR(50),
	numero VARCHAR(6),
	complemento VARCHAR(50),
	bairro VARCHAR(50),
	cep VARCHAR(20),
	cidade VARCHAR(50),
	estado VARCHAR(4),
    ativo BOOLEAN NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) 
			values ('João', 'rua joaquim', '22', null, 'vicente', '13222322', 'cidade','SP', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) 
			values ('Joaquim', 'rua marcelo', '23', null, 'sobrado', '13225322', 'cidade', 'SC', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) 
			values ('Marcelo', 'rua fernando', '24', null, 'belem', '75034393', 'cidade', 'RO', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) 
			values ('Fernando', 'rua patricia', '2', null, 'parai', '34056221', 'cidade', 'PE', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) 
			values ('Patricia', 'rua roberta', '22q', null, 'setor de mansoes', '305353674', 'cidade', 'AC', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) 
			values ('Roberta', 'rua joao', '15a', null, 'lago sul', '7305333', 'cidade', 'DF', true);
