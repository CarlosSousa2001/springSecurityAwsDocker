package com.securityaws.apirest.controllers;

import com.securityaws.apirest.data.vo.v1.BooksVO;
import com.securityaws.apirest.exception.ErrorResponse;
import com.securityaws.apirest.exception.ErrorServerException;
import com.securityaws.apirest.exception.PersonNotFoundException;
import com.securityaws.apirest.service.BooksService;
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

@Tag(name = "Books", description = "Contém todas as operações para manipulação da entidade Books")
@RestController
@RequestMapping("/api/books/v1")
public class BooksController {

    @Autowired
    private BooksService booksService;

    @Operation(summary = "Listar todos os books", description = "Enpoint que retorna uma lista", tags = {"Books"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso realizado com sucesso",
                            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = BooksVO.class)))),
                    @ApiResponse(responseCode = "401", description = "Recurso não autorizado",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "404", description = "Recurso não realizado ",
                            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ErrorResponse.class)))),
                    @ApiResponse(responseCode = "500", description = "Erro interno",
                            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ErrorServerException.class))))
            }
    )
    @GetMapping
    public ResponseEntity<List<BooksVO>> getAllBooks() {
        List<BooksVO> booksList = booksService.findAllBooks();
        return ResponseEntity.ok().body(booksList);
    }

    @Operation(summary = "Buscar Book por ID", description = "Enpoint que retorna um Book", tags = {"Books"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso realizado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = BooksVO.class))),
                    @ApiResponse(responseCode = "401", description = "Recurso não autorizado",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "404", description = "Recurso não realizado ",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Erro interno",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorServerException.class)))
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<BooksVO> getById(@PathVariable Long id) {
        BooksVO books = booksService.getByidBooks(id);
        return ResponseEntity.ok().body(books);
    }
    @Operation(summary = "Criar um novo Book", description = "Enpoint que cria um Book", tags = {"Books"},
            responses = {
                    @ApiResponse(responseCode = "201", description = "Recurso realizado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = BooksVO.class))),
                    @ApiResponse(responseCode = "401", description = "Recurso não autorizado",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "404", description = "Recurso não realizado ",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Erro interno",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorServerException.class)))
            }
    )
    @PostMapping
    public ResponseEntity<BooksVO> create(@RequestBody BooksVO books) {
        booksService.createBooks(books);
        return ResponseEntity.status(HttpStatus.CREATED).body(books);
    }

    @Operation(summary = "Atualizar dados de um Books", description = "Enpoint que tualiza os dados de um Book", tags = {"Books"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso realizado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class))),
                    @ApiResponse(responseCode = "400", description = "Senha não confere",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "401", description = "Recurso não autorizado",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "404", description = "Recurso não realizado ",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Erro interno",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorServerException.class)))
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<BooksVO> update(@PathVariable Long id, @RequestBody BooksVO books) {
        BooksVO updatePerson = booksService.updateBooks(books);
        return ResponseEntity.ok().body(updatePerson);
    }
    @Operation(summary = "Deletar uma Book", description = "Enpoint que deleta um Book", tags = {"Books"},
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
        booksService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
