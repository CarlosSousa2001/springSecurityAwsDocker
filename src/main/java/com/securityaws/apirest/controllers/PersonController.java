package com.securityaws.apirest.controllers;

import com.securityaws.apirest.data.vo.v1.PersonVO;
import com.securityaws.apirest.data.vo.v2.PersonVOV2;
import com.securityaws.apirest.exception.ErrorResponse;
import com.securityaws.apirest.exception.ErrorServerException;
import com.securityaws.apirest.exception.PersonNotFoundException;
import com.securityaws.apirest.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "People", description = "Contém todas as operações para manipulação da entidade Person")
@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Operation(summary = "Listar todos as pessoas", description = "Enpoint que retorna uma lista", tags = {"People"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso realizado com sucesso",
                            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PersonVO.class)))),
                    @ApiResponse(responseCode = "401", description = "Recurso não autorizado",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "404", description = "Recurso não realizado ",
                            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PersonNotFoundException.class)))),
                    @ApiResponse(responseCode = "500", description = "Erro interno",
                            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ErrorServerException.class))))
            }
    )
    @GetMapping
    public ResponseEntity<List<PersonVO>> getAllPerson() {
        List<PersonVO> personList = personService.findAllPerson();
        return ResponseEntity.ok().body(personList);
    }

    @Operation(summary = "Buscar pessoa por ID", description = "Enpoint que retorna uma pesssoa", tags = {"People"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso realizado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonVO.class))),
                    @ApiResponse(responseCode = "401", description = "Recurso não autorizado",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "404", description = "Recurso não realizado ",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonNotFoundException.class))),
                    @ApiResponse(responseCode = "500", description = "Erro interno",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorServerException.class)))
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<PersonVO> getById(@PathVariable Long id) {
        PersonVO person = personService.getByidPerson(id);
        return ResponseEntity.ok().body(person);
    }
    @Operation(summary = "Criar nova pessoa", description = "Enpoint que cria uma pessoa", tags = {"People"},
            responses = {
                    @ApiResponse(responseCode = "201", description = "Recurso realizado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonVO.class))),
                    @ApiResponse(responseCode = "401", description = "Recurso não autorizado",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "404", description = "Recurso não realizado ",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonNotFoundException.class))),
                    @ApiResponse(responseCode = "500", description = "Erro interno",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorServerException.class)))
            }
    )
    @PostMapping
    public ResponseEntity<PersonVO> create(@RequestBody PersonVO person) {
        personService.createPerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(person);
    }

    @PostMapping("/v2")
    public ResponseEntity<PersonVOV2> createV2(@RequestBody PersonVOV2 person) {
        personService.createPersonV2(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(person);
    }
    @Operation(summary = "Atualizar dados de uma pessoa", description = "Enpoint que tualiza os dados de uma pesssoa", tags = {"People"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso realizado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class))),
                    @ApiResponse(responseCode = "400", description = "Senha não confere",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "401", description = "Recurso não autorizado",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "404", description = "Recurso não realizado ",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonNotFoundException.class))),
                    @ApiResponse(responseCode = "500", description = "Erro interno",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorServerException.class)))
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<PersonVO> update(@PathVariable Long id, @RequestBody PersonVO person) {
        PersonVO updatePerson = personService.updatePerson(person);
        return ResponseEntity.ok().body(updatePerson);
    }
    @Operation(summary = "Deletar uma pessoa", description = "Enpoint que deleta uma pesssoa", tags = {"People"},
            responses = {
                    @ApiResponse(responseCode = "204", description = "Recurso realizado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class))),
                    @ApiResponse(responseCode = "400", description = "id não existe",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "401", description = "Recurso não autorizado",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "404", description = "Recurso não realizado ",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Erro interno",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorServerException.class)))
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        personService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
