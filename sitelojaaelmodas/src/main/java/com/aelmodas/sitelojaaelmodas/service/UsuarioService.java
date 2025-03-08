package com.aelmodas.sitelojaaelmodas.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aelmodas.sitelojaaelmodas.AuthJWT.Usuario;
import com.aelmodas.sitelojaaelmodas.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder; // Injetar o password encoder

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

	@Transactional
	public Usuario atualizarUsuarioPorID(Long id, Usuario usuario) {
		// Buscar o usuário pelo ID
		Usuario usuarioExistente = usuarioRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Usuário com ID " + id + " não encontrado."));

		// Atualizar os dados do usuário existente
		usuarioExistente.setLogin(usuario.getLogin());
		usuarioExistente.setEmail(usuario.getEmail());
		
		// Verificar se uma nova senha foi fornecida
	    if (usuario.getSenha() != null && !usuario.getSenha().isBlank()) {
	        // Se a senha não estiver criptografada (não começa com "$2a$")
	        if (!usuario.getSenha().startsWith("$2a$")) {
	            usuarioExistente.setSenha(passwordEncoder.encode(usuario.getSenha())); // Criptografa a nova senha
	        } else {
	            usuarioExistente.setSenha(usuario.getSenha()); // Mantém a senha já criptografada
	        }
	    }
		
		usuarioExistente.setRoles(usuario.getRoles());

		// Salvar e retornar o usuário atualizado
		return usuarioRepository.save(usuarioExistente);
	}

	@Transactional
    public Usuario salvarUsuario(Usuario usuario) {
        if (usuario.getLogin() == null || usuario.getLogin().trim().isEmpty()) {
            throw new IllegalArgumentException("Login do usuário não pode ser nulo ou vazio.");
        }
        
        usuario.setId(null);
        
        if (!usuario.getSenha().startsWith("$2a$")) {
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        }
     
        if (usuario.getRoles() == null || usuario.getRoles().isEmpty()) {
            usuario.setRoles(new ArrayList<>());
        }

        // Se for um novo usuário, garantir que `id` seja gerado pelo banco
        return usuarioRepository.save(usuario);
    }

	// Deletar ususario por id
	public Usuario deletarPorId(Long id) {
		if (usuarioRepository.existsById(id)) {
			usuarioRepository.deleteById(id);
		}
		return null;
	}

	// Buscar usuario por id
	public Usuario buscarPorId(Long id) {
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Usuário com ID " + id + " não encontrado."));
	}

	// Buscar todos os usuarios
	public List<Usuario> buscarTodos() {
		return usuarioRepository.findAll();
	}

}
