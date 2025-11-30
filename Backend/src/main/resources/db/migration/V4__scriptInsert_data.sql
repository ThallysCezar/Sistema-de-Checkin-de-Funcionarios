---- Insert employees
INSERT INTO public.employee (id, name, email) VALUES
(1, 'Manager', 'manager@example.com'),
(2, 'Employee', 'employee@example.com');


---- Insert work records (one closed record and one open)
INSERT INTO public.work_records (id, employee_id, check_in_time, check_out_time, duration) VALUES
	(1, 1, '2025-11-28 08:00:00', '2025-11-28 17:00:00', '09:00:00'), -- 9 hours
	(2, 2, '2025-11-28 09:00:00', NULL, NULL);