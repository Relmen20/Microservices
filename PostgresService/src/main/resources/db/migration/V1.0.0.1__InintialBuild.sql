create SCHEMA IF NOT EXISTS TestDB;

create TABLE IF NOT EXISTS TestDB.Operator (
    id SERIAL PRIMARY KEY,
    operator_name VARCHAR(255) NOT NULL
);

create TABLE IF NOT EXISTS TestDB.Address (
    id SERIAL PRIMARY KEY,
    port INT NOT NULL,
    address VARCHAR(255) NOT NULL
);

create TABLE IF NOT EXISTS TestDB.Provider (
    id SERIAL PRIMARY KEY,
    address_id INT NOT NULL,
    provider_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    FOREIGN KEY (address_id)
        REFERENCES  TestDB.Address (id)
);

create TABLE IF NOT EXISTS TestDB.Sessions (
    id SERIAL PRIMARY KEY,
    session_name VARCHAR(255) NOT NULL,
    operator_id INT NOT NULL,
    provider_id INT NOT NULL,
    priority_type VARCHAR(255) NOT NULL,
    FOREIGN KEY (provider_id)
        REFERENCES  TestDB.Provider (id),
    FOREIGN KEY (operator_id)
        REFERENCES  TestDB.Operator (id)
);

