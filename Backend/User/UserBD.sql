drop table if exists user;
create table user (
    id int primary key auto_increment,
    username VARCHAR(200) NOT NULL,
    pass VARCHAR(200) NOT NULL,
    name varchar(200) not null,
    lastname varchar(500) NOT NULL,
    birthDate DATE NOT NULL,
    position VARCHAR(200) NOT NULL,
    activo BOOL NOT NULL
);
INSERT INTO user
(username, pass, name, lastname, birthDate,position,activo) values
('laurozo02', 'hola', 'laura', 'rozo','2002/01/02','admin',TRUE);
	
