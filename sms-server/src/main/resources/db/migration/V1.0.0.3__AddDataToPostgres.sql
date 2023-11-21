DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM TestDB.Provider) THEN
        INSERT INTO TestDB.Provider (address_id, provider_name, email) VALUES
        (1, 'Mts-Telecom', 'mts@telecom.ru'),
        (2, 'Megafon-Telecom', 'megafon@telecom.ru'),
        (3, 'Beeline-Telecom', 'beeline@telecom.ru');
    END IF;
END
$$;