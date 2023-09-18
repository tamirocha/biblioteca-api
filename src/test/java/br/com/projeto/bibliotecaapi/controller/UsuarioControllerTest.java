package br.com.projeto.bibliotecaapi.controller;

import br.com.projeto.bibliotecaapi.controllers.UsuarioController;
import br.com.projeto.bibliotecaapi.dtos.CreateUsuarioRequestDto;
import br.com.projeto.bibliotecaapi.models.Usuario;
import br.com.projeto.bibliotecaapi.services.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {
    @MockBean
    UsuarioService usuarioService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateUsuario() throws Exception{
        CreateUsuarioRequestDto usuarioRequestDto = CreateUsuarioRequestDto.builder().cpf("11372365423").nome("Beatriz de Andrade").matricula("000001").build();

        Usuario usuario = new Usuario(1L, "11372365423", "Beatriz de Andrade", "000001", null);

        when(usuarioService.cadastrarUsuario(usuarioRequestDto.toEntity())).thenReturn(usuario);

        String usuarioDto = objectMapper.writeValueAsString(usuarioRequestDto);

        this.mockMvc
                .perform(
                        post("/usuario")
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .content(usuarioDto))

                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.cpf", is("11372365423")))
                .andExpect(jsonPath("$.nome", is("Beatriz de Andrade")))
                .andExpect(jsonPath("$.matricula", is("000001")));
    }

    @Test
    void shouldFindUsuario() throws Exception{
        Long codigoUsuario = 1l;
        Usuario usuario = new Usuario(1L, "11372365423", "Beatriz de Andrade", "000001", null);

        when(usuarioService.buscarPorCodigo(codigoUsuario)).thenReturn(usuario);

        this.mockMvc
                .perform(
                        get("/usuario/codigo/{codigo}", codigoUsuario))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cpf", is("11372365423")))
                .andExpect(jsonPath("$.nome", is("Beatriz de Andrade")))
                .andExpect(jsonPath("$.matricula", is("000001")));
    }
}
