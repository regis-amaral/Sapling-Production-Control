package dev.regis.rest.configurations;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.regis.rest.models.mappers.MapBatch;
import dev.regis.rest.models.mappers.MapGeneticMaterial;

@Configuration
public class MapStructConfig {
    
    @Bean
    public MapBatch mapBatch() {
        return Mappers.getMapper(MapBatch.class);
    }

    @Bean
    public MapGeneticMaterial mapGeneticMaterial() {
        return Mappers.getMapper(MapGeneticMaterial.class);
    }
}
