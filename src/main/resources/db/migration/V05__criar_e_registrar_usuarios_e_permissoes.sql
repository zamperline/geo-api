DROP SEQUENCE IF EXISTS usuario_id_seq;
CREATE SEQUENCE usuario_id_seq;
CREATE TABLE usuario (
	id bigint DEFAULT nextval('usuario_id_seq') NOT NULL,
	nome VARCHAR(50) NOT NULL,
	email VARCHAR(100) NOT NULL,
	senha VARCHAR(150) NOT NULL,
	CONSTRAINT usuario_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);

DROP SEQUENCE IF EXISTS permissao_id_seq;
CREATE SEQUENCE permissao_id_seq;
CREATE TABLE permissao (
	id bigint DEFAULT nextval('permissao_id_seq') NOT NULL,
	descricao VARCHAR(50) NOT NULL,
	CONSTRAINT permissao_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);


CREATE TABLE usuario_permissao (
	id_usuario BIGINT NOT NULL,
	id_permissao BIGINT NOT NULL,
	CONSTRAINT usuario_permissao_pkey PRIMARY KEY (id_usuario, id_permissao),
	CONSTRAINT usuario_fkey FOREIGN KEY ("id_usuario") REFERENCES "public"."usuario" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION,
	CONSTRAINT permissao_fkey FOREIGN KEY ("id_permissao") REFERENCES "public"."permissao" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION
) 
WITH (
  OIDS=FALSE
);

INSERT INTO usuario (nome, email, senha) values ('Administrador', 'admin@geo.com', '$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.');
INSERT INTO usuario (nome, email, senha) values ('Maria Silva', 'maria@geo.com', '$2a$10$Zc3w6HyuPOPXamaMhh.PQOXvDnEsadztbfi6/RyZWJDzimE8WQjaq');

INSERT INTO permissao (descricao) values ('ROLE_CADASTRAR_CATEGORIA');
INSERT INTO permissao (descricao) values ('ROLE_PESQUISAR_CATEGORIA');

INSERT INTO permissao (descricao) values ('ROLE_CADASTRAR_PESSOA');
INSERT INTO permissao (descricao) values ('ROLE_REMOVER_PESSOA');
INSERT INTO permissao (descricao) values ('ROLE_PESQUISAR_PESSOA');

INSERT INTO permissao (descricao) values ('ROLE_CADASTRAR_LANCAMENTO');
INSERT INTO permissao (descricao) values ('ROLE_REMOVER_LANCAMENTO');
INSERT INTO permissao (descricao) values ('ROLE_PESQUISAR_LANCAMENTO');

INSERT INTO permissao (descricao) values ('ROLE_EDITAR_MAPA');
INSERT INTO permissao (descricao) values ('ROLE_PESQUISAR_MAPA');


-- admin
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 1);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 2);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 3);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 4);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 5);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 6);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 7);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 8);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 9);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 10);

-- maria
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (2, 2);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (2, 5);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (2, 8);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (2, 9);