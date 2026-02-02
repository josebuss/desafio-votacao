package br.com.desafiovotacao.entity.dto;

import org.mapstruct.Mapper;

import br.com.desafiovotacao.entity.SessaoVotacaoEntity;

@Mapper(componentModel = "spring")
public interface SessaoVotacaoMapper {

	SessaoVotacaoEntity toEntity(SessaoVotacaoDTO dto);

	SessaoVotacaoDTO toResponse(SessaoVotacaoEntity entity);
}
