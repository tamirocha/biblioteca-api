package br.com.projeto.bibliotecaapi.services;

import br.com.projeto.bibliotecaapi.models.Usuario;
import br.com.projeto.bibliotecaapi.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;


    public UsuarioService(UsuarioRepository usuarioRepository) {

        this.usuarioRepository = usuarioRepository;
    }

    public Usuario cadastrarUsuario (Usuario usuario) {

        return usuarioRepository.save(usuario);
    }

    public Usuario buscarPorCpf(String cpf) {

        return usuarioRepository.findByCpf(cpf);
    }

    public Usuario buscarPorCodigo(Long codigo) {

        return usuarioRepository.findByCodigo(codigo);
    }

    public Usuario update(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}
