package br.com.projeto.bibliotecaapi.service;

import br.com.projeto.bibliotecaapi.models.Usuario;
import br.com.projeto.bibliotecaapi.repository.UsuarioRepository;
import br.com.projeto.bibliotecaapi.services.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {
    @InjectMocks
    UsuarioService usuarioService;
    @Mock
    UsuarioRepository usuarioRepository;

    Usuario usuario;

    @BeforeEach
    public void setUp(){
        usuario = new Usuario(1L, "11372365423", "Beatriz de Andrade", "000001", null);
    }
    @Test
    public void shouldCadastarUsuarioSucess(){
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario usuarioSaved = usuarioService.cadastrarUsuario(usuario);
        assertEquals(usuario, usuarioSaved);

        verify(usuarioRepository).save(usuario);
        verifyNoMoreInteractions(usuarioRepository);
    }
    @Test
    public void shouldBuscarPorCpfSucess(){
        when(usuarioRepository.findByCpf(usuario.getCpf())).thenReturn(usuario);

        Usuario usuarioFoundCpf = usuarioService.buscarPorCpf(usuario.getCpf());
        assertEquals(usuario, usuarioFoundCpf);

        verify(usuarioRepository).findByCpf(usuario.getCpf());
        verifyNoMoreInteractions(usuarioRepository);
    }
    @Test
    public void shouldBuscarPorCodigoSucess(){
        when(usuarioRepository.findByCodigo(usuario.getCodigo())).thenReturn(usuario);

        Usuario usuarioFoundCodigo = usuarioService.buscarPorCodigo(usuario.getCodigo());
        assertEquals(usuario, usuarioFoundCodigo);

        verify(usuarioRepository).findByCodigo(usuario.getCodigo());
        verifyNoMoreInteractions(usuarioRepository);
    }
    @Test
    public void shouldUpdateSucess(){
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario usuarioUpdate = usuarioService.update(usuario);
        assertEquals(usuario, usuarioUpdate);

        verify(usuarioRepository).save(usuario);
        verifyNoMoreInteractions(usuarioRepository);
    }
}
