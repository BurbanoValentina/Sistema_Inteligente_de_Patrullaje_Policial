package com.patrullaje.mapper;

/**
 * * * @author Valentina
 */
import com.patrullaje.dto.CiudadanoDTO;
import com.patrullaje.model.Ciudadano;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CiudadanoMapper {

    CiudadanoDTO toDto(Ciudadano entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "solicitudes", ignore = true)
    Ciudadano toEntity(CiudadanoDTO dto);
}
