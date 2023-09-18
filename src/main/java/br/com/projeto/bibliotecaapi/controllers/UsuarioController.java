package br.com.projeto.bibliotecaapi.controllers;

import br.com.projeto.bibliotecaapi.dtos.CreateUsuarioRequestDto;
import br.com.projeto.bibliotecaapi.dtos.CreateUsuarioResponseDto;
import br.com.projeto.bibliotecaapi.models.Usuario;
import br.com.projeto.bibliotecaapi.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<CreateUsuarioResponseDto> cadastrarUsuario(@RequestBody @Valid CreateUsuarioRequestDto usuarioDto) {
        Usuario usuario = usuarioService.cadastrarUsuario(usuarioDto.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(CreateUsuarioResponseDto.toDto(usuario));
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Usuario> buscarPorCpf(@PathVariable(value = "cpf") String cpf) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.buscarPorCpf(cpf));
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<Usuario> buscarPorCodigo(@PathVariable(value = "codigo") Long codigo){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.buscarPorCodigo(codigo));
    }
}

