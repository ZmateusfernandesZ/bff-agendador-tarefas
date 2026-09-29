package com.mateus.bffagendador.business;

import com.mateus.bffagendador.business.dto.request.EnderecoRequestDTO;
import com.mateus.bffagendador.business.dto.request.LoginRequestDTO;
import com.mateus.bffagendador.business.dto.request.TelefoneRequestDTO;
import com.mateus.bffagendador.business.dto.request.UsuarioRequestDTO;
import com.mateus.bffagendador.business.dto.response.EnderecoResponseDTO;
import com.mateus.bffagendador.business.dto.response.LoginResponseDTO;
import com.mateus.bffagendador.business.dto.response.TelefoneResponseDTO;
import com.mateus.bffagendador.business.dto.response.UsuarioResponseDTO;
import com.mateus.bffagendador.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioClient client;


    public UsuarioResponseDTO salvaUsuario(UsuarioRequestDTO usuarioRequestDTO){
        return client.salvaUsuario(usuarioRequestDTO);

    }

    public LoginResponseDTO loginUsuario(LoginRequestDTO loginRequestDTO){
        return client.login(loginRequestDTO);
    }

    public UsuarioResponseDTO buscarUsuarioPorEmail (String email, String token) {
        return client.buscaUsuarioPorEmail(email, token);
    }

    public void deletarUsuarioPorEmail(String email, String token){
        client.deletarUsuarioPorEmail(email, token);
    }

    public UsuarioResponseDTO atualizaDadosUsuario(String token, UsuarioRequestDTO dto){
        return client.atualizaDadoUsuario(dto, token);
    }

    public EnderecoResponseDTO atualizaEndereco (Long idEndereco, EnderecoRequestDTO enderecoResponseDTO, String token){
        return client.atualizaEndereco(idEndereco, enderecoResponseDTO, token);
    }

    public TelefoneResponseDTO atualizaTelefone (Long idTelefone, TelefoneRequestDTO telefoneResponseDTO, String token){
        return client.atualizaTelefone(idTelefone, telefoneResponseDTO, token);
    }

    public EnderecoResponseDTO cadastraEndereco(String token, EnderecoRequestDTO dto){
       return client.cadastraEndereco(dto, token);

    }

    public TelefoneResponseDTO cadastraTelefone(String token, TelefoneRequestDTO dto){
        return client.cadastraTelefone(dto, token);

    }







}
