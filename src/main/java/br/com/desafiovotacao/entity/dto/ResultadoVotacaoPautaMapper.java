package br.com.desafiovotacao.entity.dto;

import org.mapstruct.Mapper;

import br.com.desafiovotacao.entity.ResultadoVotacaoPautaEntity;

@Mapper(componentModel = "spring")
public interface ResultadoVotacaoPautaMapper {

	ResultadoVotacaoPautaEntity toEntity(ResultadoVotacaoPautaDTO dto);

	ResultadoVotacaoPautaDTO toResponse(ResultadoVotacaoPautaEntity entity);
}
