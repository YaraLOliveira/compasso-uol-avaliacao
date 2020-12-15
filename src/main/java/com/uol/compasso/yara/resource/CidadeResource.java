package com.uol.compasso.yara.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.uol.compasso.yara.enuns.EnumEstado;
import com.uol.compasso.yara.interfaces.IResource;
import com.uol.compasso.yara.model.Cidade;
import com.uol.compasso.yara.service.CidadeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

@Log4j2
@RestController
@Api(value="Operações para manipulação dos dados de cidade", tags = "cidade, city")
@RequestMapping(value = "/api/cidades", path = "/api/cidades")
public class CidadeResource extends DefaultResource implements IResource<Cidade, Integer> {

    @Autowired
    private CidadeService cidadeService;

    @Override
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value="${resource.cidade-post}", notes="Criar dados da cidade.")
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "Registro criado com sucesso.", response = Cidade.class),
            @ApiResponse(code = 301, message = "Redirecionamento permanente.", response = Cidade.class),
            @ApiResponse(code = 401, message = "Não autorizado.", response = Cidade.class),
            @ApiResponse(code = 404, message = "Registro não encontrado.", response = Cidade.class),
            @ApiResponse(code = 500, message = "Erro na requisão, verifique configurações do servidor.", response = Cidade.class)
    })
    public ResponseEntity<?> create(@Valid @RequestBody Cidade entity) {
        log.debug("Criando objeto cidade. {}", entity);
        Cidade cidade = cidadeService.create(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
    }

   @Override
   @GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
   @ResponseStatus(HttpStatus.OK)
   @ApiOperation(value="${controller.cidade-get-id}", notes="Exibe dados da cidade.")
   @ApiResponses(value={
           @ApiResponse(code = 200, message = "Registro atualizado com sucesso.", response = Cidade.class),
           @ApiResponse(code = 301, message = "Redirecionamento permanente.", response = Cidade.class),
           @ApiResponse(code = 401, message = "Não autorizado.", response = Cidade.class),
           @ApiResponse(code = 404, message = "Registro não encontrado.", response = Cidade.class),
           @ApiResponse(code = 500, message = "Erro na requisão, verifique configurações do servidor.", response = Cidade.class)
   })
    public ResponseEntity<?> read(@PathVariable("id") Integer id) {
       Cidade cidade =  cidadeService.read(id);
        return ResponseEntity.status(HttpStatus.OK).body(cidade);
    }

    @GetMapping( produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value="${controller.cidade-get}", notes="Exibe dados da cidade.")
    @ApiResponses(value= {
            @ApiResponse(code = 200, message = "Registro atualizado com sucesso.", response = Cidade.class),
            @ApiResponse(code = 301, message = "Redirecionamento permanente.", response = Cidade.class),
            @ApiResponse(code = 401, message = "Não autorizado.", response = Cidade.class),
            @ApiResponse(code = 404, message = "Registro não encontrado.", response = Cidade.class),
            @ApiResponse(code = 500, message = "Erro na requisão, verifique configurações do servidor.", response = Cidade.class)
    })
    public ResponseEntity<?> read(@RequestParam("nome") String nome,
                                  @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "20") Integer size ) {
        Page<Cidade> lista = cidadeService.read(nome, PageRequest.of(page, size));

        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @Override
    @PutMapping(path = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ApiOperation(value="${controller.cidade-put}", notes="Atualizar dados da cidade.")
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "Registro atualizado com sucesso.", response = Cidade.class),
            @ApiResponse(code = 301, message = "Redirecionamento permanente.", response = Cidade.class),
            @ApiResponse(code = 401, message = "Não autorizado.", response = Cidade.class),
            @ApiResponse(code = 404, message = "Registro não encontrado.", response = Cidade.class),
            @ApiResponse(code = 500, message = "Erro na requisão, verifique configurações do servidor.", response = Cidade.class)
    })
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @Valid @RequestBody Cidade entity) {
        log.trace("Alterando registro {}", entity);
        entity.setId(id);
        cidadeService.update(entity);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PatchMapping(path = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ApiOperation(value="${controller.cidade-patch}", notes="Atualizar dados da cidade.")
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "Registro atualizado com sucesso.", response = Cidade.class),
            @ApiResponse(code = 301, message = "Redirecionamento permanente.", response = Cidade.class),
            @ApiResponse(code = 401, message = "Não autorizado.", response = Cidade.class),
            @ApiResponse(code = 404, message = "Registro não encontrado.", response = Cidade.class),
            @ApiResponse(code = 500, message = "Erro na requisão, verifique configurações do servidor.", response = Cidade.class)
    })
    public ResponseEntity<?> patch(@PathVariable("id") Integer id, @Valid @RequestBody Cidade entity) {
        Cidade cidade = cidadeService.read(id);
        if(cidade!=null){
            if(entity.getNome()!=null){
                if(!cidade.getNome().equalsIgnoreCase(entity.getNome())){
                    cidade.setNome(entity.getNome());
                }
            }
            if(entity.getEstado()!=null){
                if (!cidade.getEstado().equals(entity.getEstado())){
                    cidade.setEstado(entity.getEstado());
                }
            }
        }
        cidadeService.update(cidade);
        return ResponseEntity.noContent().build();
    }

    @Override
    @DeleteMapping(path = "/{id}")
    @ApiOperation(value="${controller.cidade-delete}", notes="Exlcuir dados da cidade.")
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "Registro atualizado com sucesso.", response = Cidade.class),
            @ApiResponse(code = 301, message = "Redirecionamento permanente.", response = Cidade.class),
            @ApiResponse(code = 401, message = "Não autorizado.", response = Cidade.class),
            @ApiResponse(code = 404, message = "Registro não encontrado.", response = Cidade.class),
            @ApiResponse(code = 500, message = "Erro na requisão, verifique configurações do servidor.", response = Cidade.class)
    })
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        cidadeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @RequestMapping(method={RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value="${controller.cidade-options}", notes="Método responsável para apresentar as operações que a cidade pode fazer da API utilizada.")
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
