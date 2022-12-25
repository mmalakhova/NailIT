create table genders
(
    id   serial
        constraint pk_genders primary key,
    name varchar(128) not null
);

create table beauty_salons
(
    id      serial
        constraint pk_beauty_salons primary key,
    name    varchar(256)  not null,
    address varchar(256)  not null,
    rate    numeric(2, 1) not null,
    lat     varchar       not null,
    lon     varchar       not null
);

create table master_categories
(
    id          serial
        constraint pk_master_categories primary key,
    name        varchar(256) not null,
    description varchar(256)
);

create table masters
(
    id                 serial
        constraint pk_masters primary key,
    name               varchar(256)  not null,
    age                integer       not null,
    phone_number       varchar(256)  not null,
    rate               numeric(2, 1) not null,
    qualification      varchar(512)  not null,
    photo_url          varchar(256),
    status             varchar(256),
    gender_id          integer       not null
        constraint fk_masters_genders references genders on delete cascade,
    beauty_salon_id    integer       not null
        constraint fk_masters_beauty_salons references beauty_salons on delete cascade,
    master_category_id integer       not null
        constraint fk_masters_master_categories references master_categories on delete cascade
);

create table service_types
(
    id    serial
        constraint pk_service_types primary key,
    title varchar(256) not null
);

create table service
(
    id              serial
        constraint pk_services primary key,
    title           varchar(256) not null,
    time_estimate   interval     not null,
    price           int          not null,
    master_id       integer      not null
        constraint fk_services_masters references masters on delete cascade,
    service_type_id integer      not null
        constraint fk_services_service_types references service_types on delete cascade
);

create table clients
(
    id           serial
        constraint pk_clients primary key,
    name         varchar(256)  not null,
    age          int           not null,
    phone_number varchar(256)  not null,
    rate         numeric(2, 1) not null,
    gender_id    integer       not null
        constraint fk_clients_genders references genders on delete cascade
);

create table service_provision
(
    id        serial
        constraint pk_service_provisions primary key,
    title     varchar(256)             not null,
    date      timestamp with time zone not null,
    price     int                      not null,
    available bool                     not null,
    client_id integer
        constraint fk_service_provision_clients references clients on delete cascade,
    master_id integer                  not null
        constraint fk_service_provision_masters references masters on delete cascade
);
