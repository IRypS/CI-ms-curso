package com.edu.certus.mscurso.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.certus.mscurso.dto.CursoAlumnoDto;
import com.edu.certus.mscurso.entity.CursoAlumnoEntity;
import com.edu.certus.mscurso.repository.CursoAlumnoRepository;
import com.edu.certus.mscurso.service.CursoAlumnoService;
import com.edu.certus.mscurso.util.Constantes;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CursoAlumnoServiceImpl implements CursoAlumnoService{


    @Autowired
    private CursoAlumnoRepository cursoAlumnoRepository;

    
    @Override
    public List<CursoAlumnoDto> getAllListCursoAlumno() {

        try {
            List<CursoAlumnoEntity> cursoAlumnoEntityList = cursoAlumnoRepository.findAll();

            if ( cursoAlumnoEntityList.isEmpty() ) {
                return null;
            }

            List<CursoAlumnoDto> cursoAlumnoDtoList = new ArrayList<CursoAlumnoDto>();

            for ( CursoAlumnoEntity cursoAlumnoEntity : cursoAlumnoEntityList ) {
                cursoAlumnoDtoList.add(
                    CursoAlumnoDto.builder()
                        .id_curso( cursoAlumnoEntity.getId_curso() )
                        .id_alumno( cursoAlumnoEntity.getId_alumno() )
                        .build()
                );
            }

            return cursoAlumnoDtoList;

        } catch ( Exception e ) {
            log.error( Constantes.OPERATION_FAILED, e );
            return null;
        }
    }
    
    @Override
    public CursoAlumnoDto getCursoAlumno( Long id ) {

        try {
            CursoAlumnoEntity cursoAlumnoFound = cursoAlumnoRepository.findById( id ).orElse( null );

            if ( cursoAlumnoFound == null ) {
                return null;
            }

            return CursoAlumnoDto.builder()
                .id_curso( cursoAlumnoFound.getId_curso() )
                .id_alumno( cursoAlumnoFound.getId_alumno() )
                .build();

        } catch ( Exception e ) {
            log.error( Constantes.OPERATION_FAILED, e );
            return null;
        }
    }

}
