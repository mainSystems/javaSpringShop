-- DROP SCHEMA public;

CREATE SCHEMA public AUTHORIZATION pg_database_owner;

-- Drop table

-- DROP TABLE public.customers;

CREATE TABLE public.customers (
	id int8 NOT NULL DEFAULT nextval('customer_customer_id_seq'::regclass),
	title varchar NOT NULL,
	username varchar NOT NULL,
	"password" varchar NOT NULL,
	CONSTRAINT customer_pk PRIMARY KEY (id)
);

-- Drop table

-- DROP TABLE public.orders;

CREATE TABLE public.orders (
	id int8 NOT NULL DEFAULT nextval('orders_order_id_seq'::regclass),
	product_id int4 NOT NULL,
	customer_id int4 NOT NULL,
	"date" varchar NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"cost" int4 NOT NULL DEFAULT 0,
	quantity int4 NOT NULL DEFAULT 0,
	CONSTRAINT orders_pk PRIMARY KEY (id),
	CONSTRAINT orders_fk_customer FOREIGN KEY (customer_id) REFERENCES public.customers(id),
	CONSTRAINT orders_fk_product FOREIGN KEY (product_id) REFERENCES public.products(id)
);

-- Drop table

-- DROP TABLE public.products;

CREATE TABLE public.products (
	id bigserial NOT NULL,
	title varchar(80) NOT NULL,
	"cost" int4 NOT NULL,
	CONSTRAINT products_pkey PRIMARY KEY (id)
);

-- Drop table

-- DROP TABLE public.roles;

CREATE TABLE public.roles (
	id bigserial NOT NULL,
	"name" varchar NOT NULL,
	CONSTRAINT roles_pk PRIMARY KEY (id)
);

-- Drop table

-- DROP TABLE public.users_roles;

CREATE TABLE public.users_roles (
	user_id int8 NOT NULL,
	role_id int4 NOT NULL,
	CONSTRAINT users_roles_pk PRIMARY KEY (user_id, role_id),
	CONSTRAINT users_roles_fk FOREIGN KEY (user_id) REFERENCES public.customers(id),
	CONSTRAINT users_roles_fk_1 FOREIGN KEY (role_id) REFERENCES public.roles(id)
);
