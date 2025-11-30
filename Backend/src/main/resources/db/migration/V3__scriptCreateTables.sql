-- Create employees table
CREATE TABLE IF NOT EXISTS public.employee (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    email VARCHAR(200)
);

CREATE TABLE IF NOT EXISTS public.auth_credentials (
    id BIGSERIAL PRIMARY KEY,
    employee_id BIGINT NOT NULL,
    email VARCHAR(200) NOT NULL UNIQUE,
    password VARCHAR(200),
    role VARCHAR(50),
    CONSTRAINT fk_employee_auth FOREIGN KEY(employee_id) REFERENCES public.employee(id) ON DELETE CASCADE
);

-- Create work_records table
CREATE TABLE IF NOT EXISTS public.work_records (
    id BIGSERIAL PRIMARY KEY,
    employee_id BIGINT NOT NULL,
    check_in_time TIMESTAMP,
    check_out_time TIMESTAMP,
    duration VARCHAR(20),
    CONSTRAINT fk_employee FOREIGN KEY(employee_id) REFERENCES public.employee(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_work_records_employee_id ON public.work_records(employee_id);