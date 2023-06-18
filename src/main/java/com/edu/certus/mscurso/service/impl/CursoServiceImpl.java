package com.edu.certus.mscurso.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.certus.mscurso.dto.CursoDto;
import com.edu.certus.mscurso.dto.ResponseDto;
import com.edu.certus.mscurso.entity.CursoEntity;
import com.edu.certus.mscurso.repository.CursoRepository;
import com.edu.certus.mscurso.service.CursoService;
import com.edu.certus.mscurso.util.Constantes;
import com.edu.certus.mscurso.util.Util;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CursoServiceImpl implements CursoService {


    @Autowired
    private CursoRepository cursoRepository;


    @Override
    public ResponseDto getAllCursos() {

        try {
            
            List<CursoEntity> listaCursosEntities = cursoRepository.findAll();

            if ( listaCursosEntities.isEmpty() ) {
                return Util.getResponse( true, Constantes.NO_RECORDS_FOUND, null );
            }

            List<CursoDto> listaCursosDtos = new ArrayList<>();

            for( CursoEntity cursoEntity : listaCursosEntities ) {
                listaCursosDtos.add(
                    CursoDto.builder()
                        .id( cursoEntity.getId() )
                        .descripcion( cursoEntity.getDescripcion() )
                        .estado( cursoEntity.isEstado() )
                        .build()
                );
            }

            return Util.getResponse( true, Constantes.OPERATION_SUCCESS, listaCursosDtos );
            
        } catch ( Exception e ) {
            log.error( Constantes.OPERATION_FAILED, e );
            return Util.getResponse( false, Constantes.OPERATION_FAILED, null );
        }

    };



    @Override
    public ResponseDto getListaCursos() {

        try {
            
            List<CursoEntity> listaCursosEntities = cursoRepository.findAllByEstadoTrue();

            if ( listaCursosEntities.isEmpty() ) {
                return Util.getResponse( true, Constantes.NO_RECORDS_FOUND, null );
            }

            List<CursoDto> listaCursosDtos = new ArrayList<>();

            for( CursoEntity cursoEntity : listaCursosEntities ) {
                listaCursosDtos.add(
                    CursoDto.builder()
                        .id( cursoEntity.getId() )
                        .descripcion( cursoEntity.getDescripcion() )
                        .estado( cursoEntity.isEstado() )
                        .build()
                );
            }

            return Util.getResponse( true, Constantes.OPERATION_SUCCESS, listaCursosDtos );
            
        } catch ( Exception e ) {
            log.error( Constantes.OPERATION_FAILED, e );
            return Util.getResponse( false, Constantes.OPERATION_FAILED, null );
        }

    };



    @Override
    public ResponseDto getCurso( Long id ) {

        try {

            CursoEntity cursoEntityFound = cursoRepository.findByIdAndEstadoTrue(id);
            

            if ( cursoEntityFound == null ) {
                return Util.getResponse( true, Constantes.NO_RECORDS_FOUND, null );
            }

            // System.out.println( "Ids encontrados de alumnos: " );

            // for ( CursoAlumnoEntity cursoAlumnoEntity : cursoEntityFound.getListCursoAlumnoEntity() ) {
            //     System.out.println( "-> " + cursoAlumnoEntity.getId_alumno() );
            // }

            CursoDto cursoDto = CursoDto.builder()
                .id( cursoEntityFound.getId() )
                .descripcion( cursoEntityFound.getDescripcion() )
                .estado( cursoEntityFound.isEstado() )
                .build();
            
            return Util.getResponse( true, Constantes.OPERATION_SUCCESS, cursoDto );
            
        } catch ( Exception e ) {
            log.error( Constantes.OPERATION_FAILED, e );
            return Util.getResponse( false, Constantes.OPERATION_FAILED, null );
        }
    };



    @Override
    public ResponseDto createCurso( CursoDto curso ) {

        try {

            CursoEntity cursoEntityFound = cursoRepository.findByDescripcionAndEstadoTrue( curso.getDescripcion() );

            if ( cursoEntityFound != null ) {
                return Util.getResponse( false, Constantes.ALREADY_EXISTS, cursoEntityFound );
            }
            
            CursoEntity cursoEntity = CursoEntity.builder()
                .descripcion( curso.getDescripcion() )
                .estado( true )
                .build();
            
            cursoRepository.save( cursoEntity );
            curso.setId( cursoEntity.getId() );
            return Util.getResponse( true, Constantes.OPERATION_SUCCESS, curso );
            
        } catch ( Exception e ) {
            log.error( Constantes.OPERATION_FAILED, e );
            return Util.getResponse( false, Constantes.OPERATION_FAILED, null );
        }
    };



    @Override
    public ResponseDto updateCurso( CursoDto curso ) {
        
        try {
            
            CursoEntity cursoEntityFound = cursoRepository.findByIdAndEstadoTrue( curso.getId() );

            if ( cursoEntityFound == null ) {
                return Util.getResponse( false, Constantes.NO_RECORDS_FOUND, null );
            }

            cursoEntityFound.setDescripcion( curso.getDescripcion() );
            cursoRepository.save( cursoEntityFound );
            return Util.getResponse( true , Constantes.OPERATION_SUCCESS, cursoEntityFound );
            
        } catch ( Exception e ) {
            log.error( Constantes.OPERATION_FAILED, e );
            return Util.getResponse( false, Constantes.OPERATION_FAILED, e );
        }
    };



    @Override
    public ResponseDto deleteCurso( Long id ) {
        try {

            CursoEntity cursoEntityFound = cursoRepository.findByIdAndEstadoTrue( id );

            if ( cursoEntityFound == null ) {
                return Util.getResponse( false, Constantes.NO_RECORDS_FOUND, null );
            }
            
            cursoEntityFound.setEstado( false );
            cursoRepository.save( cursoEntityFound );
            return Util.getResponse( true, Constantes.OPERATION_SUCCESS, null );
            
        } catch ( Exception e ) {
            log.error( Constantes.OPERATION_FAILED, e );
            return Util.getResponse( false, Constantes.OPERATION_FAILED, e );
        }
    };

    
}