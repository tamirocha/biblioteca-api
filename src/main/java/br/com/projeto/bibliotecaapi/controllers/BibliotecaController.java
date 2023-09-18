package br.com.projeto.bibliotecaapi.controllers;

import br.com.projeto.bibliotecaapi.dtos.BibliotecaDto;
import br.com.projeto.bibliotecaapi.services.BibliotecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/biblioteca")
public class BibliotecaController {

    private BibliotecaService bibliotecaService;

    public BibliotecaController(BibliotecaService bibliotecaService) {

        this.bibliotecaService = bibliotecaService;
    }

    // Adicionando mais um livro na lista de livros do usuário
    // Como não é uma entidade do banco de dados não retorna um ResponseEntity
    @PutMapping("/realizarEmprestimo")
    public ResponseEntity realizarEmprestimo(@RequestBody @Valid BibliotecaDto bibliotecaDto) {
        bibliotecaService.realizarEmprestimo(bibliotecaDto);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/realizarDevolucao")
    public ResponseEntity realizarDevolucao(@RequestBody @Valid BibliotecaDto bibliotecaDto) {
        bibliotecaService.realizarDevolucao(bibliotecaDto);
        return ResponseEntity.noContent().build();
    }

}
