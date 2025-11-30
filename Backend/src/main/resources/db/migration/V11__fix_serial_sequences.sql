-- Fix serial sequences for tables with manually seeded IDs. This ensures nextval won't collide with existing IDs
DO $$
DECLARE
    seq_name text;
    max_id bigint;
BEGIN
    -- Work records
    SELECT pg_get_serial_sequence('work_records', 'id') INTO seq_name;
    IF seq_name IS NOT NULL THEN
        EXECUTE format('SELECT COALESCE(MAX(id), 0) FROM work_records') INTO max_id;
        IF max_id IS NULL THEN
            max_id := 0;
        END IF;
        -- setval requires value >= 1, so use GREATEST(max_id, 1)
        EXECUTE format('SELECT setval(%L, GREATEST(%s, 1))', seq_name, max_id);
    END IF;

    -- Employee table
    SELECT pg_get_serial_sequence('employee', 'id') INTO seq_name;
    IF seq_name IS NOT NULL THEN
        EXECUTE format('SELECT COALESCE(MAX(id), 0) FROM employee') INTO max_id;
        IF max_id IS NULL THEN
            max_id := 0;
        END IF;
        -- setval requires value >= 1, so use GREATEST(max_id, 1)
        EXECUTE format('SELECT setval(%L, GREATEST(%s, 1))', seq_name, max_id);
    END IF;

    -- Auth credentials (if has id serial)
    SELECT pg_get_serial_sequence('auth_credentials', 'id') INTO seq_name;
    IF seq_name IS NOT NULL THEN
        EXECUTE format('SELECT COALESCE(MAX(id), 0) FROM auth_credentials') INTO max_id;
        IF max_id IS NULL THEN
            max_id := 0;
        END IF;
        -- setval requires value >= 1, so use GREATEST(max_id, 1)
        EXECUTE format('SELECT setval(%L, GREATEST(%s, 1))', seq_name, max_id);
    END IF;
END $$;
