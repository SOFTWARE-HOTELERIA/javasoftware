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
 costo_final numeric(7,2) null,
 FOREIGN KEY (habitacionNum) references habitacion(nhabitacion),
 FOREIGN KEY (clienteCodigo) references clientes(clienteCodigo)
);

--clientes:
insert into clientes values('PO01','Scott','Mitchell','lidaniel@gmail.com','9898798',2,'545698','Peruano');
insert into clientes values('PO02','Julie','Ellis','markschristian@mcdaniel-lamb.com','091339',1,'4549978','Veneco');
insert into clientes values('PO03','William','Delacruz','sherry95@hayden.com','9877818',1,'56487','Veneco');
INSERT INTO clientes values('PO04','Robert','Bolaños','robert@gmail.com','996524234','1','9568978','PERU');
INSERT INTO clientes values('PO05','Carlos','Perez','carlos@gmail.com','996734','2','954568','PERU');


--insert into habitacion
 insert into habitacion(nhabitacion,estado,tipoHabitacionId,nivelId) values('A001','DISPONIBLE',1,1);
 insert into habitacion(nhabitacion,estado,tipoHabitacionId,nivelId) values('A002','OCUPADO',2,2);
insert into habitacion(nhabitacion,estado,tipoHabitacionId,nivelId) values('A003','DISPONIBLE',1,1);
 insert into habitacion(nhabitacion,estado,tipoHabitacionId,nivelId) values('A004','OCUPADO',2,2);



--insert into  tiphoabitacion
insert into tipo_habitacion(nhabitacion,descripcion,costo) values(1,'presidencial',55);
insert into tipo_habitacion(nhabitacion,descripcion,costo) values(2,'Individual',30);
--insert into nivel
insert into nivel(pisoId,cantidad) values(1,3);
insert into nivel(pisoId,cantidad) values(2,5);
---insert into reservahabitacion
insert into reservahabitacion values (P0O1,AOO1,'2020-10-26','2020-10-31',5,400)
insert into reservahabitacion values (P0O2,AOO2,'2020-10-22','2020-10-31',9,560)
insert into reservahabitacion values (P0O3,AOO3,'2020-10-29','2020-10-30',1,400)

---
create view estadiaFinalizada as
select h.nhabitacion,dias,fecha_entrada,fecha_salida,costo,costo_final,nombre,apellido from habitacion h
inner join reservahabitacion r on h.nhabitacion=r.habitacionnum 
left join clientes c on c.clientecodigo=r.clientecodigo
inner join nivel n on h.nivelid=n.pisoid
inner join tipo_habitacion t on h.tipohabitacionid=t.nhabitacion