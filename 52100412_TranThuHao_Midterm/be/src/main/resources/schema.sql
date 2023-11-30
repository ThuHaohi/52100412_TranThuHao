drop table if exists order_detail;
drop table if exists shopping_order;
drop table if exists customer;
drop table if exists vehicle;
drop table if exists brand;


create table if not exists brand
(
    id   varchar(36)  not null
        primary key,
    name varchar(200) null
);

create table if not exists customer
(
    id       varchar(36)  not null
        primary key,
    username varchar(200) null,
    password varchar(200) null
);

create table if not exists shopping_order
(
    id          varchar(36)  not null
        primary key,
    customer_id varchar(36)  null,
    status      varchar(200) null,
    constraint order_customer_null_fk
        foreign key (customer_id) references customer (id)
);

create table if not exists vehicle
(
    id          varchar(36) charset utf8mb4   not null
        primary key,
    title       varchar(200) charset utf8mb4  null,
    description varchar(2000) charset utf8mb4 null,
    price       double                        null,
    path        varchar(1000) charset utf8mb4 null,
    brand_id    varchar(36) charset utf8mb4   null,
    constraint vehicle_brand_null_fk
        foreign key (brand_id) references brand (id)
)
    collate = utf8mb4_general_ci;

create table if not exists order_detail
(
    id         varchar(36) not null
        primary key,
    amount     int         null,
    vehicle_id varchar(36) null,
    order_id   varchar(36) null,
    constraint order_detail_shopping_order_null_fk
        foreign key (order_id) references shopping_order (id),
    constraint order_detail_vehicle_null_fk
        foreign key (vehicle_id) references vehicle (id)
);

