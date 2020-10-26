/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  josel
 * Created: 26/09/2020
 */
create database javahotel
-- ready
 create table tipo_documento( 
  documentoId serial,
  descripcion varchar(50) not null,
 PRIMARY KEY(documentoId)
 );
-- 
 create table clientes(
  clienteCodigo varchar(15) not null PRIMARY KEY,
  nombre varchar(30) not null,
  apellido varchar(30) not nulL,
  correo varchar(50) not null,
  telefono varchar(10) not null,
  documentoId int not null,
  numeroIdentidad varchar(20) not null UNIQUE,
  nacionalidad varchar(20) not null,
  FOREIGN KEY (documentoId) references tipo_documento(documentoId)
 );
create table nivel(
 pisoId int not null primary key,
 cantidad int not null
);
create table tipo_habitacion(
 nhabitacion serial PRIMARY KEY,
 descripcion text not null,
 costo numeric(4,1) not null
);
-----------------------
create table habitacion(
 nhabitacion varchar(20) not null PRIMARY KEY,
 estado varchar(15) not null,
 tipoHabitacionId int not null,
 nivelId int not null,
 FOREIGN KEY (nivelId) references nivel(pisoId),
 FOREIGN KEY (tipoHabitacionId) references tipo_habitacion(nhabitacion)
);

create table reservahabitacion(
 clienteCodigo varchar(15) not null,
 habitacionNum varchar(20) not null,
 PRIMARY KEY(clienteCodigo,habitacionNum),
 fecha_entrada date not null,
 fecha_salida date null,
 dias int null,
 costo_final numeric(7,2) not null,
 FOREIGN KEY (habitacionNum) references habitacion(nhabitacion),
 FOREIGN KEY (clienteCodigo) references clientes(clienteCodigo)
);

