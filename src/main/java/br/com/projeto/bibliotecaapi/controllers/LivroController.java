package br.com.projeto.bibliotecaapi.controllers;

import br.com.projeto.bibliotecaapi.dtos.CreateLivroRequestDto;
import br.com.projeto.bibliotecaapi.dtos.CreateLivroResponseDto;
import br.com.projeto.bibliotecaapi.dtos.CreateUsuarioResponseDto;
import br.com.projeto.bibliotecaapi.models.Livro;
import br.com.projeto.bibliotecaapi.services.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/livro")
public class LivroController {
    @Autowired
    private LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping
    public ResponseEntity<CreateLivroResponseDto> cadastrarLivro(@RequestBody @Valid CreateLivroRequestDto livroDto){
        Livro livro = livroService.cadastrar(livroDto.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(CreateLivroResponseDto.toDto(livro));
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Livro> getOneLivro(@PathVariable(value = "codigo") Long codigo) {
        return ResponseEntity.status(HttpStatus.OK).body(livroService.buscarPorCodigo(codigo));
    }
}
