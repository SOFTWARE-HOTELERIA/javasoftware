/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  josel
 * Created: 26/09/2020
 */

create database hoteleria;
create table categoria(
id_categoria int primary key not null,
nombre varchar(20) not null
);

create table producto(
cod_producto varchar(10) primary key not null,
nombre varchar(20)not null,
cantidad int not null,
precioventa decimal(10,2) not null,
id_categoria int not null,
foreign key (id_categoria) references categoria(id_categoria)
);
create table imagen_producto(
 imagenid varchar(35) not null PRIMARY KEY,
 imageUrl varchar(120) not null,
 codproducto varchar(10) not null,
 foreign key (codproducto) references producto(cod_producto) on delete cascade
);

create table usuario(
cod_usuario int not null auto_increment primary key,
nombre varchar(20) not null,
apellido varchar(20) not null,
email varchar(40) not null,
contraseña varchar(30) not null,
telefono varchar(10) not null,
cargo varchar(20)
);

create table cliente(
cod_cliente int primary key not null,
nombre varchar(20)not null,
apellido varchar(20)not null
);

create table venta(
seria_venta varchar(20) primary key not null,
importe decimal(5,2) not null,
total_neto decimal(8,2)not null,
fecha_venta date not null,
cod_cliente int not null,
cod_usuario int not null,
foreign key (cod_cliente) references cliente(cod_cliente),
foreign key (cod_usuario) references usuario(cod_usuario)
);

create table detalle_ventas(
productold varchar(10) not null,
ventald varchar(20) not null,
primary key(productold,ventald),
fecha DATETIME NOT NULL DEFAULT NOW(),
cantidad int not null,
importe decimal(7,2) not null,
foreign key (productold) references producto(cod_producto),
foreign key (ventald) references venta(seria_venta)
);
create table promociones(
 idpromociones int not null,
 fecha_apertura date not null,
 fecha_limite date not null,
 descuento decimal(5,2) not null,
 codproducto varchar(10) not null,
 foreign key (codproducto)references producto(cod_producto)
);
