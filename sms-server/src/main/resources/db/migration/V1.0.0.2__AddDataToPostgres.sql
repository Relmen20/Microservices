DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM TestDB.Address) THEN
        INSERT INTO TestDB.Address (port, address) VALUES
        (8091, 'localhost'),
        (8092, 'localhost'),
        (8093, 'localhost');
    END IF;
END
$$;

DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM TestDB.Operator) THEN
        INSERT INTO TestDB.Operator (operator_name) VALUES
        ('MTS'),
        ('MEGAFON'),
        ('BEELINE');
    END IF;
END
$$;