package com.edu.certus.mscurso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.certus.mscurso.entity.CursoAlumnoEntity;

@Repository
public interface CursoAlumnoRepository extends JpaRepository<CursoAlumnoEntity, Long>{
    
}
