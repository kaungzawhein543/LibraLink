package com.elibrary.LibraLink.configurations;


import io.github.cdimascio.dotenv.Dotenv;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Dotenv dotenv() {
        return Dotenv.configure().load();  // Loads the .env file
    }
}
