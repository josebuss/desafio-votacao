CREATE TABLE sessao_votacao (
    id UUID PRIMARY KEY,
    pauta_id UUID NOT NULL,
    inicio TIMESTAMP NOT NULL,
    fim TIMESTAMP NOT NULL,
    datger TIMESTAMP ,
    datalt TIMESTAMP,
    CONSTRAINT fk_sessao_votacao_pauta FOREIGN KEY (pauta_id) REFERENCES pauta (id)
);