insert into pauta (id, codigo, descricao)
values
('476289f8-9fb3-45d2-91a1-7ef6a5904815', '1', 'Instalação de painel Solar');

insert into sessao_votacao (id, pauta_id, inicio, fim)
values ('e88ef005-11ef-47a2-b7b4-c4766b8e4726','476289f8-9fb3-45d2-91a1-7ef6a5904815', TIMESTAMP '2026-01-10 10:00:00', TIMESTAMP '2026-01-10 10:10:00');

insert into pauta (id, codigo, descricao)
values
('6f1c6bc9-c83f-48ab-8dd8-313ee9c036ca', '2', 'Instalação de painel Solar');

insert into sessao_votacao (id, pauta_id, inicio, fim)
values ('c7cd8d7a-2632-4a93-b030-3754c72162de','6f1c6bc9-c83f-48ab-8dd8-313ee9c036ca', CURRENT_TIMESTAMP(), TIMESTAMPADD(MINUTE, 10, CURRENT_TIMESTAMP()));
