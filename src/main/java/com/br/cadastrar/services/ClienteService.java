package com.br.cadastrar.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.br.cadastrar.domain.Cliente;
import com.br.cadastrar.dto.ClienteDTO;
import com.br.cadastrar.dto.ClienteNewDTO;
import com.br.cadastrar.repositories.ClienteRepository;
import com.br.cadastrar.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;// Injetando dependecia

	// Buscando objeto no banco por Id
	public Cliente find(Integer id) {// Método para fazer tratamento personalizado de erro
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ",Tipo:" + Cliente.class.getName()));
	}

	@Transactional
	public Cliente insert(Cliente obj) {// Inserindo um cliente
		obj.setId(null);// Testando o id se realmente é null
		//obj.setDataCadastro(new Date());
		return repo.save(obj);
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());// Verificando se o id existe. Atualizando de acordo o banco
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		repo.deleteById(id);
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	// Metodo para fazer paginação de Clientes
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	// Instanciando um cliente dto
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), null, objDto.getNome(), null, objDto.getEndereco(), objDto.getNumero(),
				objDto.getBairro(), objDto.getCep(), objDto.getCidade(), objDto.getEstado());
	}

	// Instanciando um clienteNewdto
	public Cliente fromDTO(ClienteNewDTO objDto) {
	 Cliente cli = new Cliente( null,objDto.getDataCadastro(), objDto.getNome(), objDto.getCpf(), objDto.getEndereco(), objDto.getNumero(),
				objDto.getBairro(), objDto.getCep(), objDto.getCidade(), objDto.getEstado());
	 cli.getTelefones().add(objDto.getTelefone1());
	 if (objDto.getTelefone2()!=null) {
		cli.getTelefones().add(objDto.getTelefone2());
	}
	 return cli;
	}

	// Metodo auxiliar para atualizar dados, trazer dados que foram atualizado do
	// banco
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEndereco(obj.getEndereco());
		newObj.setNumero(obj.getNumero());
		newObj.setBairro(obj.getBairro());
		newObj.setCep(obj.getCep());
		newObj.setCidade(obj.getCidade());
		newObj.setEstado(obj.getEstado());
	}

}
