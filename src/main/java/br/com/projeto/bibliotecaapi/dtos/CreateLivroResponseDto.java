package br.com.projeto.bibliotecaapi.dtos;

import br.com.projeto.bibliotecaapi.models.Livro;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CreateLivroResponseDto {
    @NotBlank
    private String titulo;
    @NotBlank
    private String autor;
    @NotNull
    private Long quantidadeTotal;
    @NotNull
    private Long quantidadeDisponivel;
    @NotNull
    private int numeroPaginas;
    private boolean disponibilidade;

    public static CreateLivroResponseDto toDto(Livro livro) {
        CreateLivroResponseDto dto = new CreateLivroResponseDto();
        dto.setTitulo(livro.getTitulo());
        dto.setAutor(livro.getAutor());
        dto.setQuantidadeTotal(livro.getQuantidadeTotal());
        dto.setQuantidadeDisponivel(livro.getQuantidadeDisponivel());
        dto.setNumeroPaginas(livro.getNumeroPaginas());
        dto.setDisponibilidade(livro.isDisponibilidade());
        return dto;
    }
}
