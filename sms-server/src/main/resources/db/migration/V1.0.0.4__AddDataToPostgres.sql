DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM TestDB.Sessions) THEN
        INSERT INTO TestDB.Sessions (operator_id, priority_type, provider_id, session_name) VALUES
        (1, 'VIP', 1, 'FirstSession'),
        (2, 'VIP', 2, 'SecondSession'),
        (3, 'DEFAULT', 3, 'ThirdSession');
    END IF;
END
$$;