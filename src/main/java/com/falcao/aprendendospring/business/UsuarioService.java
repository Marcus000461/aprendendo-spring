package com.falcao.aprendendospring.business;

import com.falcao.aprendendospring.infrastructure.entity.Usuario;
import com.falcao.aprendendospring.infrastructure.expections.ConflictExecption;
import com.falcao.aprendendospring.infrastructure.expections.ResourceNotFoundExceptin;
import com.falcao.aprendendospring.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UsuarioService {


    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public Usuario salvarUsuario(Usuario usuario) {
        try {
            emailExiste(usuario.getEmail());
             usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
            return usuarioRepository.save(usuario);
        } catch (ConflictExecption e) {
            throw new ConflictExecption("Email já cadastrado", e.getCause());
        }
    }

    public void emailExiste(String email) {
        try{
           boolean existe = verificaEmailExistente(email);
           if(existe){
               throw new ConflictExecption("Email já cadastrado" + email);
           }
        }catch (Exception e){
            throw new ConflictExecption("Email já cadastrado" + e.getCause());
        }
    }

    public boolean verificaEmailExistente(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public Usuario buscaUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email).orElseThrow
                (() -> new ResourceNotFoundExceptin("Email não encontrado" + email));
    }
    public void deleteUsuarioPorEmail(String email) {
        usuarioRepository.deleteByEmail(email);
    }

}
