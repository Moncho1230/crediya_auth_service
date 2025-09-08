
BEGIN;
CREATE EXTENSION IF NOT EXISTS "pgcrypto";


CREATE TABLE IF NOT EXISTS public.users
(
    id uuid NOT NULL DEFAULT gen_random_uuid(),
    last_name character varying COLLATE pg_catalog."default" NOT NULL,
    address character varying COLLATE pg_catalog."default",
    phone_number character varying COLLATE pg_catalog."default",
    email character varying COLLATE pg_catalog."default" NOT NULL,
    name character varying COLLATE pg_catalog."default" NOT NULL,
    balance integer NOT NULL,
    birthdate date,
    role_id uuid NOT NULL,
    document character varying COLLATE pg_catalog."default",
    password character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT document UNIQUE (document)
        INCLUDE(document)
);

CREATE TABLE IF NOT EXISTS public.rol
(
    role_id uuid NOT NULL DEFAULT gen_random_uuid(),
    name character varying COLLATE pg_catalog."default" NOT NULL,
    description character varying COLLATE pg_catalog."default",
    CONSTRAINT rol_pkey PRIMARY KEY (role_id),
    CONSTRAINT name UNIQUE (name)
        INCLUDE(name)
);

ALTER TABLE IF EXISTS public.users
    ADD CONSTRAINT role_id FOREIGN KEY (role_id)
    REFERENCES public.rol (role_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

END;