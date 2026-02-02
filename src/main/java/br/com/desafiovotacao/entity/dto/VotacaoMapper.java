package br.com.desafiovotacao.entity.dto;

import org.mapstruct.Mapper;

import br.com.desafiovotacao.entity.VotacaoEntity;

@Mapper(componentModel = "spring")
public interface VotacaoMapper {

    VotacaoEntity toEntity(VotacaoDTO dto);

    VotacaoDTO toResponse(VotacaoEntity entity);
    
}
