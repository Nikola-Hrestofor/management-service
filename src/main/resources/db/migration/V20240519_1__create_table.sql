create table if not exists presale_card
(
    id              serial  primary key,
    uuid            uuid,
    item_id         serial,
    type            varchar NOT NULL,
    qty             numeric NOT NULL
);

create table if not exists product
(
    id              serial  primary key,
    uuid            uuid,
    name            varchar NOT NULL
);
