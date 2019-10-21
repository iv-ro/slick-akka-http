CREATE SEQUENCE public.user_id_seq
    INCREMENT 1
    START 1;

ALTER SEQUENCE public.user_id_seq
    OWNER TO restapp;

CREATE TABLE public."USERS"
(
    "USER_ID" bigint NOT NULL DEFAULT nextval('user_id_seq'::regclass),
    "USER_FIRST_NAME" character varying(64),
    "USER_LAST_NAME" character varying(64)[],
    "USER_BIRTH_DATE" date,
    "USER_ADDRESS" character varying(256)[],
    CONSTRAINT "USERS_pkey" PRIMARY KEY ("USER_ID")
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."USERS"
    OWNER to restapp;