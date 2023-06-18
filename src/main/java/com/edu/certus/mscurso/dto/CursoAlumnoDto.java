package com.edu.certus.mscurso.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoAlumnoDto {

    private Long id_curso;
    private Long id_alumno;
    
}
