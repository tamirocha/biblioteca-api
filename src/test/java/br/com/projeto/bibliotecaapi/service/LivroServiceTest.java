package br.com.projeto.bibliotecaapi.service;

import br.com.projeto.bibliotecaapi.models.Livro;
import br.com.projeto.bibliotecaapi.repository.LivroRepository;
import br.com.projeto.bibliotecaapi.services.LivroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LivroServiceTest {
    @InjectMocks
    LivroService livroService;
    @Mock
    LivroRepository livroRepository;

    Livro livro;


    @BeforeEach
    public void setUp(){
        livro = new Livro(1L, "Moby Dicky", "Arthur de Azevedo", 5L, 5L, 280, true, LocalDate.parse("2023-09-08"), null);
    }

    @Test
    public void shouldCadastrarLivroSucess(){
        when(livroRepository.save(livro)).thenReturn(livro);

        Livro livroSaved = livroService.cadastrar(livro);
        assertEquals(livro, livroSaved);

        verify(livroRepository).save(livro);
        verifyNoMoreInteractions(livroRepository);

    }
    @Test
    public void shouldBuscarPorCodigoSucess(){
        when(livroRepository.findByCodigo(livro.getCodigo())).thenReturn(livro);

        Livro livroFound = livroService.buscarPorCodigo(livro.getCodigo());
        assertEquals(livro, livroFound);

        verify(livroRepository).findByCodigo(livro.getCodigo());
        verifyNoMoreInteractions(livroRepository);
    }
    @Test
    public void shouldUpdateSucess(){
        when(livroRepository.save(livro)).thenReturn(livro);

        Livro livroUpdate = livroService.update(livro);
        assertEquals(livro, livroUpdate);

        verify(livroRepository).save(livro);
        verifyNoMoreInteractions(livroRepository);
    }
}
