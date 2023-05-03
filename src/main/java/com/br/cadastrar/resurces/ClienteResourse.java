package com.br.cadastrar.resurces;


import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.cadastrar.domain.Cliente;
import com.br.cadastrar.dto.ClienteDTO;
import com.br.cadastrar.dto.ClienteNewDTO;
import com.br.cadastrar.services.ClienteService;

	@RestController
	@RequestMapping(value = "/clientes") // edipoint rest para fazer chamado no brause.
	public class ClienteResourse{

		@Autowired
		private ClienteService service;

		@RequestMapping(value = "/{id}", method = RequestMethod.GET)//edipoint para fazer chamada por (ID)no postmam
		public ResponseEntity<Cliente> find(@PathVariable Integer id) {//Método para retornar codigo do cliente
			Cliente obj = service.find(id);
			return ResponseEntity.ok().body(obj);

		}
		
		@ RequestMapping(method = RequestMethod.POST)
		public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDto){//Inserindo cliente com post
			Cliente obj = service.fromDTO(objDto);
			obj = service.insert(obj);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
			return ResponseEntity.created(uri).build();
		}
		
		@RequestMapping(value = "/{id}", method = RequestMethod.PUT)//Atualizando
		public ResponseEntity<Cliente> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id) {
			Cliente obj = service.fromDTO(objDto);
			obj.setId(id);//verificando o id
			obj = service.update(obj);
			return ResponseEntity.noContent().build();
			
		}
		
		@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)//Deletando Cliente
		public ResponseEntity<Void> delete(@PathVariable Integer id){
			service.delete(id);
			return ResponseEntity.noContent().build();
		}
		//edipoint para listar clientes
		@RequestMapping( method = RequestMethod.GET)
		public ResponseEntity<List<ClienteDTO>> findAll() {
			List<Cliente> list = service.findAll();
			List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
			return ResponseEntity.ok().body(listDto);

		}
		//Metodo Para fazer paginação de clientes
				@RequestMapping(value="/page", method = RequestMethod.GET)//edipoint para fazer chamada de paginação ordenada de fornecedo fornecedores postmam
				public ResponseEntity<Page<ClienteDTO>> findPage(
						@RequestParam(value = "page", defaultValue = "0") Integer page,
						@RequestParam(value = "linesPerPage", defaultValue = "24")	Integer linesPerPage,
						@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
						@RequestParam(value = "direction", defaultValue = "ASC")String direction){
				
					Page<Cliente> list = service.findPage(page, linesPerPage, orderBy, direction);
					Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj));
					return ResponseEntity.ok().body(listDto);
				}
	}

