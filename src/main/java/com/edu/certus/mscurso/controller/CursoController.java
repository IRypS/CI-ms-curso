package com.edu.certus.mscurso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.certus.mscurso.dto.CursoDto;
import com.edu.certus.mscurso.dto.ResponseDto;
import com.edu.certus.mscurso.service.CursoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping( "/v1/curso" )
@Api( tags = "Controlador Curso", description = "Controlador para las interacciones con el objeto Curso" )
public class CursoController {
    
    
    
    @Autowired
    private CursoService cursoService;



    @ApiOperation( value = "Método para listar los cursos visibles",
        notes = "- Lista todos los cursos cuyo estado sea **true**" )
    @GetMapping
    public ResponseEntity< ResponseDto > readCursos() {
        return ResponseEntity.status( HttpStatus.OK ).body( cursoService.getListaCursos() );
    }



    @ApiOperation( value = "Método para listar todos los cursos",
        notes = "- Lista todos los cursos sin importar su visibilidad (estado)" )
    @GetMapping( "/all" )
    public ResponseEntity< ResponseDto > readAllCursos() {
        return ResponseEntity.status( HttpStatus.OK ).body( cursoService.getAllCursos() );
    }



    @ApiOperation( value = "Método para obtener un curso visible mediante su ID", 
        notes = "- Solo encuentra el curso si tiene el estado con el valor **true**" )
    @GetMapping( "/{id}" )
    public ResponseEntity< ResponseDto > readCurso( @PathVariable( "id" ) Long id ) {
        return ResponseEntity.status( HttpStatus.OK ).body( cursoService.getCurso( id ) );
    }



    @ApiOperation( value = "Método para crear un curso",
        notes = "- Al crearse, el atributo \"estado\" se inicializa en [True] \n" + 
                "- El atributo ID se crea una vez se registra en la base de datos \n" + 
                "- **No se creará el curso si ya existe uno visible con la misma descripción**" )
    @PostMapping
    public ResponseEntity< ResponseDto > createCurso( @RequestBody CursoDto curso ) {
        return ResponseEntity.status( HttpStatus.OK ).body( cursoService.createCurso(curso) );
    }



    @ApiOperation( value = "Método para actualizar un curso",
        notes = "- Se debe incluir todo el objeto curso, incluyendo los atributos que no se actualizan \n" +
                "- El ID del objeto debe existir en la base de datos \n" +
                "- **No se actualiza el estado**" )
    @PutMapping
    public ResponseEntity< ResponseDto > updateCurso( @RequestBody CursoDto curso ) {
        return ResponseEntity.status( HttpStatus.OK ).body( cursoService.updateCurso(curso) );
    }

    

    @ApiOperation( value = "Método para eliminar un curso mediante su id",
        notes = "- El borrado es lógico (cambia el estado de **true** a **false**) \n" +
                "- Solo borrará los cursos visibles (no encontrá el registro si ya se encuentra borrado)" )
    @DeleteMapping( "/{id}" )
    public ResponseEntity< ResponseDto > deleteCurso( @PathVariable( "id" ) Long id ) {
        return ResponseEntity.status( HttpStatus.OK ).body( cursoService.deleteCurso( id ) );
    }

}
