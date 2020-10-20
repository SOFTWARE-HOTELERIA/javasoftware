/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  josel
 * Created: 16/10/2020
 */

--consulta #1 BUSQUEDA CLIENTE fijate en el figma lo que te pide
SELECT c.nombre,c.apellido,c.telefono,td.descripcion,c.correo ,c.nacionalidad 
FROM clientes c left JOIN tipo_documento td on c.documentoId =td.documentoId
where c.numeroIdentidad = '65821548'
--SI FUNCIONA OJO TE ENVIE CAPTURA DE LO QUE SALIO
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
--b) Mostrar el Total acumulado del costo final
--c ) Mostrar el total de registros que existen.



--consulta #6:
--a)Mostrar los siguientes datos: fecha hospedaje,numero habitacion,tipo habitacion,piso habitacion,costo de habitacion
-- referente al documento de identidad.


--consulta #7:
-- Actualizar datos del cliente considerando que va cerrar la estadia