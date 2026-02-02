CREATE TABLE pauta (
    id UUID PRIMARY KEY,
    codigo VARCHAR(50) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    datger TIMESTAMP,
    datalt TIMESTAMP,
    CONSTRAINT uk_pauta_codigo UNIQUE (codigo)
);