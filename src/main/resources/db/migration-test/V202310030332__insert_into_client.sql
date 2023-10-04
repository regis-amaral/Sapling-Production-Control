-- ATENÇÃO:
-- Alterações nos dados desta migration podem acarretar quebra em testes existentes

-- CLASSES DE TESTE QUE UTILIZAM ESSES DADOS:
-- src/test/java/dev/regis/rest/controllers/person/ClienteControllerTest.java
-- src/test/java/dev/regis/rest/services/ClientServiceTest.java

INSERT INTO client (name) VALUES
    ('User A'),
    ('User B'),
    ('User C to update'),
    ('User D to delete');
