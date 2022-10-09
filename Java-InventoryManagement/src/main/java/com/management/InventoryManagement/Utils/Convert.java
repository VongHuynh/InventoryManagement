package com.management.InventoryManagement.Utils;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Convert {
    @Autowired
    ModelMapper modelMapper;

    public <E, eDTO> eDTO toDto(E entity, Class<eDTO> DTOClass) {
        eDTO DTO = modelMapper.map(entity, DTOClass);
        return DTO;
    }

    public <E, eDTO> E toEntity(eDTO DTO, Class<E> EClass) {
        E entity = modelMapper.map(DTO, EClass);
        return entity;
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STANDARD);
        return modelMapper;
    }

}
