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
select H.nhabitacion as 'Habitacion',H.estado as 'Estado de la habitacion',H.pisoId as'# Piso'
from habitacion H
left join tipo_Habitacion T
on H.nhabitacion=T.nhabitacion
where T.descripcion_tipo='media'
--b)mostrar costo total referente al tipo de habitacion anterior
--c)Mostrar el total de habitaciones referente al tipo de habitacion anterior
---------Muestra Cod.Cliente, # Habitacion, Fecha de entrada y salida, Dias de estadia y costo total; Buscado Mediante el Cod.Cliente
select R.clienteCodigo as 'Cod. Cliente',T.nhabitacion as '# Habitacion',H.pisoId as '# Piso',R.fecha_entrada as 'Ingreso',R.fecha_salida as 'Salida',R.dias,R.costo_final as 'Costo total'
from tipo_Habitacion T 
inner join reservahabitacion R on T.nhabitacion=R.nhabitacion 
inner join habitacion H on T.nhabitacion=H.nhabitacion
where R.clienteCodigo='0001'

--consulta #3 
--a)Registrar hospedaje referente basandose al cliente


--consulta #4
--INPRETAR A SU CRITERIO





--consulta #5 MOSTRAR REFERENTE A LAS DOS FECHAS MENCIONADAS 
--a)Listar los siguientes datos nombre,apellido,telelono,tipo_documento,fecha entrada,fecha salida,costo final
select row_number() over(),cli.nombre,cli.apellido,cli.numeroidentidad,rh.fecha_entrada,rh.fecha_salida,rh.costo_final\n
                                        from clientes cli inner join reservahabitacion rh\
                                        on cli.clientecodigo = rh.clientecodigo\n
                                        where rh.fecha_salida between '10/10/2020' and '10/11/2020'

--b) Mostrar el Total acumulado del costo final
select sum(rh.costo_final)as 'Costo Total' from reservahabitacion rh
where rh.fecha_salida between '10/10/2020' and '10/11/2020'
--c ) Mostrar el total de registros que existen.
select count(rh.ClienteCodigo)as 'Cantidad de Clientes' from reserbahabitacion rh
where rh.fecha_salida between '10/10/2020' and '10/11/2020'
--consulta #6:
--a)Mostrar los siguientes datos: fecha hospedaje,numero habitacion,tipo habitacion,piso habitacion,costo de habitacion
-- referente al documento de identidad.


--consulta #7:
-- Actualizar datos del cliente considerando que va cerrar la estadia