package br.com.projeto.bibliotecaapi.dtos;

import br.com.projeto.bibliotecaapi.models.Usuario;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder

public class CreateUsuarioRequestDto {
    @NotBlank
    private String cpf;
    @NotBlank
    private String nome;
    @NotBlank
    private String matricula;

    public Usuario toEntity(){
        Usuario usuario = new Usuario();
        usuario.setCpf(this.cpf);
        usuario.setNome(this.nome);
        usuario.setMatricula(this.matricula);

        return usuario;
    }
}
