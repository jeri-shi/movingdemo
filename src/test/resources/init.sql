
drop table if exists authorities;
drop table if exists users;

create table if not exists users (
	username varchar(50) not null primary key,
	password varchar(50) not null,
	enabled boolean not null
);

create table if not exists authorities  (
	username varchar(50) not null,
    authority varchar(50) not null,
    foreign key (username) references users (username)
);

create unique index ix_auth_username on authorities (username,authority);

insert into users values('Jeri', '111111', true);
insert into users values('Sunny', '222', true);

insert into authorities values ('Jeri', 'ADMIN');
insert into authorities values ('Jeri', 'USER');
insert into authorities values ('Sunny', 'USER');
