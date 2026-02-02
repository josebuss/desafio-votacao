CREATE TABLE votacao (
    id UUID PRIMARY KEY,
    sessao_votacao_id UUID NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    aprova BOOLEAN NOT NULL,
    datger TIMESTAMP,
    datalt TIMESTAMP,
    CONSTRAINT fk_sessao_votacao FOREIGN KEY (sessao_votacao_id) REFERENCES sessao_votacao(id),
	CONSTRAINT uk_sessao_cpf UNIQUE (sessao_votacao_id, cpf)
);