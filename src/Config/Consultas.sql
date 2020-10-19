/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  josel
 * Created: 16/10/2020
 */

--consulta #1 BUSQUEDA CLIENTE 


--consulta #2 
--a)Mostrar datos: habitacion,estado,piso referente a cualquier tipo de habitacion
--b)mostrar costo total referente al tipo de habitacion anterior
--c)Mostrar el total de habitaciones referente al tipo de habitacion anterior


--consulta #3 
--a)Registrar hospedaje referente basandose al cliente


--consulta #4
--INPRETAR A SU CRITERIO





--consulta #5 MOSTRAR REFERENTE A LAS DOS FECHAS MENCIONADAS 
--a)Listar los siguientes datos nombre,apellido,telelono,tipo_documento,fecha entrada,fecha salida,costo final
select cli.nombre,cli.apellido,cli.telefono,td.descripcion,rh.fecha_entrada,rh.fecha_salida,rh.costo_final
from cliente cli inner join reservahabitacion rh
on cli.ClienteCodigo=rh.ClienteCodigo inner join tipo_Documento td
on cli.documentold=td.documentold

--b) Mostrar el Total acumulado del costo final
--c ) Mostrar el total de registros que existen.
select sum(rh.costo_final)as 'Costo Total',count(rh.ClienteCodigo)as 'Cantidad de Clientes' from reservahabitacion rh


--consulta #6:
--a)Mostrar los siguientes datos: fecha hospedaje,numero habitacion,tipo habitacion,piso habitacion,costo de habitacion
-- referente al documento de identidad.


--consulta #7:
-- Actualizar datos del cliente considerando que va cerrar la estadia