create table if not exists customers
(
    id            serial primary key,
    customer_name varchar(50),
    user_name varchar(50),
    password varchar(10),
    address       varchar(255)
    );

create table if not exists orders
(
    id          serial primary key,
    create_at   varchar(10),
    total       int,
    payment_status VARCHAR(50),
    customer_id int references customers (id)
    );

CREATE TABLE IF NOT EXISTS TYPES
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE
    );


create table if not exists products
(
    id           serial primary key,
    product_name varchar(50),
    number_product int,
    price double precision,
    type_id  int references types (id)
    );

create table if not exists order_product
(
    order_id   int references orders (id),
    product_id int references products (id),
    quantity   int
    );