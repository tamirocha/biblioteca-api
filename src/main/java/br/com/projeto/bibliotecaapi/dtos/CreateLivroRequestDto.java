package br.com.projeto.bibliotecaapi.dtos;

import br.com.projeto.bibliotecaapi.models.Livro;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class CreateLivroRequestDto {
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

    public Livro toEntity(){
        Livro livro = new Livro();
        livro.setTitulo(this.titulo);
        livro.setAutor(this.autor);
        livro.setQuantidadeTotal(this.quantidadeTotal);
        livro.setQuantidadeDisponivel(this.quantidadeDisponivel);
        livro.setNumeroPaginas(this.numeroPaginas);
        livro.setDisponibilidade(this.disponibilidade);
        return livro;
    }
}
