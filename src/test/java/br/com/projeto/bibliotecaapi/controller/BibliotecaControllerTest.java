package br.com.projeto.bibliotecaapi.controller;

import br.com.projeto.bibliotecaapi.controllers.BibliotecaController;
import br.com.projeto.bibliotecaapi.dtos.BibliotecaDto;
import br.com.projeto.bibliotecaapi.services.BibliotecaService;
import br.com.projeto.bibliotecaapi.services.LivroService;
import br.com.projeto.bibliotecaapi.services.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BibliotecaController.class)
public class BibliotecaControllerTest {
    @MockBean
    BibliotecaService bibliotecaService;

    @MockBean
    private LivroService livroService;
    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldRealizarEmprestimo() throws Exception {
        BibliotecaDto bibliotecaDto = BibliotecaDto.builder().codigoLivro(1l).codigoUsuario(1l).build();

        this.mockMvc
                .perform(
                        put("/biblioteca/realizarEmprestimo")
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .content(objectMapper.writeValueAsString(bibliotecaDto)))
                .andExpect(status().isNoContent());

        verify(bibliotecaService).realizarEmprestimo(bibliotecaDto);
        verifyNoMoreInteractions(bibliotecaService);
    }
    @Test
    void shouldRealizarDevolucao() throws Exception {
        BibliotecaDto bibliotecaDto = BibliotecaDto.builder().codigoLivro(1l).codigoUsuario(1l).build();

        this.mockMvc
                .perform(
                        put("/biblioteca/realizarDevolucao")
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .content(objectMapper.writeValueAsString(bibliotecaDto)))
                .andExpect(status().isNoContent());

        verify(bibliotecaService).realizarDevolucao(bibliotecaDto);
        verifyNoMoreInteractions(bibliotecaService);
    }
}
