# SQL_CallableStatement
DATABASE AND TABLES REQUIRED
create database academia;

use academia;

create table curso(cod_curso int, nombre varchar(30), horas smallint, turno enum('mañana','tarde') not null, mes_comienzo varchar(12), primary key (cod_curso));

create table alumno(cod_alumno int, DNI char(9) not null, nombre varchar(15), apellidos varchar(50), direccion varchar(50), localidad varchar(20), f_nac date, tfno char(9) not null, primary key (cod_alumno));

create table matricula(cod_curso int, cod_alumno int, fecha_mat date not null, calificacion enum('Apto', 'No Apto'),
    primary key (cod_curso, cod_alumno),
    foreign key (cod_curso) references curso(cod_curso),
    foreign key (cod_alumno) references alumno(cod_alumno));
    
--------------------
PROCEDURES AND FUNCTION REQUIRED
1.
delimiter //
create procedure matricula_alumno (in c_alumno int, out n_asignaturas int)
begin
	select count(*) into n_asignaturas from matricula
	where cod_alumno = c_alumno;
end
//
delimiter ;

-------------
2.
delimiter //
create procedure fecha_comienzo (in c_curso int, out mes varchar(12))
begin
	select mes_comienzo into mes from curso
	where cod_curso = c_curso;
end
//
delimiter ;
-------------
3.
delimiter //
create function calificacion(c_curso int, c_alumno int) returns varchar(20)
deterministic
begin
	declare salida varchar(20);
	declare consulta int default 0;
	select count(*) into consulta from matricula
	where cod_curso = c_curso and cod_alumno = c_alumno;
	if (consulta = 0) then
		set salida = 'NO MATRICULADO';
		return salida;
	else
		select calificacion into salida from matricula where cod_curso = c_curso and cod_alumno = c_alumno;
		return salida;
	end if;
end
//
delimiter ;
