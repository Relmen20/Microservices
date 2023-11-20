IF NOT EXISTS (SELECT 1 FROM address) THEN
    INSERT INTO address (address, port) VALUES
    ('localhost', 8091),
    ('localhost', 8092),
    ('localhost', 8093);
END IF;
IF NOT EXISTS (SELECT 1 FROM operator) THEN
    INSERT INTO operator (operatorName) VALUES
    ('MTS'),
    ('MEGAFON'),
    ('BEELINE');
END IF;
IF NOT EXISTS (SELECT 1 FROM provider) THEN
    INSERT INTO provider (addressId, providerName, email) VALUES
    (1, 'Mts-Telecom', 'mts@telecom.ru'),
    (2, 'Megafon-Telecom', 'megafon@telecom.ru'),
    (3, 'Beeline-Telecom', 'beeline@telecom.ru');
END IF;
IF NOT EXISTS (SELECT 1 FROM session) THEN
    INSERT INTO session (operatorId, priorityType, providerId, sessionName) VALUES
    (1, 'VIP', 1, 'FirstSession'),
    (2, 'VIP', 2, 'SecondSession'),
    (3, 'DEFAULT', 3, 'ThirdSession');
END IF;