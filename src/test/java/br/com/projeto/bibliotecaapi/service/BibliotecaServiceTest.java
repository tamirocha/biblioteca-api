package br.com.projeto.bibliotecaapi.service;

import br.com.projeto.bibliotecaapi.dtos.BibliotecaDto;
import br.com.projeto.bibliotecaapi.exception.BusinessException;
import br.com.projeto.bibliotecaapi.exception.Error;
import br.com.projeto.bibliotecaapi.models.Livro;
import br.com.projeto.bibliotecaapi.models.Usuario;
import br.com.projeto.bibliotecaapi.repository.LivroRepository;
import br.com.projeto.bibliotecaapi.repository.UsuarioRepository;
import br.com.projeto.bibliotecaapi.services.BibliotecaService;
import br.com.projeto.bibliotecaapi.services.LivroService;
import br.com.projeto.bibliotecaapi.services.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BibliotecaServiceTest {
    @InjectMocks
    BibliotecaService bibliotecaService;
    @Mock
    LivroService livroService;
    @Mock
    UsuarioService usuarioService;
    @Mock
    LivroRepository livroRepository;
    @Mock
    UsuarioRepository usuarioRepository;
    ///
    Livro livro;
    Usuario usuario;

    BibliotecaDto bibliotecaDto;

    Long codigoLivro = 1l;

    Long codigoUsuario = 1l;

    List<Livro> livrosEmprestados = new ArrayList<>();


    @BeforeEach
    public void setUp() {
        livro = new Livro(1L, "Moby Dicky", "Arthur de Azevedo", 5L, 5L, 280, true, LocalDate.parse("2023-09-08"), null);
        usuario = new Usuario(1L, "11372365423", "Beatriz de Andrade", "000001", null);
        bibliotecaDto = BibliotecaDto.builder().codigoLivro(codigoLivro).codigoUsuario(codigoLivro).build();

        livrosEmprestados.add(new Livro(1L, "Moby Dicky", "Arthur de Azevedo", 5L, 5L, 280, true, LocalDate.parse("2023-09-14"), null));
        livrosEmprestados.add(new Livro(2L, "A Pequena Sereia", "Carlos Martins", 5L, 5L, 250, true, LocalDate.parse("2023-09-14"), null));
        livrosEmprestados.add(new Livro(3L, "O Rei Leão", "Maria da Silva", 5L, 5L, 305, true, LocalDate.parse("2023-09-14"), null));
        livrosEmprestados.add(new Livro(4L, "O Gato de Botas", "Antonio Gonzaga", 5L, 5L, 230, true, LocalDate.parse("2023-09-14"), null));
        livrosEmprestados.add(new Livro(5L, "A Bela e a Fera", "Andressa Ribeiro", 5L, 5L, 210, true, LocalDate.parse("2023-09-14"), null));
    }

    @Test
    public void shouldRealizarEmprestimoSemDisponibilidade() {

        livro.setDisponibilidade(false);
        when(livroService.buscarPorCodigo(codigoLivro)).thenReturn(livro);

        assertThrows(BusinessException.class, () -> bibliotecaService.realizarEmprestimo(bibliotecaDto));

        verify(livroService).buscarPorCodigo(livro.getCodigo());
        verifyNoMoreInteractions(livroService);
    }

    @Test
    public void shouldRealizarEmprestimoComLimiteLivros() {

        when(livroService.buscarPorCodigo(codigoLivro)).thenReturn(livro);

        usuario.setLivrosEmprestados(livrosEmprestados);
        when(usuarioService.buscarPorCodigo(codigoUsuario)).thenReturn(usuario);

        assertThrows(BusinessException.class, () -> bibliotecaService.realizarEmprestimo(bibliotecaDto));

        verify(livroService).buscarPorCodigo(livro.getCodigo());
        verifyNoMoreInteractions(livroService);
    }

    @Test
    public void shouldRealizarEmprestimoJaPossuiExemplar() {

        when(livroService.buscarPorCodigo(codigoLivro)).thenReturn(livro);

        livrosEmprestados.remove(2);
        usuario.setLivrosEmprestados(livrosEmprestados);
        when(usuarioService.buscarPorCodigo(codigoUsuario)).thenReturn(usuario);

        assertThrows(BusinessException.class, () -> bibliotecaService.realizarEmprestimo(bibliotecaDto));

        verify(livroService).buscarPorCodigo(livro.getCodigo());
        verifyNoMoreInteractions(livroService);
    }

    @Test
    public void shouldRealizarEmprestimoEntregaAtrasada() {
        // O LIVRO
        // QUE ESTÁ SENDO EMPRESTADO
        // TEM QUE ESTÁ DISPONÍVEL
        // TEM QUE TER PELO MENOS UMA UNIDADE

//        Long codigoLivro = 1l;
//        Livro livroDB = new Livro(codigoLivro, "Moby Dicky", "Arthur de Azevedo", 5L, 5L, 280, true, LocalDate.parse("2023-09-08"), null);
//        when(livroService.buscarPorCodigo(codigoLivro)).thenReturn(livroDB);
//
//
//
//        // O USUARIO
//        // PODE PEGAR EMPRESTADO NO MAXIMO 5 LIVROS
//        // NÃO PODE PEGAR DOIS LIVROS COM O MSM CODIGO
//        // DENTRE OS LIVROS QUE ELE PEGOU EMPRESTADO DEVE TER PELO MENOS UM LIVRO COM A DATA DE ENTREGA MENOR QUE DATA DE HOJE
//
//        List<Livro> livrosUsuario = new ArrayList<>();
//        livrosUsuario.add(new Livro(2L, "A Pequena Sereia", "Carlos Martins", 5L, 5L, 250, true, LocalDate.parse("2023-09-08"), null));
//        livrosUsuario.add(new Livro(3L, "O Rei Leão", "Maria da Silva", 5L, 5L, 305, true, LocalDate.parse("2023-09-08"), null));
//        livrosUsuario.add(new Livro(4L, "O Gato de Botas", "Antonio Gonzaga", 5L, 5L, 230, true, LocalDate.parse("2023-09-08"), null));
//        livrosUsuario.add(new Livro(5L, "A Bela e a Fera", "Andressa Ribeiro", 5L, 5L, 210, true, LocalDate.parse("2023-09-08"), null));
//
//        usuario = new Usuario(1L, "11372365423", "Beatriz de Andrade", "000001", null);
//
//        usuario.setLivrosEmprestados(livrosUsuario);
//
//        when(usuarioService.buscarPorCodigo(codigoUsuario)).thenReturn(usuario);
//
//        assertThrows(InternalErrorException.class, () -> bibliotecaService.realizarEmprestimo(bibliotecaDto));
//
//        verify(livroService).buscarPorCodigo(livro.getCodigo());
//        verifyNoMoreInteractions(livroService);
//
        when(livroService.buscarPorCodigo(codigoLivro)).thenReturn(livro);


        livrosEmprestados.get(3).setDataEntrega(LocalDate.now().minusDays(1));
        livrosEmprestados.remove(0);

        usuario.setLivrosEmprestados(livrosEmprestados);
        when(usuarioService.buscarPorCodigo(codigoUsuario)).thenReturn(usuario);

        assertThrows(BusinessException.class, () -> bibliotecaService.realizarEmprestimo(bibliotecaDto));

        verify(livroService).buscarPorCodigo(livro.getCodigo());
        verifyNoMoreInteractions(livroService);
    }

    @Test
    public void shouldRealizarEmprestimoSucess() {

        Long codigoLivro = 1l;
        Long codigoUsuario = 1l;

        livro = new Livro(1L, "Moby Dicky", "Arthur de Azevedo", 5L, 5L, 280, true, LocalDate.parse("2023-09-08"), null);

        usuario = new Usuario(1L, "11372365423", "Beatriz de Andrade", "000001", null);

        List<Livro> livrosEmprestados = new ArrayList<>();
        livrosEmprestados.add(new Livro(1L, "Moby Dicky", "Arthur de Azevedo", 5L, 5L, 280, true, LocalDate.parse("2023-09-14"), null));
        livrosEmprestados.add(new Livro(2L, "A Pequena Sereia", "Carlos Martins", 5L, 5L, 250, true, LocalDate.parse("2023-09-14"), null));
        livrosEmprestados.add(new Livro(3L, "O Rei Leão", "Maria da Silva", 5L, 5L, 305, true, LocalDate.parse("2023-09-14"), null));
        livrosEmprestados.add(new Livro(4L, "O Gato de Botas", "Antonio Gonzaga", 5L, 5L, 230, true, LocalDate.parse("2023-09-14"), null));
        livrosEmprestados.add(new Livro(5L, "A Bela e a Fera", "Andressa Ribeiro", 5L, 5L, 210, true, LocalDate.parse("2023-09-14"), null));

        when(livroService.buscarPorCodigo(codigoLivro)).thenReturn(livro);

        when(usuarioService.buscarPorCodigo(codigoUsuario)).thenReturn(usuario);
        usuario.setLivrosEmprestados(livrosEmprestados);
        livrosEmprestados.remove(0);
        bibliotecaService.realizarEmprestimo(bibliotecaDto);

        verify(livroService).update(livro);
        verify(usuarioService).update(usuario);
        verifyNoMoreInteractions(livroService);
        verifyNoMoreInteractions(usuarioService);
    }

    @Test
    public void shouldRealizarDevolucaoSucess() {

        Long codigoLivro = 1l;
        Livro livro = new Livro(codigoLivro, "Moby Dicky", "Arthur de Azevedo", 5L, 5L, 280, true, LocalDate.parse("2023-09-08"), null);
        when(livroService.buscarPorCodigo(codigoLivro)).thenReturn(livro);
        livro.incrementarQuantidadeDisponivel();
        livroRepository.save(livro);


        usuario = new Usuario(1L, "11372365423", "Beatriz de Andrade", "000001", null);
        List<Livro> livrosUsuario = new ArrayList<>();
        livrosUsuario.add(new Livro(1L, "Moby Dicky", "Arthur de Azevedo", 5L, 5L, 280, true, LocalDate.parse("2023-09-14"), null));
        livrosUsuario.add(new Livro(2L, "A Pequena Sereia", "Carlos Martins", 5L, 5L, 250, true, LocalDate.parse("2023-09-08"), null));
        livrosUsuario.add(new Livro(3L, "O Rei Leão", "Maria da Silva", 5L, 5L, 305, true, LocalDate.parse("2023-09-08"), null));
        livrosUsuario.add(new Livro(4L, "O Gato de Botas", "Antonio Gonzaga", 5L, 5L, 230, true, LocalDate.parse("2023-09-08"), null));
        livrosUsuario.add(new Livro(5L, "A Bela e a Fera", "Andressa Ribeiro", 5L, 5L, 210, true, LocalDate.parse("2023-09-08"), null));
        usuario.setLivrosEmprestados(livrosUsuario);

        when(usuarioService.buscarPorCodigo(codigoUsuario)).thenReturn(usuario);
        bibliotecaService.realizarDevolucao(bibliotecaDto);

        verify(livroService).update(livro);
        verify(usuarioService).update(usuario);
        verifyNoMoreInteractions(livroService);
        verifyNoMoreInteractions(usuarioService);
    }
}
