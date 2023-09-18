package br.com.projeto.bibliotecaapi.dtos;

import br.com.projeto.bibliotecaapi.models.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUsuarioResponseDto {
    @NotBlank
    private String cpf;
    @NotBlank
    private String nome;
    @NotBlank
    private String matricula;

    public static CreateUsuarioResponseDto toDto(Usuario usuario){
        CreateUsuarioResponseDto dto = new CreateUsuarioResponseDto();
        dto.setCpf(usuario.getCpf());
        dto.setNome(usuario.getNome());
        dto.setMatricula(usuario.getMatricula());
        return dto;
    }

}
