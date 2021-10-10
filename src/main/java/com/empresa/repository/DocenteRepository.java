package com.empresa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;
import com.empresa.entity.Docente;
import com.empresa.entity.FiltroDocente;
public interface DocenteRepository extends JpaRepository<Docente, Integer> {

	public List<Docente> findByDni(String dni);
	
	@Query("select d from Docente d where d.nombre like ?1% ")
	public List<Docente> listarDocentePorNombre(String nomPro);
	
	
	@Query("select d from Docente d where (:dni is '' or d.dni= :dni) and (:nombre is '' or d.nombre like :nombre% )")
	public List<Docente> listarDocentePorDniNombre(@Param("dni") String dni, @Param("nombre") String nombre);
	
	@Query("select d from Docente d where "
					+ "	( :#{#fil.dni} is ''  or d.dni = :#{#fil.dni} ) and "
					+ "( :#{#fil.nombre} is '' or d.nombre like :#{#fil.nombre} ) and"
					+ "( :#{#fil.idUbigeo} is 0 or d.ubigeo.idUbigeo = :#{#fil.idUbigeo} ) ")

	public List<Docente> listaPorFiltro(@Param("fil") FiltroDocente filtro );
	
}


