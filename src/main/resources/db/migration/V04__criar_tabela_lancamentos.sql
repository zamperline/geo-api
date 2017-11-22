DROP SEQUENCE IF EXISTS lancamento_id_seq;
CREATE SEQUENCE lancamento_id_seq;
CREATE TABLE lancamento (
	id bigint DEFAULT nextval('lancamento_id_seq') NOT NULL,
	descricao VARCHAR(50) NOT NULL,
	data_vencimento DATE NOT NULL,
	data_pagamento DATE,
	valor DECIMAL(10,2) NOT NULL,
	observacao VARCHAR(100),
	tipo VARCHAR(20) NOT NULL,
	id_categoria BIGINT NOT NULL,
	id_pessoa BIGINT NOT NULL,
	CONSTRAINT lancamento_pkey PRIMARY KEY (id),
	CONSTRAINT categoria_fkey FOREIGN KEY ("id_categoria") REFERENCES "public"."categoria" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION,
	CONSTRAINT pessoa_fkey FOREIGN KEY ("id_pessoa") REFERENCES "public"."pessoa" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION
)
WITH (
  OIDS=FALSE
);

INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, id_categoria, id_pessoa) values ('Lanche', '2017-06-10', null, 10.20, null, 'DESPESA', 1, 1);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, id_categoria, id_pessoa) values ('Eletrônicos', '2017-04-10', '2017-04-10', 2100.32, null, 'DESPESA', 2, 4);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, id_categoria, id_pessoa) values ('Eletrônicos', '2017-04-10', '2017-04-10', 2100.32, null, 'DESPESA', 3, 4);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, id_categoria, id_pessoa) values ('Eletrônicos', '2017-04-10', '2017-04-10', 2100.32, null, 'RECEITA', 4, 2);
