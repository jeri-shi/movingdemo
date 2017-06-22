use hello;
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

use hello;

create table if not exists companyusers (
	id int not null AUTO_INCREMENT primary key,
	company varchar(50) not null,
 	username varchar(50) not null,
	password varchar(50) not null,
	enabled boolean not null,
    CONSTRAINT  UNIQUE index_company_username (company, username)
);

insert into companyusers (company, username, password, enabled) values ('Learn', 'Wenddy', '111', true);
insert into companyusers (company, username, password, enabled) values ('Learn', 'Jin', '111', true);

select * from companyusers;

create table if not exists companyauthorities  (
	id int not null AUTO_INCREMENT primary key,
	authority varchar(50) not null,
    userId int not null,
    foreign key (userId) references companyusers (id)
);

insert into companyauthorities (authority, userId) values ('USER', 1);
insert into companyauthorities (authority, userId) values ('USER', 2);
insert into companyauthorities (authority, userId) values ('ADMIN', 2);
