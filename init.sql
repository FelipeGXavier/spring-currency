create table IF NOT EXISTS currency_type(
id serial primary key,
type varchar(5) unique not null,
symbol varchar(3)
);


create table IF NOT EXISTS currency(
id serial primary key,
value float not null,
type_id int,
foreign key (type_id) references currency_type(id),
CONSTRAINT type_id_fk UNIQUE (type_id)
);


insert into currency_type (type, symbol) values ('USD', '$'),('EUR', '€'),('BRL', 'R$'), ('JPY','¥');