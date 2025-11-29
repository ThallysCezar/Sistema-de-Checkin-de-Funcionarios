-- Remove credentials with null employee linkage and duplicate email entries
DELETE FROM public.auth_credentials WHERE employee_id IS NULL;

-- Delete duplicates by email, keeping the earliest id
DELETE FROM public.auth_credentials a
USING public.auth_credentials b
WHERE a.email = b.email AND a.id > b.id;

-- Add unique indexes to ensure future uniqueness
CREATE UNIQUE INDEX IF NOT EXISTS idx_auth_credentials_email_unique ON public.auth_credentials (email);
CREATE UNIQUE INDEX IF NOT EXISTS idx_auth_credentials_employee_id_unique ON public.auth_credentials (employee_id);
