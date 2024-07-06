create table if not exists delivery
(
    id              serial  primary key,
    customer_id     serial,
    order_number    varchar,
    product_id      serial,
    unit_type       varchar,
    qty             numeric,
    price           numeric,
    direction       varchar,
    delivery_time   timestamp DEFAULT NOW()
);

create table if not exists payment
(
    id              serial  primary key,
    customer_id     serial,
    order_number    varchar,
    amount          numeric,
    direction       varchar,
    delivery_time   timestamp DEFAULT NOW()
);