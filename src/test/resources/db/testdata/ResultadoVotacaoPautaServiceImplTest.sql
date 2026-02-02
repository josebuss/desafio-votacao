insert into pauta (id, codigo, descricao)
values
('476289f8-9fb3-45d2-91a1-7ef6a5904815', '1', 'Instalação de painel Solar'),
('72e17ba5-aab6-4d9b-8d90-41e3d9cf2af6', '2', 'Instalação de Bicicletário'),
('100e578b-d0ea-4bfb-95f1-5a05a023273c', '3', 'Chamadada de capital');


insert into sessao_votacao (id, pauta_id, inicio, fim)
values 
('e88ef005-11ef-47a2-b7b4-c4766b8e4726','476289f8-9fb3-45d2-91a1-7ef6a5904815', TIMESTAMP '2026-01-10 10:00:00', TIMESTAMP '2026-01-10 10:10:00');

INSERT INTO votacao (id, sessao_votacao_id, cpf, aprova) 
VALUES 
('959c9a3e-c289-4dc6-83cb-b836f172aa87', 'e88ef005-11ef-47a2-b7b4-c4766b8e4726', '80414457000', TRUE),
('0119b7fb-76a9-4c4c-b097-f37157e0a01b', 'e88ef005-11ef-47a2-b7b4-c4766b8e4726', '90582253055', FALSE),
('d181eec9-7574-4142-a980-904e60fe91ae', 'e88ef005-11ef-47a2-b7b4-c4766b8e4726', '42735248054', TRUE);


insert into sessao_votacao (id, pauta_id, inicio, fim)
values 
('9b9da753-4066-4407-8d32-7a4801018701','72e17ba5-aab6-4d9b-8d90-41e3d9cf2af6', TIMESTAMP '2026-01-10 10:00:00', TIMESTAMP '2026-01-10 10:10:00');

INSERT INTO resultado_votacao_pauta (id, pauta_id, sessao_votacao_id, total_sim, total_nao, total_votos, resultado) 
VALUES ('9f6a0c42-9a5c-4c6c-8d51-3c2c8b9c1111', '72e17ba5-aab6-4d9b-8d90-41e3d9cf2af6', '9b9da753-4066-4407-8d32-7a4801018701', 5, 5, 10, 'INCONCLUSIVO');


insert into sessao_votacao (id, pauta_id, inicio, fim)
values 
('6f1c6bc9-c83f-48ab-8dd8-313ee9c036ca','100e578b-d0ea-4bfb-95f1-5a05a023273c', CURRENT_TIMESTAMP(), TIMESTAMPADD(MINUTE, 10, CURRENT_TIMESTAMP()));
