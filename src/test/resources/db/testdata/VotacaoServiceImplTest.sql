insert into pauta (id, codigo, descricao)
values
('476289f8-9fb3-45d2-91a1-7ef6a5904815', '1', 'Instalação de painel Solar');


insert into sessao_votacao (id, pauta_id, inicio, fim)
values 
('e88ef005-11ef-47a2-b7b4-c4766b8e4726','476289f8-9fb3-45d2-91a1-7ef6a5904815', TIMESTAMP '2026-01-10 10:00:00', TIMESTAMP '2026-01-10 10:10:00'),
('22ff68b5-5896-42c0-997a-8408b02aa5ff','476289f8-9fb3-45d2-91a1-7ef6a5904815', CURRENT_TIMESTAMP(), TIMESTAMPADD(MINUTE, 10, CURRENT_TIMESTAMP()));