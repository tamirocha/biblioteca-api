package br.com.projeto.bibliotecaapi.repository;

import br.com.projeto.bibliotecaapi.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByCpf(String cpf);

    Usuario findByCodigo(Long codigo);

}
