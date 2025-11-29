-- Seed work_records with sample data
-- Inserts two records for employee id 1 and 2
-- INSERT INTO work_records (id, employee_id, check_in_time, check_out_time, duration)
-- VALUES
--   (1, 1, '2025-11-29 09:00:00', '2025-11-29 18:00:00', '09:00:00'),
--   (2, 2, '2025-11-29 08:30:00', '2025-11-29 17:30:00', '09:00:00');

-- -- Make sure serial sequence for id is in sync (to avoid duplicate key errors for subsequent inserts)
-- -- (works for PostgreSQL)
-- SELECT setval(pg_get_serial_sequence('public.work_records','id'), COALESCE((SELECT MAX(id) FROM public.work_records), 1));
