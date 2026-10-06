package com.patrullaje.mapper;

import com.patrullaje.dto.CaiDTO;
import com.patrullaje.model.CAI;
import com.patrullaje.model.Comuna;
import com.patrullaje.model.ServicioPolicial;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-10-05T19:16:04-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.46.100.v20260826-1225, environment: Java 21.0.12.1 (Eclipse Adoptium)"
)
@Component
public class CaiMapperImpl implements CaiMapper {

    @Override
    public CaiDTO toDto(CAI entity) {
        if ( entity == null ) {
            return null;
        }

        CaiDTO caiDTO = new CaiDTO();

        caiDTO.setComunaId( entityComunaId( entity ) );
        caiDTO.setServicioIds( servicioPolicialSetToLongSet( entity.getServicios() ) );
        caiDTO.setId( entity.getId() );
        caiDTO.setNombre( entity.getNombre() );
        caiDTO.setDireccion( entity.getDireccion() );
        caiDTO.setTelefono( entity.getTelefono() );
        caiDTO.setDisponible( entity.isDisponible() );

        return caiDTO;
    }

    @Override
    public CAI toEntity(CaiDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CAI cAI = new CAI();

        cAI.setNombre( dto.getNombre() );
        cAI.setDireccion( dto.getDireccion() );
        cAI.setTelefono( dto.getTelefono() );
        cAI.setDisponible( dto.isDisponible() );

        return cAI;
    }

    private Long entityComunaId(CAI cAI) {
        if ( cAI == null ) {
            return null;
        }
        Comuna comuna = cAI.getComuna();
        if ( comuna == null ) {
            return null;
        }
        Long id = comuna.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Set<Long> servicioPolicialSetToLongSet(Set<ServicioPolicial> set) {
        if ( set == null ) {
            return null;
        }

        Set<Long> set1 = new LinkedHashSet<Long>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ServicioPolicial servicioPolicial : set ) {
            set1.add( mapServicioId( servicioPolicial ) );
        }

        return set1;
    }
}
