package com.uol.compasso.yara.resource;

import com.uol.compasso.yara.interfaces.IResource;
import com.uol.compasso.yara.model.Cidade;
import com.uol.compasso.yara.model.Cliente;
import com.uol.compasso.yara.service.CidadeService;
import com.uol.compasso.yara.service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

@Log4j2
@RestController
@Api(value="Operações para manipulação dos dados de cliente", tags = "cliente, user")
@RequestMapping(value = "/api/clientes", path = "/api/clientes")
public class ClienteResource extends DefaultResource implements IResource<Cliente, Integer> {
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private CidadeService cidadeService;

    @Override
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value="${resource.cliente-post}", notes="Criar dados da cidade.")
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "Registro criado com sucesso.", response = Cliente.class),
            @ApiResponse(code = 301, message = "Redirecionamento permanente.", response = Cliente.class),
            @ApiResponse(code = 401, message = "Não autorizado.", response = Cliente.class),
            @ApiResponse(code = 404, message = "Registro não encontrado.", response = Cliente.class),
            @ApiResponse(code = 500, message = "Erro na requisão, verifique configurações do servidor.", response = Cliente.class)
    })
    public ResponseEntity<?> create(@Valid @RequestBody Cliente entity) {

        //Consultar objeto cidade para evitar erro de detach
        Cidade cidade =null;
        if(entity.getCidade() != null && entity.getCidade().getId() != 0){
            cidade = cidadeService.read(entity.getCidade().getId());
            entity.setCidade(cidade);
        }

        log.debug("Criando objeto cliente. {}", entity);
        Cliente cliente =  clienteService.create(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    @Override
    @GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value="${controller.cliente-get-id}", notes="Exibe dados do tipo: id do  cliente.")
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "Registro atualizado com sucesso.", response = Cliente.class),
            @ApiResponse(code = 301, message = "Redirecionamento permanente.", response = Cliente.class),
            @ApiResponse(code = 401, message = "Não autorizado.", response = Cliente.class),
            @ApiResponse(code = 404, message = "Registro não encontrado.", response = Cliente.class),
            @ApiResponse(code = 500, message = "Erro na requisão, verifique configurações do servidor.", response = Cliente.class)
    })
    public ResponseEntity<?> read(@PathVariable("id") Integer id) {
        Cliente cliente = clienteService.read(id);
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value="${controller.cliente-get}", notes="Exibe dados do tipo: nome do cliente.")
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "Registro atualizado com sucesso.", response = Cliente.class),
            @ApiResponse(code = 301, message = "Redirecionamento permanente.", response = Cliente.class),
            @ApiResponse(code = 401, message = "Não autorizado.", response = Cliente.class),
            @ApiResponse(code = 404, message = "Registro não encontrado.", response = Cliente.class),
            @ApiResponse(code = 500, message = "Erro na requisão, verifique configurações do servidor.", response = Cliente.class)
    })
    public ResponseEntity<?> read(@RequestParam("nome") String nome, @RequestParam(defaultValue = "0") Integer page,
                                  @RequestParam(defaultValue = "20") Integer size ) {
        Page<Cliente> lista = clienteService.read(nome,PageRequest.of(page, size));
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(lista);
    }

    @Override
    @PutMapping(path = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ApiOperation(value="${controller.cliente-put}", notes="Atualizar dados do cliente.")
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "Registro atualizado com sucesso.", response = Cliente.class),
            @ApiResponse(code = 301, message = "Redirecionamento permanente.", response = Cliente.class),
            @ApiResponse(code = 401, message = "Não autorizado.", response = Cliente.class),
            @ApiResponse(code = 404, message = "Registro não encontrado.", response = Cliente.class),
            @ApiResponse(code = 500, message = "Erro na requisão, verifique configurações do servidor.", response = Cliente.class)
    })
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Cliente entity) {
        log.trace("Alterando registro {}", entity);
        //Atuliza o registro
        entity.setId(id);
        //Fazer tratativas de retorno correto HTTP
        clienteService.update(entity);
        //Retornar a consulta com o cabeçalho correto
        return ResponseEntity.noContent().build();
    }

    @Override
    @PatchMapping(path = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ApiOperation(value="${controller.cliente-patch}", notes="Atualizar dados da cliente.")
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "Registro atualizado com sucesso.", response = Cliente.class),
            @ApiResponse(code = 301, message = "Redirecionamento permanente.", response = Cliente.class),
            @ApiResponse(code = 401, message = "Não autorizado.", response = Cliente.class),
            @ApiResponse(code = 404, message = "Registro não encontrado.", response = Cliente.class),
            @ApiResponse(code = 500, message = "Erro na requisão, verifique configurações do servidor.", response = Cliente.class)
    })
    public ResponseEntity<?> patch(@PathVariable("id") Integer id, @RequestBody Cliente entity) {
      Cliente cliente = clienteService.read(id);

        if(cliente!=null){
            if(entity.getNome()!=null){
                if(!cliente.getNome().equalsIgnoreCase(entity.getNome())){
                    cliente.setNome(entity.getNome());
                }
            }
            if (entity.getDataNascimento()!=null){
                if(!cliente.getDataNascimento().equals(entity.getDataNascimento())){
                    cliente.setDataNascimento(entity.getDataNascimento());
                }
            }
            if (entity.getIdade() != 0){
                if(!(cliente.getIdade() == entity.getIdade())){
                    cliente.setIdade(entity.getIdade());
                }
            }
            if (entity.getCidade() != null){
                if(!cliente.getCidade().equals(entity.getCidade())){
                    cliente.setCidade(entity.getCidade());
                }
            }
            if (!entity.getSexo().isEmpty() ){
                if(!(cliente.getSexo().equalsIgnoreCase(entity.getSexo()))){
                    cliente.setSexo(entity.getSexo());
                }
            }
        }
        clienteService.update(cliente);
        return  ResponseEntity.noContent().build();
    }

    @Override
    @DeleteMapping(path = "/{id}")
    @ApiOperation(value="${controller.cliente-delete}", notes="Exlcuir dados do cliente.")
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "Registro atualizado com sucesso.", response = Cliente.class),
            @ApiResponse(code = 301, message = "Redirecionamento permanente.", response = Cliente.class),
            @ApiResponse(code = 401, message = "Não autorizado.", response = Cliente.class),
            @ApiResponse(code = 404, message = "Registro não encontrado.", response = Cliente.class),
            @ApiResponse(code = 500, message = "Erro na requisão, verifique configurações do servidor.", response = Cliente.class)
    })
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        clienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @RequestMapping(method={RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value="${controller.cliente-options}", notes="Método responsável para apresentar as operações que o cliente pode fazer da API utilizada.")
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "Registro atualizado com sucesso.", response = Cidade.class),
            @ApiResponse(code = 301, message = "Redirecionamento permanente.", response = Cidade.class),
            @ApiResponse(code = 401, message = "Não autorizado.", response = Cidade.class),
            @ApiResponse(code = 404, message = "Registro não encontrado.", response = Cidade.class),
            @ApiResponse(code = 500, message = "Erro na requisão, verifique configurações do servidor.", response = Cidade.class)
    })
    public ResponseEntity<?> options() {
        return ResponseEntity.status(200)
                .allow(HttpMethod.POST, HttpMethod.GET, HttpMethod.PATCH, HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.OPTIONS)
                .body(String.format("Métodos permitidos: %s", Arrays.asList("GET", "POST","PUT","PATCH","DELETE", "OPTIONS").toString()));
    }
}
