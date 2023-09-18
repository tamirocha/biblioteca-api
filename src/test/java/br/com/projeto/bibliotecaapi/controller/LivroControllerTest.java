package br.com.projeto.bibliotecaapi.controller;

import br.com.projeto.bibliotecaapi.controllers.LivroController;
import br.com.projeto.bibliotecaapi.dtos.CreateLivroRequestDto;
import br.com.projeto.bibliotecaapi.models.Livro;
import br.com.projeto.bibliotecaapi.services.LivroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LivroController.class)
public class LivroControllerTest {
    @MockBean
    LivroService livroService;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateLivro() throws Exception {
        CreateLivroRequestDto livroRequestDto = CreateLivroRequestDto
                .builder()
                .titulo("Moby Dicky")
                .autor("Arthur de Azevedo")
                .quantidadeDisponivel(5L)
                .quantidadeTotal(5L)
                .disponibilidade(true)
                .build();

        Livro livro = new Livro(1L, "Moby Dicky", "Arthur de Azevedo", 5L, 5L, 280, true, LocalDate.parse("2023-09-08"), null);

        when(livroService.cadastrar(livroRequestDto.toEntity())).thenReturn(livro);

        String livroDto = objectMapper.writeValueAsString(livroRequestDto);

        this.mockMvc
                .perform(
                        post("/livro")
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .content(livroDto))

                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.titulo", is("Moby Dicky")))
                .andExpect(jsonPath("$.autor", is("Arthur de Azevedo")))
                .andExpect(jsonPath("$.quantidadeTotal", is(5)))
                .andExpect(jsonPath("$.quantidadeDisponivel", is(5)))
                .andExpect(jsonPath("$.numeroPaginas", is(280)))
                .andExpect(jsonPath("$.disponibilidade", is(true)));
    }

    @Test
    void shouldFindLivro() throws Exception {
        Long codigoLivro = 1l;
        Livro livro = new Livro(1L, "Moby Dicky", "Arthur de Azevedo", 5L, 5L, 280, true, LocalDate.parse("2023-09-08"), null);

        when(livroService.buscarPorCodigo(codigoLivro)).thenReturn(livro);

        this.mockMvc
                .perform(
                        get("/livro/{codigo}", codigoLivro))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo", is("Moby Dicky")))
                .andExpect(jsonPath("$.autor", is("Arthur de Azevedo")))
                .andExpect(jsonPath("$.quantidadeTotal", is(5)))
                .andExpect(jsonPath("$.quantidadeDisponivel", is(5)))
                .andExpect(jsonPath("$.numeroPaginas", is(280)))
                .andExpect(jsonPath("$.disponibilidade", is(true)));
    }
}
