package br.com.projeto.bibliotecaapi.repository;

import br.com.projeto.bibliotecaapi.models.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    Livro findByCodigo(Long codigo);

}
