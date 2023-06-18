package com.edu.certus.mscurso.service;

import java.util.List;

import com.edu.certus.mscurso.dto.CursoAlumnoDto;

public interface CursoAlumnoService {
    
    public List<CursoAlumnoDto> getAllListCursoAlumno();
    public CursoAlumnoDto getCursoAlumno( Long id );

}
