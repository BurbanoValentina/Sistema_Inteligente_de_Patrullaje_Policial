package com.patrullaje.mapper;

/**
 * * * @author Valentina
 */
import com.patrullaje.dto.CaiDTO;
import com.patrullaje.model.CAI;
import com.patrullaje.model.ServicioPolicial;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CaiMapper {

    @Mapping(target = "comunaId", source = "comuna.id")
    @Mapping(target = "servicioIds", source = "servicios")
    CaiDTO toDto(CAI entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "comuna", ignore = true)
    @Mapping(target = "servicios", ignore = true)
    CAI toEntity(CaiDTO dto);

    default Long mapServicioId(ServicioPolicial servicio) {
        return servicio == null ? null : servicio.getId();
    }
}
