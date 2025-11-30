-- Ensure employee table exists (in case V3 was skipped)
CREATE TABLE IF NOT EXISTS public.employee (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    email VARCHAR(200)
);

-- Ensure work_records table exists (in case V3 was skipped)
CREATE TABLE IF NOT EXISTS public.work_records (
    id BIGSERIAL PRIMARY KEY,
    employee_id BIGINT NOT NULL,
    check_in_time TIMESTAMP,
    check_out_time TIMESTAMP,
    duration VARCHAR(20),
    CONSTRAINT fk_employee FOREIGN KEY(employee_id) REFERENCES public.employee(id) ON DELETE CASCADE
);

-- Ensure auth_credentials table exists (in case V3 was skipped)
CREATE TABLE IF NOT EXISTS public.auth_credentials (
    id BIGSERIAL PRIMARY KEY,
    employee_id BIGINT NOT NULL,
    email VARCHAR(200) NOT NULL UNIQUE,
    password VARCHAR(200),
    role VARCHAR(50),
    CONSTRAINT fk_employee_auth FOREIGN KEY(employee_id) REFERENCES public.employee(id) ON DELETE CASCADE
);

-- Insert employees if they don't exist
INSERT INTO public.employee (id, name, email)
SELECT 1, 'Manager', 'manager@example.com'
WHERE NOT EXISTS (SELECT 1 FROM public.employee WHERE email = 'manager@example.com');

INSERT INTO public.employee (id, name, email)
SELECT 2, 'Employee', 'employee@example.com'
WHERE NOT EXISTS (SELECT 1 FROM public.employee WHERE email = 'employee@example.com');

-- Insert auth credentials
INSERT INTO public.auth_credentials (id, employee_id, email, password, role)
SELECT 
    1,
    (SELECT id FROM public.employee WHERE email = 'manager@example.com'),
    'manager@example.com',
    'manager',
    'manager'
WHERE NOT EXISTS (SELECT 1 FROM public.auth_credentials WHERE email = 'manager@example.com');

INSERT INTO public.auth_credentials (id, employee_id, email, password, role)
SELECT 
    2,
    (SELECT id FROM public.employee WHERE email = 'employee@example.com'),
    'employee@example.com',
    'password',
    'employee'
WHERE NOT EXISTS (SELECT 1 FROM public.auth_credentials WHERE email = 'employee@example.com');
--INSERT INTO public.auth_credentials (employee_id, email, password, role) VALUES
--  (1, 'admin@example.com', 'admin', 'admin'),
--  (2, 'manager@example.com', 'manager', 'manager'),
--  (3, 'employee@example.com', 'password', 'employee')
--ON CONFLICT (email) DO NOTHING;
