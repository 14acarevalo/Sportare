CREATE DATABASE sportare;
USE sportare;

CREATE TABLE controleconomico (
idIngreso int primary key auto_increment,
ingresos varchar(45) not null,
fechaIngreso varchar(45) not null,
idUsuario int not null,
nombreUsuario varchar(255) not null,
apellidosUsuario varchar(255) not null,
saldo varchar(255) not null,
descripcion varchar(255) not null,

foreign key (idUsuario) references usuario(idUsuario)
);

CREATE TABLE usuario (
idUsuario int primary key auto_increment,
nombre varchar(255) not null,
apellidos varchar(255) not null,
correoElectronico varchar(255) not null,
password varchar(255) not null,
fechaNacimiento date not null,
dni varchar(255) not null,
nacionalidad varchar(255) not null,
ciudad varchar(100) not null,
direccion varchar(225) not null,
calle varchar(255) not null,
codigoPostal int,
telefono int not null,
saldo double,
genero varchar (40) not null,
permiso int not null
);

CREATE TABLE ServicioDeportivo (
idRegistro int primary key auto_increment,
nombre varchar(255) not null,
descripcion varchar(255) not null,
horaIniciacion varchar(255) not null,
minInicio varchar (255) not null,
horaFinalizacion varchar(255) not null,
minFinalizacion varchar(255) not null,
dia varchar(255) not null,
mes varchar(100) not null,
aforo int not null,
idUsuario int not null,
foreign key (idUsuario) references Usuario(idUsuario)
);
