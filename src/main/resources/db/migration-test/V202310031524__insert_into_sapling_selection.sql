-- ATENÇÃO:
-- Alterações nos dados desta migration podem acarretar quebra em testes existentes

INSERT INTO sapling_selection (selection_date, total_rooted_saplings) VALUES
    ('2023-09-25', 800),
    ('2023-09-30', 1400),
    ('2023-10-01', 1750),
    ('2023-10-03', 2000);

UPDATE batch SET sapling_selection_id = 1 WHERE id = 1;
UPDATE batch SET sapling_selection_id = 1 WHERE id = 2;

