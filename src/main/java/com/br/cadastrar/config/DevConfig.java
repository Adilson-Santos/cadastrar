package com.br.cadastrar.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.br.cadastrar.services.DBService;

@Configuration
@Profile("dev")//Classe de configuração de desenvolvimento
public class DevConfig {
		
		@Autowired
		private DBService dbService;
		
		@Value("${spring.jpa.hibernate.ddl-auto}")//fazendo o teste se cria uma nova base de dados 
		private String strategy;
		
		@Bean
		public boolean instantiateDataBase() throws ParseException {
			
			if(!"create".equals(strategy)) {
				return false;
			}
			
			dbService.instantiateDataBase();
			return true;
		}
}
