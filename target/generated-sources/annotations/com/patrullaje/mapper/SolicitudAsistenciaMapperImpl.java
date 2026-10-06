package com.patrullaje.mapper;

import com.patrullaje.dto.SolicitudAsistenciaDTO;
import com.patrullaje.model.CAI;
import com.patrullaje.model.Ciudadano;
import com.patrullaje.model.PersonalPolicial;
import com.patrullaje.model.SolicitudAsistencia;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-10-06T11:26:56-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.46.100.v20260826-1225, environment: Java 21.0.12.1 (Eclipse Adoptium)"
)
@Component
public class SolicitudAsistenciaMapperImpl implements SolicitudAsistenciaMapper {

    @Override
    public SolicitudAsistenciaDTO toDto(SolicitudAsistencia entity) {
        if ( entity == null ) {
            return null;
        }

        SolicitudAsistenciaDTO solicitudAsistenciaDTO = new SolicitudAsistenciaDTO();

        solicitudAsistenciaDTO.setCiudadanoId( entityCiudadanoId( entity ) );
        solicitudAsistenciaDTO.setCaiId( entityCaiId( entity ) );
        solicitudAsistenciaDTO.setPersonalId( entityPersonalAsignadoId( entity ) );
        solicitudAsistenciaDTO.setId( entity.getId() );
        solicitudAsistenciaDTO.setFechaCreacion( entity.getFechaCreacion() );
        solicitudAsistenciaDTO.setDescripcion( entity.getDescripcion() );
        solicitudAsistenciaDTO.setEstado( entity.getEstado() );
        solicitudAsistenciaDTO.setPrioridad( entity.getPrioridad() );

        return solicitudAsistenciaDTO;
    }

    @Override
    public SolicitudAsistencia toEntity(SolicitudAsistenciaDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SolicitudAsistencia solicitudAsistencia = new SolicitudAsistencia();

        solicitudAsistencia.setDescripcion( dto.getDescripcion() );
        solicitudAsistencia.setPrioridad( dto.getPrioridad() );

        return solicitudAsistencia;
    }

    private UUID entityCiudadanoId(SolicitudAsistencia solicitudAsistencia) {
        if ( solicitudAsistencia == null ) {
            return null;
        }
        Ciudadano ciudadano = solicitudAsistencia.getCiudadano();
        if ( ciudadano == null ) {
            return null;
        }
        UUID id = ciudadano.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long entityCaiId(SolicitudAsistencia solicitudAsistencia) {
        if ( solicitudAsistencia == null ) {
            return null;
        }
        CAI cai = solicitudAsistencia.getCai();
        if ( cai == null ) {
            return null;
        }
        Long id = cai.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long entityPersonalAsignadoId(SolicitudAsistencia solicitudAsistencia) {
        if ( solicitudAsistencia == null ) {
            return null;
        }
        PersonalPolicial personalAsignado = solicitudAsistencia.getPersonalAsignado();
        if ( personalAsignado == null ) {
            return null;
        }
        Long id = personalAsignado.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
