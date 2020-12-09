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
 numeroreserva varchar(20) not null PRIMARY KEY,
 clienteCodigo varchar(15) not null,
 habitacionNum varchar(20) not null,
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
INSERT INTO clientes values('PO06','Richard','Jimenez','richard@gmail.com','996525','1','7519246','PERU');
INSERT INTO clientes values('PO07','Marina','Perez','marina@gmail.com','986312','2','013486','PERU');
INSERT INTO clientes values('PO08','Daniel','Castro','Daniel@gmail.com','985482','2','751426','PERU');
INSERT INTO clientes values('PO09','Peter','Hale','Peter@gmail.com','994214','2','953458','PERU');
INSERT INTO clientes values('PO10','Lydia','Garcia','lydia@gmail.com','458624','2','421685','PERU');
INSERT INTO clientes values('PO11','Stiles','Ramirez','Stiles@gmail.com','789541','2','631586','PERU');

--insert into habitacion
 insert into habitacion(nhabitacion,estado,tipoHabitacionId,nivelId) values('A001','DISPONIBLE',1,1);
 insert into habitacion(nhabitacion,estado,tipoHabitacionId,nivelId) values('A002','DISPONIBLE',2,2);
insert into habitacion(nhabitacion,estado,tipoHabitacionId,nivelId) values('A003','DISPONIBLE',1,1);
 insert into habitacion(nhabitacion,estado,tipoHabitacionId,nivelId) values('A004','DISPONIBLE',2,2);
insert into habitacion(nhabitacion,estado,tipoHabitacionId,nivelId) values('A005','DISPONIBLE',4,3);
insert into habitacion(nhabitacion,estado,tipoHabitacionId,nivelId) values('A006','DISPONIBLE',3,2);
insert into habitacion(nhabitacion,estado,tipoHabitacionId,nivelId) values('A007','DISPONIBLE',1,1);
insert into habitacion(nhabitacion,estado,tipoHabitacionId,nivelId) values('A008','DISPONIBLE',3,4);
insert into habitacion(nhabitacion,estado,tipoHabitacionId,nivelId) values('A009','DISPONIBLE',2,4);
insert into habitacion(nhabitacion,estado,tipoHabitacionId,nivelId) values('A0010','DISPONIBLE',3,3);
insert into habitacion(nhabitacion,estado,tipoHabitacionId,nivelId) values('A0011','DISPONIBLE',3,4);
insert into habitacion(nhabitacion,estado,tipoHabitacionId,nivelId) values('A0012','DISPONIBLE',2,4);
insert into habitacion(nhabitacion,estado,tipoHabitacionId,nivelId) values('A0013','DISPONIBLE',1,2);


insert into reservahabitacion values ('TZ-01','PO01','A001','2020-10-26','2020-10-31',5,400);
insert into reservahabitacion values ('TZ-02','PO02','A002','2020-10-22','2020-10-31',9,560);	
insert into reservahabitacion values ('TZ-03','PO03','A003','2020-10-29','2020-10-30',1,400);
insert into reservahabitacion values ('TZ-04','PO04','A004','2020-10-29','2020-10-30',1,30);
insert into reservahabitacion values ('TZ-05','PO05','A002','2020-11-15','2020-11-17',2,110);
insert into reservahabitacion values ('TZ-06','PO06','A004','2020-11-22','2020-11-25',3,90);
insert into reservahabitacion values ('TZ-07','PO07','A008','2020-11-26','2020-11-29',3,165);
insert into reservahabitacion values ('TZ-08','PO08','A007','2020-11-30','2020-12-04',5,275);
insert into reservahabitacion values ('TZ-09','PO09','A006','2020-11-30','2020-12-04',5,275);
insert into reservahabitacion values ('TZ-10','PO06','A005','2020-11-30','2020-12-04',5,275);
---

--insert into  tiphoabitacion
insert into tipo_habitacion(nhabitacion,descripcion,costo) values(1,'presidencial',55);
insert into tipo_habitacion(nhabitacion,descripcion,costo) values(2,'Individual',30);
insert into tipo_habitacion(nhabitacion,descripcion,costo) values(3,'Doble',45);
insert into tipo_habitacion(nhabitacion,descripcion,costo) values(4,'Triple',30);
--insert into nivel
insert into nivel(pisoId,cantidad) values(1,3);
insert into nivel(pisoId,cantidad) values(2,5);
insert into nivel(pisoId,cantidad) values(3,6);
insert into nivel(pisoId,cantidad) values(4,5);


---insert into reservahabitacionDER BY (date_part('month'::text, rh.fecha_salida));
---vista para reporte para cierre de estadia
create view estadiaFinalizada as
select numeroreserva,c.clientecodigo,h.nhabitacion,dias,fecha_entrada,fecha_salida,t.descripcion,costo,costo_final,nombre,apellido,n.pisoid,numeroIdentidad from habitacion h
inner join reservahabitacion r on h.nhabitacion=r.habitacionnum 
left join clientes c on c.clientecodigo=r.clientecodigo
inner join nivel n on h.nivelid=n.pisoid
inner join tipo_habitacion t on h.tipohabitacionid=t.nhabitacion


--vista para  reporte grafico por meses referente al año
CREATE OR REPLACE VIEW public."reporteanyo"
 AS
 SELECT count(cli.clientecodigo) AS hospedados,
    to_char(rh.fecha_salida::timestamp with time zone, 'month'::text) AS mestexto,
    rh.fecha_salida
   FROM clientes cli
     JOIN reservahabitacion rh ON rh.clientecodigo::text = cli.clientecodigo::text
  GROUP BY (date_part('month'::text, rh.fecha_salida)), (to_char(rh.fecha_salida::timestamp with time zone, 'month'::text)), rh.fecha_salida
  ORDER BY (date_part('month'::text, rh.fecha_salida));