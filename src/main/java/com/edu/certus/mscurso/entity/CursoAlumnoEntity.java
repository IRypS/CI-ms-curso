package com.edu.certus.mscurso.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Table( name = "alumno_curso" )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoAlumnoEntity {

    @Id
    @Column( name = "cod_curso" )
    private Long id_curso;
    
    @Column( name = "cod_alumno" )
    private Long id_alumno;
    
}
