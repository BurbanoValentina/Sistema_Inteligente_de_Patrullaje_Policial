package com.patrullaje.mapper;

/**
 * * * @author Valentina
 */
import com.patrullaje.dto.SolicitudAsistenciaDTO;
import com.patrullaje.model.SolicitudAsistencia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SolicitudAsistenciaMapper {

    @Mapping(target = "ciudadanoId", source = "ciudadano.id")
    @Mapping(target = "caiId", source = "cai.id")
    @Mapping(target = "personalId", source = "personalAsignado.id")
    SolicitudAsistenciaDTO toDto(SolicitudAsistencia entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "ciudadano", ignore = true)
    @Mapping(target = "cai", ignore = true)
    @Mapping(target = "personalAsignado", ignore = true)
    @Mapping(target = "seguimientos", ignore = true)
    SolicitudAsistencia toEntity(SolicitudAsistenciaDTO dto);
}
