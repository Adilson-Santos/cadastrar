package com.br.cadastrar.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.cadastrar.domain.Cliente;
import com.br.cadastrar.repositories.ClienteRepository;

@Service
public class DBService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public void instantiateDataBase() throws ParseException {
		

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		
		Cliente cli1 = new Cliente(null,sdf.parse("28/03/2023"), "Gabriele Nascimento", 
				"0234562", "Rua Bras", 56, "Jardim paulista", 276890556, "Indaiatuba", "São Paulo");
		cli1.getTelefones().addAll(Arrays.asList("36789088"));//Associando os telenes com o cliente
		
		Cliente cli2 = new Cliente(null,sdf.parse("18/02/2023"),"Adilson Santos", "12309355", "Praça Aguas Marinhas", 23, "São José",
				19865347,"Padre Paraiso","Minas Gerais");
		cli2.getTelefones().addAll(Arrays.asList("2378904"));
		
		Cliente cli3 = new Cliente(null,sdf.parse("14/01/2023"), "Vitor Silva", "12309388", "Silvio Candello", 88, "Jardim Morada do Sol",
				1986523,"Campinas", "São Paulo");
		cli3.getTelefones().addAll(Arrays.asList("637890470"));
		
		
		clienteRepository.saveAll(Arrays.asList(cli1,cli2,cli3));
		
	}
	

}
