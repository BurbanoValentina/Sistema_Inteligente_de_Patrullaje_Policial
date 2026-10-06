package com.patrullaje.mapper;

import com.patrullaje.dto.CiudadanoDTO;
import com.patrullaje.model.Ciudadano;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-10-06T11:26:56-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.46.100.v20260826-1225, environment: Java 21.0.12.1 (Eclipse Adoptium)"
)
@Component
public class CiudadanoMapperImpl implements CiudadanoMapper {

    @Override
    public CiudadanoDTO toDto(Ciudadano entity) {
        if ( entity == null ) {
            return null;
        }

        CiudadanoDTO ciudadanoDTO = new CiudadanoDTO();

        ciudadanoDTO.setId( entity.getId() );
        ciudadanoDTO.setNombre( entity.getNombre() );
        ciudadanoDTO.setDocumento( entity.getDocumento() );
        ciudadanoDTO.setTelefono( entity.getTelefono() );
        ciudadanoDTO.setCorreo( entity.getCorreo() );

        return ciudadanoDTO;
    }

    @Override
    public Ciudadano toEntity(CiudadanoDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Ciudadano ciudadano = new Ciudadano();

        ciudadano.setNombre( dto.getNombre() );
        ciudadano.setDocumento( dto.getDocumento() );
        ciudadano.setTelefono( dto.getTelefono() );
        ciudadano.setCorreo( dto.getCorreo() );

        return ciudadano;
    }
}
