-- ATENÇÃO:
-- Alterações nos dados desta migration podem acarretar quebra em testes existentes

INSERT INTO batch (code, genetic_material_id, staking_date, amount) VALUES
    ('321/2023', 1, '2023-09-19', 1000), -- Com SaplingSelection
    ('322/2023', 3, '2023-09-20', 1500), -- Com SaplingSelection
    ('323/2023', 2, '2023-09-21', 2000),
    ('324/2023', 4, '2023-09-22', 2500),
    ('325/2023', 5, '2023-09-22', 2500); -- To Delete

