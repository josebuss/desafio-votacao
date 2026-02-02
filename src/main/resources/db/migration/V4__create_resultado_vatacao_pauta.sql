CREATE TABLE resultado_votacao_pauta (
    id UUID PRIMARY KEY,
    pauta_id UUID NOT NULL,
    sessao_votacao_id UUID NOT NULL,
    total_sim INT NOT NULL,
    total_nao INT NOT NULL,
    total_votos INT NOT NULL,
    resultado VARCHAR(20) NOT NULL,
    datger TIMESTAMP,
    datalt TIMESTAMP,
    CONSTRAINT fk_resultado_votacao_sessao FOREIGN KEY (sessao_votacao_id) REFERENCES sessao_votacao(id),
    CONSTRAINT fk_resultado_votacao_pauta FOREIGN KEY (pauta_id) REFERENCES pauta(id),    
    CONSTRAINT uk_resultado_votacao_pauta_id UNIQUE (pauta_id)
);
