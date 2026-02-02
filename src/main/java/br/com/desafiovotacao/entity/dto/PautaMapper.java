package br.com.desafiovotacao.entity.dto;

import org.mapstruct.Mapper;

import br.com.desafiovotacao.entity.PautaEntity;

@Mapper(componentModel = "spring")
public interface PautaMapper {

    PautaEntity toEntity(PautaDTO dto);

    PautaDTO toResponse(PautaEntity entity);
    
}
