
	create table orders.order(
    id bigint primary key,
		product_id bigint,
		status character varying(10),
	quantity integer,
	amount float,
	ordered_on timestamp with time zone
);



CREATE SEQUENCE orders.order_id_seq
START WITH 101
INCREMENT BY 1
NO MAXVALUE
NO MINVALUE
CACHE 1;
ALTER SEQUENCE orders.order_id_seq
OWNED BY orders.order.id;
ALTER TABLE ONLY orders.order ALTER COLUMN id SET DEFAULT NEXTVAL('orders.order_id_seq'::REGCLASS);