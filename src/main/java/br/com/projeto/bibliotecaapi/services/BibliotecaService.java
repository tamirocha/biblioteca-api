package br.com.projeto.bibliotecaapi.services;


import br.com.projeto.bibliotecaapi.dtos.BibliotecaDto;
import br.com.projeto.bibliotecaapi.models.Livro;
import br.com.projeto.bibliotecaapi.models.Usuario;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.projeto.bibliotecaapi.exception.Error;

import java.time.LocalDate;

@Service
@Slf4j
public class BibliotecaService {

    @Autowired
    private LivroService livroService;
    @Autowired
    private UsuarioService usuarioService;

    public void realizarEmprestimo(BibliotecaDto bibliotecaDto){
        Livro livro = livroService.buscarPorCodigo(bibliotecaDto.getCodigoLivro());
        if (!livro.isDisponibilidade()) {
            log.error("Livro não está disponível para empréstimo.");
            throw Error.NOT_AVAILABLE.toBusinessException();
        }
        livro.decrementarQuantidadeDisponivel();
        if(livro.getQuantidadeDisponivel() == 0){
            livro.setDisponibilidade(false);
        }
        Usuario usuario = usuarioService.buscarPorCodigo(bibliotecaDto.getCodigoUsuario());
        if (usuario.getLivrosEmprestados().size() >= 5) {
            log.error("Usuário atingiu o limite de livros emprestados.");
            throw Error.LIMIT_REACHED.toBusinessException();
        }
        for (Livro emprestado : usuario.getLivrosEmprestados()) { // Pega cada um dos livros (percorrendo) da lista de LivrosEmprestados e coloca na variável emprestado. Só quero percorrer todas as posições, sem saber qual posição o livro está.
            if (emprestado.getCodigo() == livro.getCodigo()) {
                log.error("Usuário já possui um exemplar desse livro emprestado.");
                throw Error.DUPLICATE_DATA.toBusinessException();
            }

            if (emprestado.getDataEntrega() != null && emprestado.getDataEntrega().isBefore(LocalDate.now())) {
                log.error("Usuário possui um livro com entrega atrasada.");
                throw Error.LATE_DELIVERY.toBusinessException();
            }
        }
        usuario.getLivrosEmprestados().add(livro);
        livroService.update(livro);
        usuarioService.update(usuario);
    }

    public void realizarDevolucao(BibliotecaDto bibliotecaDto){
        Livro livro = livroService.buscarPorCodigo(bibliotecaDto.getCodigoLivro());
        livro.incrementarQuantidadeDisponivel();
        Usuario usuario = usuarioService.buscarPorCodigo(bibliotecaDto.getCodigoLivro());
        if (usuario.getLivrosEmprestados().contains(livro)) {
            usuario.getLivrosEmprestados().remove(livro);
        }
        livroService.update(livro);
        usuarioService.update(usuario);
    }

}
