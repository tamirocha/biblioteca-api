package br.com.projeto.bibliotecaapi.services;

import br.com.projeto.bibliotecaapi.models.Livro;
import br.com.projeto.bibliotecaapi.repository.LivroRepository;
import org.springframework.stereotype.Service;

@Service
public class LivroService {

    private LivroRepository livroRepository;


    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public Livro cadastrar(Livro livro) {

        return livroRepository.save(livro);
    }

    public Livro buscarPorCodigo (Long codigo) {

        return livroRepository.findByCodigo(codigo);
    }

    public Livro update (Livro livro){

        return livroRepository.save(livro);
    }
}
