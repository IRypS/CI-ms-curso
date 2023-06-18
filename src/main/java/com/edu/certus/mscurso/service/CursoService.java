package com.edu.certus.mscurso.service;

import com.edu.certus.mscurso.dto.CursoDto;
import com.edu.certus.mscurso.dto.ResponseDto;

public interface CursoService {
    
    /**
     * Lista todos los cursos sin importar su estado
     * @return Objeto **ResponseDto** con los siguientes resultados: 
     *         - Código exitoso y listado de cursos si se encuentran datos.
     *         - Código exitoso si no se encuentran cursos.
     *         - Código fallido en caso de error.
     */
    public ResponseDto getAllCursos();


    /**
     * Lista todos los curso visibles (estado = true)
     * @return Objeto **ResponseDto** con los siguientes resultados: 
     *         - Código exitoso y listado de cursos visibles si se encuentran datos.
     *         - Código exitoso si no se encuentran cursos.
     *         - Código fallido en caso de error.
     */
    public ResponseDto getListaCursos();


    /**
     * Obtiene un curso visible a partir de un ID proporcionado
     * @param id ID del curso a buscar
     * @return Objeto **ResponseDto** con los siguientes resultados: 
     *         - Código exitoso y el curso si se encuentra el curso solicitado
     *         - Código exitoso si no se encuentran el curso solicitado.
     *         - Código fallido en caso de error.
     */
    public ResponseDto getCurso( Long id );


    /**
     * Crea un curso a partir de un Objeto CursoDto
     * @param curso Objeto CursoDto con los datos a crear <br><br>
     * <b>(El objeto a crear no debe estar visible y registrado previamente)</b>
     * @return Objeto **ResponseDto** con los siguientes resultados: 
     *         - Código exitoso y curso creado si se registra exitósamente
     *         - Código fallido y curso repetido si se encuentra un curso ya registrado.
     *         - Código fallido en caso de error.
     */
    public ResponseDto createCurso( CursoDto curso );


    /**
     * Actualiza un curso a partir de un Objeto CursoDto visible
     * @param curso Objeto CursoDto con el ID y los datos a actualizar. 
     * <b>(No actualiza el estado)</b>
     * @return Objeto **ResponseDto** con los siguientes resultados: 
     *         - Código exitoso y curso si se actualiza exitósamente
     *         - Código fallido si no se encuentra un curso para actualizar.
     *         - Código fallido en caso de error.
     */
    public ResponseDto updateCurso( CursoDto curso );


    /**
     * Borra lógicamente un curso visible
     * @param id ID de un curso existente a borrar
     * @return Objeto **ResponseDto** con los siguientes resultados: 
     *         - Código exitoso si el curso se borra exitósamente
     *         - Código fallido si no se encuentra un curso para borrar.
     *         - Código fallido en caso de error.
     */
    public ResponseDto deleteCurso( Long id );
    
}