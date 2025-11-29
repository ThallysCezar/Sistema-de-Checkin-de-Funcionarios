CREATE TABLE IF NOT EXISTS public.auth_credentials (
		id BIGSERIAL PRIMARY KEY,
		employee_id BIGINT NOT NULL,
		email VARCHAR(200) NOT NULL,
		password VARCHAR(200),
		role VARCHAR(50),
		CONSTRAINT fk_employee_auth FOREIGN KEY(employee_id) REFERENCES public.employee(id) ON DELETE CASCADE
);

INSERT INTO public.auth_credentials (employee_id, email, password, role) VALUES
	((SELECT id FROM public.employee WHERE email = 'admin@example.com'), 'admin@example.com', 'admin', 'admin'),
	((SELECT id FROM public.employee WHERE email = 'manager@example.com'), 'manager@example.com', 'manager', 'manager'),
	((SELECT id FROM public.employee WHERE email = 'employee@example.com'), 'employee@example.com', 'password', 'employee')
ON CONFLICT (email) DO NOTHING;
--INSERT INTO public.auth_credentials (employee_id, email, password, role) VALUES
--  (1, 'admin@example.com', 'admin', 'admin'),
--  (2, 'manager@example.com', 'manager', 'manager'),
--  (3, 'employee@example.com', 'password', 'employee')
--ON CONFLICT (email) DO NOTHING;
