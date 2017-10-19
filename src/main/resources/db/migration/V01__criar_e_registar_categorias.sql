CREATE SEQUENCE categoria_id_seq;

CREATE TABLE public.categoria
(
  id bigint DEFAULT nextval('categoria_id_seq') NOT NULL,
  nome VARCHAR(255) NOT NULL,
  CONSTRAINT categoria_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
  
INSERT INTO public.categoria (nome) VALUES('Lazer');
INSERT INTO public.categoria (nome) VALUES('Alimentação');
INSERT INTO public.categoria (nome) VALUES('Supermercado');
INSERT INTO public.categoria (nome) VALUES('Farmácia');
