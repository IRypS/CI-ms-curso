package com.edu.certus.mscurso.util;

import com.edu.certus.mscurso.dto.ResponseDto;

public class Util {

    /**
     * Genera un objeto ResponseDto
     * @param estado Estado de la operación
     * @param mensaje Mensaje con el resultado de la operación
     * @param data Datos resultantes de la operación
     * @return Objeto ResponseDto con los datos de los parámetros ingresados
     */
    public static ResponseDto getResponse( boolean estado, String mensaje, Object data ) {

        String codigo = estado ? Constantes.CODDE_SUCCESS : Constantes.CODE_FAILED;

        return ResponseDto.builder()
                .codigo( codigo )
                .mensaje( mensaje )
                .data( data )
                .build();
            
    }
    
}