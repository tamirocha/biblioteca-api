package br.com.projeto.bibliotecaapi.dtos;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
@Builder
public class BibliotecaDto {

    @NotNull
    private Long codigoUsuario;
    @NotNull
    private Long codigoLivro;

}
