-- ATENÇÃO:
-- Alterações nos dados desta migration podem acarretar quebra em testes existentes

INSERT INTO expedition_plan (planned, realized, expedition_month, genetic_material_id, client_id) VALUES
    (100, 0, 0, 1, 1),
    (150, 0, 1, 3, 2),
    (200, 0, 2, 5, 1),
    (250, 0, 3, 7, 2),
    (250, 0, 3, 7, 2); -- para deletar
