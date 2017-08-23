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

drop table companyauthorities;
drop table companyusers;


create table if not exists companyusers (
	id int not null AUTO_INCREMENT primary key,
	company varchar(50) not null,
 	username varchar(50) not null,
	password varchar(50) not null,
	enabled boolean default false not null,
    CONSTRAINT  UNIQUE index_company_username (company, username)
);

insert into companyusers (company, username, password, enabled) values ('Learn', 'Wenddy', '111', true);
insert into companyusers (company, username, password, enabled) values ('Learn', 'Jin', '111', true);
insert into companyusers (company, username, password, enabled) values ('Learn', 'Jin1', '111', true);
insert into companyusers (company, username, password, enabled) values ('Learn', 'Jin2', '111', true);
insert into companyusers (company, username, password, enabled) values ('Learn', 'Jin3', '111', true);
insert into companyusers (company, username, password, enabled) values ('Learn', 'Jin4', '111', true);
insert into companyusers (company, username, password, enabled) values ('Learn', 'Jin5', '111', true);
insert into companyusers (company, username, password, enabled) values ('Learn', 'Jin6', '111', true);
insert into companyusers (company, username, password, enabled) values ('Learn', 'Jin7', '111', true);
insert into companyusers (company, username, password, enabled) values ('Learn', 'Jin8', '111', true);
insert into companyusers (company, username, password, enabled) values ('Learn', 'Jin9', '111', true);
insert into companyusers (company, username, password, enabled) values ('Learn', 'Jin10', '111', true);
insert into companyusers (company, username, password, enabled) values ('Learn', 'Jeri Shi', '111', true);

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
insert into companyauthorities (authority, userId) values ('USER', 3);
insert into companyauthorities (authority, userId) values ('USER', 4);
insert into companyauthorities (authority, userId) values ('USER', 5);
insert into companyauthorities (authority, userId) values ('USER', 6);
insert into companyauthorities (authority, userId) values ('USER', 7);
insert into companyauthorities (authority, userId) values ('USER', 8);
insert into companyauthorities (authority, userId) values ('USER', 9);
insert into companyauthorities (authority, userId) values ('USER', 10);
insert into companyauthorities (authority, userId) values ('USER', 11);
insert into companyauthorities (authority, userId) values ('USER', 12);
insert into companyauthorities (authority, userId) values ('USER', 13);

select * from companyusers u inner join companyauthorities a on u.id = a.userId
where u.company = 'Learn' and u.username = 'jin';

select u.company, u.username, u.password, u.enabled, a.authority from companyusers u inner join companyauthorities a on u.id = a.userId
where u.company = 'Learn' and u.username = 'jin'
