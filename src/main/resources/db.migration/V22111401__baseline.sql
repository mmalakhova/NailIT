create table genders
(
    id   serial       not null
        constraint pk_genders primary key,
    name varchar(128) not null
);

create table beauty_salons
(
    id      serial       not null
        constraint pk_beauty_salons primary key,
    name    varchar(256) not null,
    address varchar(256) not null
);

create table master_categories
(
    id          serial       not null
        constraint pk_master_categories primary key,
    name        varchar(256) not null,
    description varchar(256)
);

create table masters
(
    id                 serial        not null
        constraint pk_masters primary key,
    name               varchar(256)  not null,
    age                integer       not null,
    phone_number       varchar(256)  not null,
    email              varchar(256)  not null,
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
